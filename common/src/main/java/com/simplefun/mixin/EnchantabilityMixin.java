package com.simplefun.mixin;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Properties.class)
public class EnchantabilityMixin {

    @Inject(method = "buildAndValidateComponents", at = @At("RETURN"), cancellable = true)
    private void simplefun$addEnchantability(Component name, Identifier modelId, CallbackInfoReturnable<DataComponentMap> cir) {
        if (modelId.equals(Identifier.withDefaultNamespace("feather")) || modelId.equals(Identifier.withDefaultNamespace("stick"))) {
            DataComponentMap original = cir.getReturnValue();
            DataComponentMap newMap = DataComponentMap.builder()
                    .addAll(original)
                    .set(DataComponents.ENCHANTABLE, new Enchantable(10))
                    .build();
            cir.setReturnValue(newMap);
        }
    }
}
