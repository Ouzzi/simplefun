package com.simplefun;

/**
 * Common entry point, invoked from each loader's bootstrap class.
 * Loader-agnostic initialisation goes here.
 */
public class SimplefunCommon {

    public static void init() {
        Constants.LOG.info("Initializing {} (common)", Constants.MOD_NAME);
    }
}
