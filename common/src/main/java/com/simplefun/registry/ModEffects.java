package com.simplefun.registry;

import com.simplefun.Constants;
import com.simplefun.effect.PiggyEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

public class ModEffects {

    public static final ResourceKey<MobEffect> PIGGY_KEY =
            ResourceKey.create(Registries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Constants.MOD_ID, "piggy_effect"));

    public static final MobEffect PIGGY_EFFECT = new PiggyEffect();

    public static Holder<MobEffect> PIGGY_HOLDER;

    public static void register() {
        PIGGY_HOLDER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, PIGGY_KEY, PIGGY_EFFECT);
    }
}
