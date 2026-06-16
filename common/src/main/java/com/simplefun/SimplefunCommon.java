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
    }

    // Idempotent. Registered lazily on first use rather than in the loader bootstrap:
    // calling AutoConfig.register during NeoForge's parallel mod-loading phase breaks loading.
    public static void registerConfig() {
        if (!configRegistered) {
            AutoConfig.register(SimplefunConfig.class, GsonConfigSerializer::new);
            configRegistered = true;
        }
    }

    // Always read the live config instance from the holder so runtime edits (commands / GUI) are reflected.
    public static SimplefunConfig getConfig() {
        registerConfig();
        return AutoConfig.getConfigHolder(SimplefunConfig.class).getConfig();
    }

    public static void saveConfig() {
        AutoConfig.getConfigHolder(SimplefunConfig.class).save();
    }
}
