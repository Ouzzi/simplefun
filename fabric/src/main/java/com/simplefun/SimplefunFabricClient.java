package com.simplefun;

import com.simplefun.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class SimplefunFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BRICK_PROJECTILE, ThrownItemRenderer::new);
        // Pig-head feature renderer is registered separately (added next step).
    }
}
