package com.simplefun.mixin.client;

import com.simplefun.client.renderer.PiggyStateExtension;
import com.simplefun.effect.ModEffects;
import com.simplefun.entity.PiggyTracked;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(method = "updateRenderState", at = @At("TAIL"))
    private void updatePiggyState(LivingEntity entity, LivingEntityRenderState state, float tickDelta, CallbackInfo ci) {
        // Funktioniert für ALLE LivingEntities, inklusive Spieler (lokal und remote).
        if (state instanceof PiggyStateExtension piggyState) {
            boolean hasEffect;
            if (entity instanceof PiggyTracked tracked) {
                // Spieler: Statuseffekte fremder Spieler werden nicht synchronisiert,
                // daher den getrackten (synchronisierten) Wert nutzen.
                hasEffect = tracked.simplefun$isPiggyTracked();
            } else {
                // Mobs: deren Effekt-Instanzen werden über den Entity-Tracker synchronisiert.
                hasEffect = entity.hasStatusEffect(ModEffects.PIGGY_EFFECT);
            }
            piggyState.simplefun$setPiggy(hasEffect);
        }
    }
}