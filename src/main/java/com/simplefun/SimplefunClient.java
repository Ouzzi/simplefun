package com.simplefun;

import com.simplefun.block.entity.ModEntities;
import com.simplefun.client.renderer.PigHeadFeatureRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EntityType;

public class SimplefunClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                registrationHelper.register(new PigHeadFeatureRenderer<>(
                        (PlayerEntityRenderer) entityRenderer,
                        context.getEntityModels() // FIX: getEntityModels statt getModelLoader
                ));
            }
        });

    }
}