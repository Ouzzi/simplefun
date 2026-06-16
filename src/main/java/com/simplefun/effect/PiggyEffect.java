package com.simplefun.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class PiggyEffect extends StatusEffect {
    public PiggyEffect() {
        // Rein kosmetischer Effekt (rendert einen Schweinekopf) -> NEUTRAL statt HARMFUL,
        // damit er nicht als Debuff zählt und nicht mit Milch entfernt wird.
        super(StatusEffectCategory.NEUTRAL, 0xF0A6A6);
    }

    // Kein canApplyUpdateEffect/applyUpdateEffect Override: der Effekt hat keine
    // Tick-Logik. Der Default (false) verhindert, dass jeden Tick ein No-Op läuft.
}