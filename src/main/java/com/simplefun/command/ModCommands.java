package com.simplefun.command;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.CommandContext;
import com.simplefun.Simplefun;
import com.simplefun.config.SimplefunConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.vehicle.*;
import net.minecraft.inventory.Inventory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class ModCommands {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {


            // --- CONFIG COMMANDS (simplefun) ---
            dispatcher.register(CommandManager.literal("simplefun")
                    // Level 4 für Admin-Befehle (Config Änderungen)
                    .requires(source -> checkPermission(source, 4))

                    // 3. PvP
                    .then(CommandManager.literal("pvp")
                            .then(CommandManager.literal("headDrops")
                                    .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                            .executes(ctx -> {
                                                boolean val = BoolArgumentType.getBool(ctx, "enabled");
                                                Simplefun.getConfig().fun.playerHeadDrops = val;
                                                saveConfig();
                                                ctx.getSource().sendFeedback(() -> Text.literal("Player Head Drops enabled: " + val), true);
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
                                                        ctx.getSource().sendFeedback(() -> Text.literal("Yeet enabled: " + val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("strength")
                                            .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.1f))
                                                    .executes(ctx -> {
                                                        float val = FloatArgumentType.getFloat(ctx, "value");
                                                        Simplefun.getConfig().fun.yeetStrength = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.literal("Yeet Strength set to: " + val), true);
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
                                                        ctx.getSource().sendFeedback(() -> Text.literal("Throwable Bricks enabled: " + val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("breakGlass")
                                            .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                                    .executes(ctx -> {
                                                        boolean val = BoolArgumentType.getBool(ctx, "enabled");
                                                        Simplefun.getConfig().fun.throwableBricksBreakBlocks = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.literal("Bricks Break Glass enabled: " + val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("damage")
                                            .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.0f))
                                                    .executes(ctx -> {
                                                        float val = FloatArgumentType.getFloat(ctx, "value");
                                                        Simplefun.getConfig().fun.brickDamage = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.literal("Brick Damage set to: " + val), true);
                                                        return 1;
                                                    })))
                                    .then(CommandManager.literal("snowballDamage")
                                            .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.0f))
                                                    .executes(ctx -> {
                                                        float val = FloatArgumentType.getFloat(ctx, "value");
                                                        Simplefun.getConfig().fun.brickSnowballDamage = val;
                                                        saveConfig();
                                                        ctx.getSource().sendFeedback(() -> Text.literal("Brick Snowball Damage set to: " + val), true);
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

    private static boolean checkPermission(ServerCommandSource source, int level) {
        if (source.getEntity() instanceof ServerPlayerEntity player) {
            return source.getServer().getPlayerManager().isOperator(player.getPlayerConfigEntry());
        }
        return true;
    }
}