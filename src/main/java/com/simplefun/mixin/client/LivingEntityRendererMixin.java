package com.simplefun.mixin.client;

import com.simplefun.client.renderer.PiggyStateExtension;
import com.simplefun.effect.ModEffects;
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
        // Diese Prüfung funktioniert nun für ALLE LivingEntities, inklusive Spieler (lokal und remote).
        if (state instanceof PiggyStateExtension piggyState) {
            boolean hasEffect = entity.hasStatusEffect(ModEffects.PIGGY_EFFECT);
            piggyState.simplefun$setPiggy(hasEffect);
        }
    }
}