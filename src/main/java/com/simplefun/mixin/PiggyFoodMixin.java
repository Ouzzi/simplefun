package com.simplefun.mixin;

import com.simplefun.Simplefun;
import com.simplefun.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PiggyFoodMixin {

    @Shadow protected ItemStack activeItemStack;

    @Inject(method = "consumeItem", at = @At("HEAD"))
    private void onConsumeItem(CallbackInfo ci) {
        if (!Simplefun.getConfig().fun.enablePiggyEffect) return;

        LivingEntity entity = (LivingEntity) (Object) this;

        // Wir prüfen das Item, das gerade gegessen wird (activeItemStack)
        if (this.activeItemStack.isOf(Items.PORKCHOP) || this.activeItemStack.isOf(Items.COOKED_PORKCHOP)) {
            if (!entity.getEntityWorld().isClient()) {
                // 5 Minuten Effekt
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.PIGGY_EFFECT, 6000, 0));
            }
        }
    }
}