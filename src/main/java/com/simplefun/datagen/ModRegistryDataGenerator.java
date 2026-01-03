package com.simplefun.datagen;

import com.simplefun.enchantment.ModEnchantments;
import com.simplefun.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRegistryDataGenerator extends FabricDynamicRegistryProvider {
    public ModRegistryDataGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        var itemRegistry = registries.getOrThrow(RegistryKeys.ITEM);

        // --- 1. No Damage Enchantment ---
        // Ziel: Unser Custom Tag (Stick, Feder + Waffen)
        Enchantment noDamage = Enchantment.builder(
                Enchantment.definition(
                    itemRegistry.getOrThrow(ModTags.Items.KNOCKBACK_ALLOWED), // Tag nutzen
                    1, // Weight (Seltenheit)
                    1, // Max Level
                    Enchantment.leveledCost(1, 10),
                    Enchantment.leveledCost(10, 20),
                    1, // Anvil Cost
                    AttributeModifierSlot.MAINHAND
                ))
                .build(ModEnchantments.NO_DAMAGE.getValue());

        entries.add(ModEnchantments.NO_DAMAGE, noDamage);


        // --- 2. Vanilla Knockback Override ---
        // Wir überschreiben das Vanilla-Knockback, damit es unseren Tag akzeptiert.
        // Werte sind die originalen Vanilla-Werte (Weight 5, Max Level 2).
        Enchantment knockbackOverride = Enchantment.builder(
                Enchantment.definition(
                        itemRegistry.getOrThrow(ModTags.Items.KNOCKBACK_ALLOWED), // JETZT AUCH FÜR STICK/FEDER!
                        5, // Vanilla Weight: 5 (Common)
                        2, // Vanilla Max Level: 2
                        Enchantment.leveledCost(5, 20), // Vanilla Kosten Berechnung
                        Enchantment.leveledCost(55, 20),
                        2, // Anvil Cost
                        AttributeModifierSlot.MAINHAND
                ))
                .build(Enchantments.KNOCKBACK.getValue());

        // Dies generiert data/minecraft/enchantment/knockback.json und überschreibt Vanilla
        entries.add(Enchantments.KNOCKBACK, knockbackOverride);
    }

    @Override
    public String getName() {
        return "SimpleFun Registry Data Generator";
    }
}