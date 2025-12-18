package com.simplefun.config;

import com.simplefun.Simplefun;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Config(name = Simplefun.MOD_ID)
public class SimplefunConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public Fun fun = new Fun();

    public static class Fun {

        @ConfigEntry.Gui.Tooltip
        public boolean playerHeadDrops = true;

        @ConfigEntry.Gui.Tooltip
        public boolean enableYeet = true;
        @ConfigEntry.Gui.Tooltip
        public float yeetStrength = 3.0f;

        @ConfigEntry.Gui.Tooltip
        public boolean enableThrowableBricks = true;
        @ConfigEntry.Gui.Tooltip
        public boolean throwableBricksBreakBlocks = false;
        @ConfigEntry.Gui.Tooltip
        public float brickDamage = 2.0f;
        @ConfigEntry.Gui.Tooltip
        public float brickSnowballDamage = 2.0f;

    }
}