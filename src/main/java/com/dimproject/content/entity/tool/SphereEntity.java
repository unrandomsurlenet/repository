package com.dimproject.content.entity.tool;

import java.util.UUID;

import com.dimproject.registries.DimProjectMobEffect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class SphereEntity extends Entity {

    private static final EntityDataAccessor<Integer> TICKS_ALIVE =
        SynchedEntityData.defineId(SphereEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SPEED_TIMER =
        SynchedEntityData.defineId(SphereEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FOV_TIMER =
        SynchedEntityData.defineId(SphereEntity.class, EntityDataSerializers.INT);

    private static final int APPEAR_DURATION = 40;
    private static final int LIFETIME = 600;
    private static final int DISAPPEAR_DURATION = 40;
    private static final int SPEED_EFFECT_DURATION = 60;  // traits s'arrêtent à 3s
    private static final int FOV_EFFECT_DURATION = 80;   // FOV revient lentement en 6s
    private UUID ownerUUID;

    public SphereEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setInvisible(true);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TICKS_ALIVE, 0);
        this.entityData.define(SPEED_TIMER, SPEED_EFFECT_DURATION);
        this.entityData.define(FOV_TIMER, FOV_EFFECT_DURATION);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            int t = this.entityData.get(TICKS_ALIVE);
            this.entityData.set(TICKS_ALIVE, t + 1);

            int speed = this.entityData.get(SPEED_TIMER);
            if (speed > 0) this.entityData.set(SPEED_TIMER, speed - 1);

            int fov = this.entityData.get(FOV_TIMER);
            if (fov > 0) this.entityData.set(FOV_TIMER, fov - 1);

            // Applique l'effet de potion toutes les secondes (20 ticks)
            if (t % 20 == 0) {
                float radius = 30f;
                this.level().getEntitiesOfClass(
                    LivingEntity.class,
                    new AABB(
                        this.getX() - radius, this.getY() - radius, this.getZ() - radius,
                        this.getX() + radius, this.getY() + radius, this.getZ() + radius
                    ),
                    entity -> entity.distanceTo(this) <= radius // filtre sphérique exact
                           && !(entity == this.getOwner())  // exclut l'entité elle-même
                ).forEach(entity -> {
                    entity.addEffect(new MobEffectInstance(
                    		DimProjectMobEffect.NEUROLOGICAL_OVERLOAD.get(),
                        40,  // durée : 2s (sera renouvelé chaque seconde)
                        0,   // amplifier
                        false,
                        true // affiche les particules
                    ));
                });
            }

            if (t >= LIFETIME + DISAPPEAR_DURATION) this.discard();
        }
    }

    // Traits de vitesse
    public boolean isSpeedEffectActive() {
        return this.entityData.get(SPEED_TIMER) > 0;
    }

    public float getSpeedIntensity(float partialTick) {
        float t = this.entityData.get(SPEED_TIMER) - partialTick;
        float half = SPEED_EFFECT_DURATION / 2f;
        float intensity = t > half ? (SPEED_EFFECT_DURATION - t) / half : t / half;
        return intensity * intensity * (3f - 2f * intensity);
    }

    // FOV
    public boolean isFovEffectActive() {
        return this.entityData.get(FOV_TIMER) > 0;
    }

    public float getFovIntensity(float partialTick) {
        float t = this.entityData.get(FOV_TIMER) - partialTick;
        float half = FOV_EFFECT_DURATION / 2f;
        float intensity = t > half ? (FOV_EFFECT_DURATION - t) / half : t / half;
        return intensity * intensity * (3f - 2f * intensity);
    }

    public float getProgress(float partialTick) {
        float t = this.entityData.get(TICKS_ALIVE) + partialTick;
        if (t < APPEAR_DURATION) return t / APPEAR_DURATION;
        if (t > LIFETIME) return 1.0f - ((t - LIFETIME) / DISAPPEAR_DURATION);
        return 1.0f;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putInt("ticksAlive", this.entityData.get(TICKS_ALIVE));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.entityData.set(TICKS_ALIVE, nbt.getInt("ticksAlive"));
    }
    public void setOwner(Entity player) {
        this.ownerUUID = player.getUUID();
    }

    public Entity getOwner() {
        if (ownerUUID == null) return null;
        return this.level().getPlayerByUUID(ownerUUID);
    }
}