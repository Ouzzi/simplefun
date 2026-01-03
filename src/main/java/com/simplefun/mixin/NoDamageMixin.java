package com.simplefun.mixin;

import com.simplefun.enchantment.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public class NoDamageMixin {

    // WICHTIG: Die Argumente müssen exakt mit der Zielmethode (damage) übereinstimmen: (ServerWorld, DamageSource, float)
    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float modifyDamageAmount(float amount, ServerWorld world, DamageSource source) {
        // Prüfen, ob der Angreifer ein Spieler ist
        if (source.getAttacker() instanceof PlayerEntity player) {
            ItemStack stack = player.getMainHandStack();

            // 1. Feder-Logik: Federn machen keinen Schaden
            if (stack.isOf(Items.FEATHER)) {
                return 0.0f;
            }

            // 2. Enchantment-Logik
            // In 1.21 nutzen wir getOrThrow und getOptional
            Optional<RegistryEntry.Reference<Enchantment>> noDamageEntry = world.getRegistryManager()
                    .getOrThrow(RegistryKeys.ENCHANTMENT)
                    .getOptional(ModEnchantments.NO_DAMAGE);

            if (noDamageEntry.isPresent() && EnchantmentHelper.getLevel(noDamageEntry.get(), stack) > 0) {
                return 0.0f;
            }
        }
        return amount;
    }
}