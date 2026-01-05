package com.simplefun.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld; // Wichtig für 1.21

public class PiggyEffect extends StatusEffect {
    public PiggyEffect() {
        super(StatusEffectCategory.HARMFUL, 0xF0A6A6);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        return true;
    }
}