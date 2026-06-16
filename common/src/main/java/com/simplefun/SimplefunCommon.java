package com.simplefun;

import com.simplefun.config.SimplefunConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

/**
 * Common entry point, invoked from each loader's bootstrap class.
 * Loader-agnostic initialisation goes here.
 */
public class SimplefunCommon {

    private static boolean configRegistered = false;

    public static void init() {
        Constants.LOG.info("Initializing {} (common)", Constants.MOD_NAME);
        registerConfig();
    }

    public static void registerConfig() {
        if (!configRegistered) {
            AutoConfig.register(SimplefunConfig.class, GsonConfigSerializer::new);
            configRegistered = true;
        }
    }

    // Always read the live config instance from the holder so runtime edits (commands / GUI) are reflected.
    public static SimplefunConfig getConfig() {
        return AutoConfig.getConfigHolder(SimplefunConfig.class).getConfig();
    }

    public static void saveConfig() {
        AutoConfig.getConfigHolder(SimplefunConfig.class).save();
    }
}
