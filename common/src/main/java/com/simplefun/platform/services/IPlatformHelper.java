package com.simplefun.platform.services;

import net.minecraft.world.entity.LivingEntity;

public interface IPlatformHelper {

    /**
     * Whether this entity should be rendered with the piggy head, using whatever
     * client-synced mechanism the loader provides (synced entity data on Fabric,
     * a synced data attachment on NeoForge). Falls back to the effect for mobs.
     */
    boolean isPiggySynced(LivingEntity entity);

    /** @return The name of the current platform (e.g. "Fabric" or "NeoForge"). */
    String getPlatformName();

    /** @return True if a mod with the given id is loaded. */
    boolean isModLoaded(String modId);

    /** @return True if running in a development environment. */
    boolean isDevelopmentEnvironment();

    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }
}
