package com.simplefun.mixin.client;

import com.simplefun.client.renderer.PiggyStateExtension;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements PiggyStateExtension {
    @Unique
    private boolean isPiggy = false;

    @Override
    public void simplefun$setPiggy(boolean isPiggy) {
        this.isPiggy = isPiggy;
    }

    @Override
    public boolean simplefun$isPiggy() {
        return this.isPiggy;
    }
}