package com.simplefun.mixin;

import com.simplefun.SimplefunCommon;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public abstract class YeetMixin {

    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("RETURN"))
    private void simplefun$onDrop(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
        if (!SimplefunCommon.getConfig().fun.enableYeet) return;

        ItemEntity itemEntity = cir.getReturnValue();
        if (itemEntity == null) return;

        ServerPlayer player = (ServerPlayer) (Object) this;
        // Only manual sneak-drops while alive (avoids yeeting death drops such as the player head).
        if (player.isAlive() && player.isShiftKeyDown()) {
            float strength = SimplefunCommon.getConfig().fun.yeetStrength;
            Vec3 vel = itemEntity.getDeltaMovement();
            itemEntity.setDeltaMovement(vel.multiply(strength, strength * 0.5, strength));
            itemEntity.setPickUpDelay(20);
        }
    }
}
