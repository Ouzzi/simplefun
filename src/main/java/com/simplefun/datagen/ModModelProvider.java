package com.simplefun.datagen;

import com.simplefun.Simplefun;
import com.simplefun.block.ModBlocks;
import com.simplefun.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // --- ITEMS (Flat 2D) ---
        itemModelGenerator.register(ModItems.BRICK_SNOWBALL, Models.GENERATED);
    }
}