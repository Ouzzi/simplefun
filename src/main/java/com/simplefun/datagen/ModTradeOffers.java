package com.simplefun.datagen;

import com.simplefun.Simplefun;
import com.simplefun.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.List;
import java.util.Optional;

public class ModTradeOffers {
    public record WeightedEnchantment(RegistryKey<Enchantment> key, int level, int weight) {}

    //register trade offers here
    public static void registerModTradeOffers() {
        Simplefun.LOGGER.info("Registering Custom Trade Offers for " + Simplefun.MOD_ID);
        registerVillagerTrades();
        registerWanderingTraderTrades();
    }


    public static void registerVillagerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1, factories -> {
            List<WeightedEnchantment> buildingPool = List.of(
                    new WeightedEnchantment(ModEnchantments.NO_DAMAGE, 1, 100)
            );
            // FIX: (world, entity, random) statt (entity, random)
            factories.add((world, entity, random) -> new TradeOffer(new TradedItem(Items.EMERALD, 25), createRandomEnchantedBook(entity, random, buildingPool, 0), 3, 15, 0.3f));
        });
    }


    public static void registerWanderingTraderTrades() {
    }

    /**
     * Hilfsmethode: Zieht eine zufällige Verzauberung basierend auf dem Gewicht.
     */
    private static WeightedEnchantment pickWeighted(List<WeightedEnchantment> pool, Random random) {
        int totalWeight = 0;
        for (WeightedEnchantment e : pool) totalWeight += e.weight();
        if (totalWeight == 0) return null;
        int pick = random.nextInt(totalWeight);
        int currentWeight = 0;
        for (WeightedEnchantment e : pool) {
            currentWeight += e.weight();
            if (pick < currentWeight) return e;
        }
        return pool.get(0);
    }

    private static ItemStack createRandomEnchantedItem(Entity entity, Random random, Item item, List<WeightedEnchantment> pool, int chanceForSecond) {
        ItemStack stack = new ItemStack(item);
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
        WeightedEnchantment firstPick = pickWeighted(pool, random);
        if (firstPick != null) {
            addEnchantmentToBuilder(entity, builder, firstPick);
            if (pool.size() > 1 && random.nextInt(100) < chanceForSecond) {
                WeightedEnchantment secondPick = pickWeighted(pool, random);
                int attempts = 0;
                while (secondPick != null && secondPick.key().equals(firstPick.key()) && attempts < 10) {
                    secondPick = pickWeighted(pool, random);
                    attempts++;
                }
                if (secondPick != null && !secondPick.key().equals(firstPick.key())) {
                    addEnchantmentToBuilder(entity, builder, secondPick);
                }
            }
        }
        stack.set(DataComponentTypes.ENCHANTMENTS, builder.build());
        return stack;
    }

    private static void addEnchantmentToBuilder(Entity entity, ItemEnchantmentsComponent.Builder builder, WeightedEnchantment selection) {
        RegistryEntry<Enchantment> enchantmentEntry = entity.getRegistryManager()
                .getOrThrow(RegistryKeys.ENCHANTMENT)
                .getOrThrow(selection.key());

        builder.add(enchantmentEntry, selection.level());
    }

    private static ItemStack createRandomEnchantedBook(Entity entity, Random random, List<WeightedEnchantment> pool, int chanceForSecond) {
        ItemStack stack = new ItemStack(Items.ENCHANTED_BOOK);
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
        WeightedEnchantment firstPick = pickWeighted(pool, random);
        if (firstPick != null) {
            addEnchantmentToBuilder(entity, builder, firstPick);

            // 2. Zweite Verzauberung
            if (pool.size() > 1 && random.nextInt(100) < chanceForSecond) {
                WeightedEnchantment secondPick = pickWeighted(pool, random);
                int attempts = 0;
                while (secondPick != null && secondPick.key().equals(firstPick.key()) && attempts < 10) {
                    secondPick = pickWeighted(pool, random);
                    attempts++;
                }
                if (secondPick != null && !secondPick.key().equals(firstPick.key())) {
                    addEnchantmentToBuilder(entity, builder, secondPick);
                }
            }
        }

        // WICHTIG: Bei Büchern nutzen wir STORED_ENCHANTMENTS
        stack.set(DataComponentTypes.STORED_ENCHANTMENTS, builder.build());
        return stack;
    }
}