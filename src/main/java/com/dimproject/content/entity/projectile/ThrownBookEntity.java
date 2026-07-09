package com.dimproject.content.entity.projectile;

import com.dimproject.registries.DimProjectEntities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ThrownBookEntity extends AbstractArrow implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ThrownBookEntity(EntityType<? extends ThrownBookEntity> type, Level level) {
        super(type, level);
    }

    public ThrownBookEntity(Level level, LivingEntity shooter) {
        super(DimProjectEntities.THROWN_BOOK.get(), shooter, level);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.BOOK);
    }

    // Geckolib
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            state.getController().setAnimation(
                RawAnimation.begin().thenLoop("living")
            );
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        spawnHitParticles(result.getLocation().x, result.getLocation().y, result.getLocation().z);
        this.discard(); // supprime l'entité
    }
    @Override
    public void tick() {
        super.tick();
        // Réduire la gravité en modifiant le deltaMovement
        Vec3 movement = this.getDeltaMovement();
        this.setDeltaMovement(movement.x, movement.y + 0.03, movement.z); // contrebalance la gravité vanilla
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        // Dégâts sur l'entité touchée
        Entity target = result.getEntity();
        target.hurt(this.damageSources().arrow(this, this.getOwner()), 5.0F);
        
        spawnHitParticles(result.getEntity().getX(), result.getEntity().getY() + result.getEntity().getBbHeight() / 2, result.getEntity().getZ());
        this.discard(); // supprime l'entité
    }

    private void spawnHitParticles(double x, double y, double z) {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                ParticleTypes.ENCHANT,  // type de particule
                x, y, z,               // position
                30,                     // nombre de particules
                0.3, 0.3, 0.3,         // spread XYZ
                0.1                     // vitesse
            );
        }
    }
}