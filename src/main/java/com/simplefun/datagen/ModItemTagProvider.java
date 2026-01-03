package com.simplefun.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // Füge Feder und Stock zu den verzauberbaren Waffen hinzu.
        // Das erlaubt Knockback, Fire Aspect, Sharpness, etc.
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .add(Items.FEATHER)
                .add(Items.STICK);
                
        // WICHTIG: Damit sie im Amboss auch als "Waffe" gelten für Knockback
        valueLookupBuilder(ItemTags.MACE_ENCHANTABLE) // Knockback geht oft auf Maces und Schwerter
                .add(Items.FEATHER)
                .add(Items.STICK);
    }
}