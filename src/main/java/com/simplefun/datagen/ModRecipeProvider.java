package com.simplefun.datagen;

import com.simplefun.block.ModBlocks;
import com.simplefun.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeGenerator getRecipeGenerator(RegistryWrapper.@NotNull WrapperLookup wrapperLookup, @NotNull RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.COMBAT, ModItems.BRICK_SNOWBALL)
                        .pattern(" S ")
                        .pattern("SBS")
                        .pattern(" S ")
                        .input('S', Items.SNOWBALL)
                        .input('B', Items.BRICK)
                        .criterion(hasItem(Items.SNOWBALL), conditionsFromItem(Items.SNOWBALL))
                        .criterion(hasItem(Items.BRICK), conditionsFromItem(Items.BRICK))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "SimpleTweaks Recipes";
    }
}