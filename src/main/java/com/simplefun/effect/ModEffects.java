package com.simplefun.effect;

import com.simplefun.Simplefun;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static RegistryEntry<StatusEffect> PIGGY_EFFECT;

    public static void registerEffects() {
        PIGGY_EFFECT = register("piggy_effect", new PiggyEffect());
    }

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Simplefun.MOD_ID, name), effect);
    }
}