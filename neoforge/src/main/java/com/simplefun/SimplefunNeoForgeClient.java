package com.simplefun;

import com.simplefun.registry.ModEntities;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * Client-only NeoForge setup. Only referenced when running on the client (dist guard in the entry point),
 * so its client-only imports are never loaded on a dedicated server.
 *
 * Note: cloth-config's in-game config screen helper (AutoConfig.getConfigScreen) is Fabric-only, so on
 * NeoForge the config is edited via the config file and the /simplefun commands rather than an in-game menu.
 */
public class SimplefunNeoForgeClient {

    public static void init(IEventBus modBus) {
        modBus.addListener((EntityRenderersEvent.RegisterRenderers event) ->
                event.registerEntityRenderer(ModEntities.BRICK_PROJECTILE, ThrownItemRenderer::new));
    }
}
