package com.simplefun;

import net.fabricmc.api.ModInitializer;

public class SimplefunFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SimplefunCommon.init();
    }
}
