package com.simplefun.platform;

import com.simplefun.entity.PiggyTracked;
import com.simplefun.platform.services.IPlatformHelper;
import com.simplefun.registry.ModEffects;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.LivingEntity;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isPiggySynced(LivingEntity entity) {
        // Players carry synced entity data (PlayerEntityMixin); mobs sync their effects via the entity tracker.
        if (entity instanceof PiggyTracked tracked) {
            return tracked.simplefun$isPiggyTracked();
        }
        return entity.hasEffect(ModEffects.holder());
    }

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
