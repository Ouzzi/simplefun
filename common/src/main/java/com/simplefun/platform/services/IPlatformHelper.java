package com.simplefun.platform.services;

public interface IPlatformHelper {

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
