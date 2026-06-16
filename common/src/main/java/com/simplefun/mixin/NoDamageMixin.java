package com.simplefun.mixin;

import com.simplefun.SimplefunCommon;
import com.simplefun.registry.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public class NoDamageMixin {

    @ModifyVariable(method = "hurtServer", at = @At("HEAD"), argsOnly = true)
    private float simplefun$modifyDamage(float amount, ServerLevel level, DamageSource source) {
        if (!SimplefunCommon.getConfig().fun.enableNoDamage) return amount;

        if (source.getEntity() instanceof Player player) {
            ItemStack stack = player.getMainHandItem();

            // Feathers deal no damage.
            if (stack.is(Items.FEATHER)) {
                return 0.0f;
            }

            // No-Damage enchantment.
            Optional<Holder.Reference<Enchantment>> noDamage = level.registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .get(ModEnchantments.NO_DAMAGE);
            if (noDamage.isPresent() && EnchantmentHelper.getItemEnchantmentLevel(noDamage.get(), stack) > 0) {
                return 0.0f;
            }
        }
        return amount;
    }
}
