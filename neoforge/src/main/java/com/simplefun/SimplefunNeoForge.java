package com.simplefun;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class SimplefunNeoForge {

    public SimplefunNeoForge(IEventBus eventBus) {
        SimplefunCommon.init();
    }
}
