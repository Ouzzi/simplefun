package com.simplefun.block.entity.projectile;

import com.simplefun.Simplefun;
import com.simplefun.block.entity.ModEntities;
import com.simplefun.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BrickProjectileEntity extends ThrownItemEntity {

    public BrickProjectileEntity(EntityType<? extends BrickProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BrickProjectileEntity(World world, LivingEntity owner) {
        super(ModEntities.BRICK_PROJECTILE, owner, world, new ItemStack(Items.BRICK));
    }

    public BrickProjectileEntity(World world, double x, double y, double z) {
        super(ModEntities.BRICK_PROJECTILE, x, y, z, world, new ItemStack(Items.BRICK));
    }

    /**
     * Gemeinsame Wurf-Logik für werfbare Ziegel (ItemMixin) und den Brick Snowball
     * (BrickSnowballItem). Spielt den Sound, spawnt das Projektil (serverseitig),
     * erhöht die "benutzt"-Statistik und verbraucht das Item (außer im Kreativmodus).
     */
    public static void throwFrom(World world, PlayerEntity user, ItemStack stack) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL,
                0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClient()) {
            BrickProjectileEntity projectile = new BrickProjectileEntity(world, user);
            projectile.setItem(stack); // Aussehen = geworfenes Item
            projectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(projectile);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }
    }

    @Override
    protected Item getDefaultItem() {
        return Items.BRICK;
    }

    // Unveränderliche Anzeige-Stacks für die Aufprall-Partikel (vermeidet Allokation pro Treffer).
    private static final ItemStack BRICK_PARTICLE_STACK = new ItemStack(Items.BRICK);
    private static final ItemStack SNOWBALL_PARTICLE_STACK = new ItemStack(Items.SNOWBALL);

    @Override
    public void handleStatus(byte status) {
        // Status 3 = Kollision/Impact
        if (status != 3) return;

        ItemStack stack = this.getStack();

        // Standard-Partikel für das geworfene Item (Ziegel oder Brick-Snowball selbst)
        spawnImpactParticles(stack, 8);

        // Brick Snowball: zusätzlich Ziegel- (rot/braun) und Schneeball-Partikel (weiß) mischen
        if (stack.isOf(ModItems.BRICK_SNOWBALL)) {
            spawnImpactParticles(BRICK_PARTICLE_STACK, 8);
            spawnImpactParticles(SNOWBALL_PARTICLE_STACK, 8);
        }
    }

    private void spawnImpactParticles(ItemStack stack, int count) {
        for (int i = 0; i < count; ++i) {
            this.getEntityWorld().addParticleClient(
                    new ItemStackParticleEffect(ParticleTypes.ITEM, stack),
                    this.getX(), this.getY(), this.getZ(),
                    ((double) this.random.nextFloat() - 0.5D) * 0.08D,
                    ((double) this.random.nextFloat() - 0.5D) * 0.08D,
                    ((double) this.random.nextFloat() - 0.5D) * 0.08D
            );
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        // FIX 2: damage() benötigt jetzt (ServerWorld, Source, Amount)
        World world = this.getEntityWorld();
        if (world instanceof ServerWorld serverWorld) {

            // SCHADEN AUS CONFIG
            float damageAmount = Simplefun.getConfig().fun.brickDamage; // Default

            // Wenn es der Brick Snowball ist, nimm den anderen Wert
            if (this.getStack().isOf(ModItems.BRICK_SNOWBALL)) {
                damageAmount = Simplefun.getConfig().fun.brickSnowballDamage;
            }

            entity.damage(serverWorld, this.getDamageSources().thrown(this, this.getOwner()), damageAmount);
        }
    }

    // Wenn ein Block getroffen wird (Glas brechen)
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getEntityWorld().isClient()) {
            BlockPos pos = blockHitResult.getBlockPos();
            BlockState state = this.getEntityWorld().getBlockState(pos);

            // NEU: Prüfung der Config Option
            boolean canBreak = Simplefun.getConfig().fun.throwableBricksBreakBlocks;

            if (canBreak && shouldBreakBlock(state)) {
                this.getEntityWorld().breakBlock(pos, true, this.getOwner());
                this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
            } else {
                ItemStack stack = this.getStack();
                if (stack.isOf(ModItems.BRICK_SNOWBALL)) {
                    this.playSound(SoundEvents.BLOCK_SNOW_BREAK, 1.0f, 0.8f);
                } else {
                    this.playSound(SoundEvents.BLOCK_STONE_HIT, 1.0f, 1.5f);
                }
            }
        }
    }

    // Allgemeine Kollision (Partikel & Despawn)
    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getEntityWorld().isClient()) {
            this.getEntityWorld().sendEntityStatus(this, (byte)3); // Partikel Effekt
            this.discard(); // Despawn
        }
    }

    private boolean shouldBreakBlock(BlockState state) {
        if (state.isOf(Blocks.TINTED_GLASS)) return false;
        return state.getSoundGroup() == BlockSoundGroup.GLASS;
    }
}