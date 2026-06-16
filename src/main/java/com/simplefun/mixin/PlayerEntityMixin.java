package com.simplefun.mixin;

import com.simplefun.effect.ModEffects;
import com.simplefun.entity.PiggyTracked;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Synchronisiert den Piggy-Zustand eines Spielers an alle trackenden Clients.
 * Getrackte Daten werden – anders als Spieler-Statuseffekte – an jeden Client
 * gesendet, der den Spieler sieht. Dadurch erscheint der Schweinekopf auch im
 * Multiplayer bei fremden Spielern.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PiggyTracked {

    @Unique
    private static final TrackedData<Boolean> SIMPLEFUN_PIGGY =
            DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void simplefun$initPiggyData(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(SIMPLEFUN_PIGGY, false);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void simplefun$syncPiggy(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        // Nur der Server ist autoritativ; der Client erhält den Wert per Sync.
        // set() versendet nur bei tatsächlicher Änderung (Vergleich verhindert unnötige Pakete).
        if (!self.getEntityWorld().isClient()) {
            boolean piggy = self.hasStatusEffect(ModEffects.PIGGY_EFFECT);
            if (self.getDataTracker().get(SIMPLEFUN_PIGGY) != piggy) {
                self.getDataTracker().set(SIMPLEFUN_PIGGY, piggy);
            }
        }
    }

    @Override
    public boolean simplefun$isPiggyTracked() {
        return ((LivingEntity) (Object) this).getDataTracker().get(SIMPLEFUN_PIGGY);
    }
}
