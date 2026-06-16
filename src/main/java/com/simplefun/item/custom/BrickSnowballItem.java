package com.simplefun.item.custom;

import com.simplefun.block.entity.projectile.BrickProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BrickSnowballItem extends Item {
    public BrickSnowballItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        // Eigenes Item -> nicht durch das enableThrowableBricks-Flag gegated.
        BrickProjectileEntity.throwFrom(world, user, user.getStackInHand(hand));
        return ActionResult.SUCCESS;
    }
}