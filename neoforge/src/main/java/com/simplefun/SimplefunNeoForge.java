package com.simplefun;

import com.simplefun.command.SimplefunCommands;
import com.simplefun.event.PlayerHeadDrop;
import com.simplefun.registry.ModItems;
import com.simplefun.registry.SimplefunRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Constants.MOD_ID)
public class SimplefunNeoForge {

    public SimplefunNeoForge(IEventBus modBus, ModContainer modContainer, Dist dist) {
        SimplefunCommon.init();

        // NeoForge registries are frozen except during their RegisterEvent (then unfrozen),
        // so the shared vanilla Registry.register calls run here.
        modBus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.ITEM)) {
                SimplefunRegistry.registerItems();
            } else if (event.getRegistryKey().equals(Registries.ENTITY_TYPE)) {
                SimplefunRegistry.registerEntities();
            } else if (event.getRegistryKey().equals(Registries.MOB_EFFECT)) {
                SimplefunRegistry.registerEffects();
            }
        });

        modBus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
                event.accept(ModItems.BRICK_SNOWBALL);
            }
        });

        NeoForge.EVENT_BUS.addListener((LivingDeathEvent event) ->
                PlayerHeadDrop.onDeath(event.getEntity(), event.getSource()));
        NeoForge.EVENT_BUS.addListener((RegisterCommandsEvent event) ->
                SimplefunCommands.register(event.getDispatcher()));

        if (dist == Dist.CLIENT) {
            SimplefunNeoForgeClient.init(modBus);
        }
    }
}
