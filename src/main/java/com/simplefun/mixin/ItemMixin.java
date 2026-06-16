package com.simplefun.mixin;

import com.simplefun.Simplefun;
import com.simplefun.block.entity.projectile.BrickProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void useThrowableBrick(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        // CONFIG CHECK: Wenn deaktiviert, sofort abbrechen
        if (!Simplefun.getConfig().fun.enableThrowableBricks) {
            return;
        }

        Item self = (Item) (Object) this;
        ItemStack itemStack = user.getStackInHand(hand);

        if (self == Items.BRICK || self == Items.NETHER_BRICK || self == Items.RESIN_BRICK) {
            BrickProjectileEntity.throwFrom(world, user, itemStack);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}