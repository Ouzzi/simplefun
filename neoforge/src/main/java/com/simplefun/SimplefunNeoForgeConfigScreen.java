package com.simplefun;

import com.simplefun.config.SimplefunConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;

/**
 * Builds a Cloth Config screen for NeoForge using the low-level ConfigBuilder API
 * (AutoConfig's screen helper is Fabric-only). Registered as the mod's IConfigScreenFactory
 * so the config button appears in NeoForge's mod list. Uses the same lang keys as the Fabric GUI.
 */
public class SimplefunNeoForgeConfigScreen {

    private static final String PREFIX = "text.autoconfig.simplefun.option.fun.";

    public static Screen build(Screen parent) {
        SimplefunConfig.Fun fun = SimplefunCommon.getConfig().fun;

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("text.autoconfig.simplefun.title"))
                .setSavingRunnable(SimplefunCommon::saveConfig);

        ConfigEntryBuilder eb = builder.entryBuilder();
        ConfigCategory cat = builder.getOrCreateCategory(Component.translatable("text.autoconfig.simplefun.option.fun"));

        bool(cat, eb, "playerHeadDrops", fun.playerHeadDrops, true, v -> fun.playerHeadDrops = v);
        bool(cat, eb, "enablePiggyEffect", fun.enablePiggyEffect, true, v -> fun.enablePiggyEffect = v);
        bool(cat, eb, "enableNoDamage", fun.enableNoDamage, true, v -> fun.enableNoDamage = v);
        bool(cat, eb, "enableYeet", fun.enableYeet, true, v -> fun.enableYeet = v);
        floatField(cat, eb, "yeetStrength", fun.yeetStrength, 3.0f, 0.1f, v -> fun.yeetStrength = v);
        bool(cat, eb, "enableThrowableBricks", fun.enableThrowableBricks, true, v -> fun.enableThrowableBricks = v);
        bool(cat, eb, "throwableBricksBreakBlocks", fun.throwableBricksBreakBlocks, false, v -> fun.throwableBricksBreakBlocks = v);
        floatField(cat, eb, "brickDamage", fun.brickDamage, 2.0f, 0.0f, v -> fun.brickDamage = v);
        floatField(cat, eb, "brickSnowballDamage", fun.brickSnowballDamage, 2.0f, 0.0f, v -> fun.brickSnowballDamage = v);

        return builder.build();
    }

    private static void bool(ConfigCategory cat, ConfigEntryBuilder eb, String key, boolean current, boolean def, Consumer<Boolean> save) {
        cat.addEntry(eb.startBooleanToggle(Component.translatable(PREFIX + key), current)
                .setDefaultValue(def)
                .setTooltip(Component.translatable(PREFIX + key + ".@Tooltip"))
                .setSaveConsumer(save)
                .build());
    }

    private static void floatField(ConfigCategory cat, ConfigEntryBuilder eb, String key, float current, float def, float min, Consumer<Float> save) {
        cat.addEntry(eb.startFloatField(Component.translatable(PREFIX + key), current)
                .setDefaultValue(def)
                .setMin(min)
                .setTooltip(Component.translatable(PREFIX + key + ".@Tooltip"))
                .setSaveConsumer(save)
                .build());
    }
}
