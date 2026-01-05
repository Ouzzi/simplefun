package com.simplefun;

import com.simplefun.block.ModBlocks;
import com.simplefun.block.entity.ModEntities;
import com.simplefun.command.ModCommands;
import com.simplefun.config.SimplefunConfig;
import com.simplefun.datagen.ModTradeOffers;
import com.simplefun.enchantment.ModEnchantments;
import com.simplefun.event.PlayerHeadDropHandler;
import com.simplefun.item.ModItems;
import net.fabricmc.api.ModInitializer;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Simplefun implements ModInitializer {
	public static final String MOD_ID = "simplefun";

    private static SimplefunConfig CONFIG;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        LOGGER.info("Initializing Simplefun mod...");

        AutoConfig.register(SimplefunConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SimplefunConfig.class).getConfig();


        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        PlayerHeadDropHandler.register();
        ModEntities.registerModEntities();
        ModCommands.register();
        ModEnchantments.registerModEnchantments();
        ModTradeOffers.registerModTradeOffers();

	}

    public static SimplefunConfig getConfig() { return CONFIG; }
}