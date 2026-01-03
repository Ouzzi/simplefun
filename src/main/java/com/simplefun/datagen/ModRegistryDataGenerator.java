package com.simplefun.datagen;

import com.simplefun.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
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

        // Erstelle das "No Damage" Enchantment
        // Es macht nichts "technisches" im JSON (Schaden regeln wir per Mixin),
        // aber es muss existieren, damit man es nutzen kann.
        Enchantment noDamage = Enchantment.builder(
                Enchantment.definition(
                    itemRegistry.getOrThrow(ItemTags.WEAPON_ENCHANTABLE), // Kann auf alles, was als Waffe gilt
                    1, // Seltenheit (Weight)
                    1, // Max Level
                    Enchantment.leveledCost(1, 10), // Min Kosten
                    Enchantment.leveledCost(10, 20), // Max Kosten
                    1, // Anvil Kosten
                    AttributeModifierSlot.MAINHAND
                ))
                .build(ModEnchantments.NO_DAMAGE.getValue());

        entries.add(ModEnchantments.NO_DAMAGE, noDamage);
    }

    @Override
    public String getName() {
        return "SimpleFun Registry Data Generator";
    }
}