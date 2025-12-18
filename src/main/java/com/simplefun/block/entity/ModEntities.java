package com.simplefun.block.entity;

import com.simplefun.Simplefun;
import com.simplefun.block.entity.projectile.BrickProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    // 1. Key definieren
    public static final RegistryKey<EntityType<?>> BRICK_PROJECTILE_KEY = RegistryKey.of(
            RegistryKeys.ENTITY_TYPE, // FIX: RegistryKeys statt Registries
            Identifier.of(Simplefun.MOD_ID, "brick_projectile")
    );

    // 2. Entity registrieren
    public static final EntityType<BrickProjectileEntity> BRICK_PROJECTILE = Registry.register(
            Registries.ENTITY_TYPE,
            BRICK_PROJECTILE_KEY,
            EntityType.Builder.create(
                            (EntityType.EntityFactory<BrickProjectileEntity>) BrickProjectileEntity::new,
                            SpawnGroup.MISC
                    )
                    .dimensions(0.25f, 0.25f)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                    .build(BRICK_PROJECTILE_KEY)
    );

    public static void registerModEntities() {
        Simplefun.LOGGER.info("Registering Mod Entities for " + Simplefun.MOD_ID);
    }
}