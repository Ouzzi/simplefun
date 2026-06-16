package com.simplefun.mixin.client;

import com.simplefun.client.PiggyStateExtension;
import com.simplefun.entity.PiggyTracked;
import com.simplefun.registry.ModEffects;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void simplefun$updatePiggyState(LivingEntity entity, LivingEntityRenderState state, float partialTick, CallbackInfo ci) {
        if (state instanceof PiggyStateExtension piggyState) {
            boolean hasEffect;
            if (entity instanceof PiggyTracked tracked) {
                // Players: remote players' effects aren't synced; use the synced flag.
                hasEffect = tracked.simplefun$isPiggyTracked();
            } else {
                // Mobs: effect instances are synced via the entity tracker.
                hasEffect = entity.hasEffect(ModEffects.holder());
            }
            piggyState.simplefun$setPiggy(hasEffect);
        }
    }
}
