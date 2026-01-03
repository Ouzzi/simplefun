package com.simplefun.enchantment;

import com.simplefun.Simplefun;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    // Der Key für unser neues Enchantment
    public static final RegistryKey<Enchantment> NO_DAMAGE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Simplefun.MOD_ID, "no_damage"));

    public static void registerModEnchantments() {
        Simplefun.LOGGER.info("Registering Mod Enchantments for " + Simplefun.MOD_ID);
    }
}