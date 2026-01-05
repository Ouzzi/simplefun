package com.simplefun.datagen;

import com.simplefun.util.ModTags;
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
        valueLookupBuilder(ModTags.Items.SIMPLE_FUN_ITEMS)
                .add(Items.FEATHER)
                .add(Items.STICK);

        valueLookupBuilder(ModTags.Items.KNOCKBACK_ALLOWED)
                .add(Items.FEATHER)
                .add(Items.STICK)
                .forceAddTag(ItemTags.SWORDS)
                .forceAddTag(ItemTags.AXES)
                .forceAddTag(ItemTags.MACE_ENCHANTABLE)
                .forceAddTag(ItemTags.TRIDENT_ENCHANTABLE)
                .forceAddTag(ItemTags.BOW_ENCHANTABLE)
                .forceAddTag(ItemTags.CROSSBOW_ENCHANTABLE)
                .forceAddTag(ItemTags.LUNGE_ENCHANTABLE);
    }
}