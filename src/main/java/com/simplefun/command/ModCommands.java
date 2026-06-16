package com.simplefun.command;

import com.mojang.brigadier.arguments.*;
import com.simplefun.Simplefun;
import com.simplefun.config.SimplefunConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class ModCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {


            // --- CONFIG COMMANDS (simplefun) ---
            dispatcher.register(CommandManager.literal("simplefun")
                    // Level 4 (ADMINS) für Admin-Befehle (Config Änderungen).
                    // requirePermissionLevel respektiert die OP-Stufe und Konsolen-/Funktions-Rechte korrekt.
                    .requires(CommandManager.requirePermissionLevel(CommandManager.ADMINS_CHECK))

                    // 3. PvP
                    .then(CommandManager.literal("pvp")
                            .then(CommandManager.literal("headDrops")
                                    .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                            .executes(ctx -> {
                                                boolean val = BoolArgumentType.getBool(ctx, "enabled");
                                                Simplefun.getConfig().fun.playerHeadDrops = val;
                                                saveConfig();
                                                ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.pvp.headDrops", val), true);
                                                return 1;
                                            })))
                    )

                    // 8. Tweaks (Alle Features)
                    .then(CommandManager.literal("tweaks")
                            // Yeet
                            .then(CommandManager.literal("yeet")
                                    .then(CommandManager.literal("toggle")
                                            .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                                    .executes(ctx -> {
                                                        boolean val = BoolArgumentType.getBool(ctx, "enabled");
                                                        Simplefun.getConfig().fun.enableYeet = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.yeet.enabled", val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("strength")
                                            .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.1f))
                                                    .executes(ctx -> {
                                                        float val = FloatArgumentType.getFloat(ctx, "value");
                                                        Simplefun.getConfig().fun.yeetStrength = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.yeet.strength", val), true);
                                                        return 1;
                                                    })))
                            )
                            .then(CommandManager.literal("bricks")
                                    .then(CommandManager.literal("enable")
                                            .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                                    .executes(ctx -> {
                                                        boolean val = BoolArgumentType.getBool(ctx, "enabled");
                                                        Simplefun.getConfig().fun.enableThrowableBricks = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.bricks.enabled", val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("breakGlass")
                                            .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                                    .executes(ctx -> {
                                                        boolean val = BoolArgumentType.getBool(ctx, "enabled");
                                                        Simplefun.getConfig().fun.throwableBricksBreakBlocks = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.bricks.breakGlass", val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("damage")
                                            .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.0f))
                                                    .executes(ctx -> {
                                                        float val = FloatArgumentType.getFloat(ctx, "value");
                                                        Simplefun.getConfig().fun.brickDamage = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.bricks.damage", val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("snowballDamage")
                                            .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.0f))
                                                    .executes(ctx -> {
                                                        float val = FloatArgumentType.getFloat(ctx, "value");
                                                        Simplefun.getConfig().fun.brickSnowballDamage = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.translatable("commands.simplefun.bricks.snowballDamage", val), true);
                                                        return 1;
                                                    })))
                            )
                    )

            );
        });
    }

    private static void saveConfig() {
        AutoConfig.getConfigHolder(SimplefunConfig.class).save();
    }
}