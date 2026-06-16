package com.simplefun;

import com.simplefun.command.SimplefunCommands;
import com.simplefun.event.PlayerHeadDrop;
import com.simplefun.registry.ModItems;
import com.simplefun.registry.SimplefunRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class SimplefunFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SimplefunCommon.init();
        SimplefunCommon.registerConfig();

        // Fabric registries are open during init.
        SimplefunRegistry.registerItems();
        SimplefunRegistry.registerEntities();
        SimplefunRegistry.registerEffects();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> entries.accept(ModItems.BRICK_SNOWBALL));
        ServerLivingEntityEvents.AFTER_DEATH.register(PlayerHeadDrop::onDeath);
        CommandRegistrationCallback.EVENT.register((dispatcher, access, environment) -> SimplefunCommands.register(dispatcher));
    }
}
