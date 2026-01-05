package com.simplefun.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class PigHeadFeatureRenderer<S extends LivingEntityRenderState, M extends EntityModel<S>> extends FeatureRenderer<S, M> {

    private final PigHeadModel pigHeadModel;

    // ÄNDERUNG: Hier verweisen wir auf deine eigene Textur in assets/simplefun/textures/entity/pig/pig.png
    private static final Identifier PIG_TEXTURE = Identifier.of("simplefun", "textures/entity/pig/pig.png");

    public PigHeadFeatureRenderer(FeatureRendererContext<S, M> context, LoadedEntityModels loader) {
        super(context);
        ModelPart root = loader.getModelPart(EntityModelLayers.PIG);
        this.pigHeadModel = new PigHeadModel(root.getChild("head"));
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S state, float limbAngle, float limbDistance) {
        // Prüfen, ob der Effekt aktiv ist
        if (state instanceof PiggyStateExtension piggyState && !piggyState.simplefun$isPiggy()) return;
        // Unsichtbare Spieler ignorieren
        if (state instanceof PlayerEntityRenderState playerState && playerState.invisible) return;

        matrices.push();

        // Positionierung am Kopf des Spielers
        if (this.getContextModel() instanceof BipedEntityModel<?> playerModel) {
            ModelPart headPart = playerModel.head;
            matrices.translate(headPart.originX / 16.0F, headPart.originY / 16.0F, headPart.originZ / 16.0F);
            if (headPart.roll != 0.0F) matrices.multiply(RotationAxis.POSITIVE_Z.rotation(headPart.roll));
            if (headPart.yaw != 0.0F) matrices.multiply(RotationAxis.POSITIVE_Y.rotation(headPart.yaw));
            if (headPart.pitch != 0.0F) matrices.multiply(RotationAxis.POSITIVE_X.rotation(headPart.pitch));
        }

        // Leichte Skalierung und Anpassung, damit es gut sitzt
        matrices.scale(1.05F, 1.05F, 1.05F);
        matrices.translate(0.0F, -0.2F, 0.1F);

        // Das Modell rendern
        FeatureRenderer.renderModel(
                this.pigHeadModel,
                PIG_TEXTURE, // Deine Custom Textur
                matrices,
                queue,
                light,
                state,
                -1,
                OverlayTexture.DEFAULT_UV // Fix für die schwarze Box
        );

        matrices.pop();
    }

    @Environment(EnvType.CLIENT)
    public static class PigHeadModel extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head;

        public PigHeadModel(ModelPart head) {
            super(head, RenderLayers::entityTranslucent);
            this.head = head;
            this.head.setOrigin(0.0F, 0.0F, 0.0F);
            this.head.setAngles(0.0F, 0.0F, 0.0F);
        }

        @Override
        public void setAngles(LivingEntityRenderState state) {
            // Kopf-Rotationen auf 0 setzen, da wir die Matrix des Eltern-Modells (Spieler) nutzen
            this.head.pitch = 0.0F;
            this.head.yaw = 0.0F;
            this.head.roll = 0.0F;
        }
    }
}