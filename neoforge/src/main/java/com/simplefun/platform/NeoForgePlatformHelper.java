package com.simplefun.platform;

import com.simplefun.SimplefunAttachments;
import com.simplefun.platform.services.IPlatformHelper;
import com.simplefun.registry.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public boolean isPiggySynced(LivingEntity entity) {
        // Players carry a synced data attachment; mobs sync their effects via the entity tracker.
        if (entity instanceof Player) {
            return entity.getData(SimplefunAttachments.PIGGY);
        }
        return entity.hasEffect(ModEffects.holder());
    }

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.getCurrent().isProduction();
    }
}
