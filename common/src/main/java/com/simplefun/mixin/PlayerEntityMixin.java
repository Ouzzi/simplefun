package com.simplefun.mixin;

import com.simplefun.entity.PiggyTracked;
import com.simplefun.registry.ModEffects;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Syncs a player's piggy state to all tracking clients via synced entity data
 * (player status effects are not sent to remote trackers in vanilla).
 */
@Mixin(Player.class)
public abstract class PlayerEntityMixin implements PiggyTracked {

    @Unique
    private static final EntityDataAccessor<Boolean> SIMPLEFUN_PIGGY =
            SynchedEntityData.defineId(Player.class, EntityDataSerializers.BOOLEAN);

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void simplefun$initPiggyData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(SIMPLEFUN_PIGGY, false);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void simplefun$syncPiggy(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        // Server is authoritative; set() only sends a packet on actual change.
        if (!self.level().isClientSide()) {
            boolean piggy = self.hasEffect(ModEffects.PIGGY_HOLDER);
            if (self.getEntityData().get(SIMPLEFUN_PIGGY) != piggy) {
                self.getEntityData().set(SIMPLEFUN_PIGGY, piggy);
            }
        }
    }

    @Override
    public boolean simplefun$isPiggyTracked() {
        return ((LivingEntity) (Object) this).getEntityData().get(SIMPLEFUN_PIGGY);
    }
}
