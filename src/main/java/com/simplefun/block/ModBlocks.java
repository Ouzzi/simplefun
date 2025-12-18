package com.simplefun.block;

import com.simplefun.Simplefun;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Helper Methode, um Keys sauber zu erzeugen (vermeidet statische Init-Probleme)
    private static RegistryKey<Block> keyOf(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Simplefun.MOD_ID, name));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Simplefun.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        // Item Key definieren
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Simplefun.MOD_ID, name));

        Registry.register(Registries.ITEM, Identifier.of(Simplefun.MOD_ID, name),
                new BlockItem(block, new Item.Settings().registryKey(itemKey)));
    }

    public static void registerModBlocks() {
        Simplefun.LOGGER.info("Registering Mod Blocks for " + Simplefun.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
        });
    }
}