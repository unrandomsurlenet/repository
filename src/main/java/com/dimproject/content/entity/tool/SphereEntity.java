package com.dimproject.content.entity.tool;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SphereEntity extends Entity {

    private static final EntityDataAccessor<Integer> TICKS_ALIVE =
        SynchedEntityData.defineId(SphereEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EFFECT_TIMER =
    	    SynchedEntityData.defineId(SphereEntity.class, EntityDataSerializers.INT);

    	private static final int EFFECT_DURATION = 80;

    private static final int APPEAR_DURATION = 40;
    private static final int LIFETIME = 600;
    private static final int DISAPPEAR_DURATION = 40;

    public SphereEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setInvisible(true);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TICKS_ALIVE, 0);
        this.entityData.define(EFFECT_TIMER, EFFECT_DURATION);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            int t = this.entityData.get(TICKS_ALIVE);
            this.entityData.set(TICKS_ALIVE, t + 1);

            // Décompte l'effet
            int effect = this.entityData.get(EFFECT_TIMER);
            if (effect > 0) this.entityData.set(EFFECT_TIMER, effect - 1);

            if (t >= LIFETIME + DISAPPEAR_DURATION) {
                this.discard();
            }
        }
    }

    // 0.0 = début/fin de l'effet, 1.0 = pic de l'effet
    public float getEffectIntensity(float partialTick) {
        float t = this.entityData.get(EFFECT_TIMER) - partialTick;
        float half = EFFECT_DURATION / 2f;

        if (t > half) {
            // montée
            return (EFFECT_DURATION - t) / half;
        } else {
            // descente
            return t / half;
        }
    }

    public boolean isEffectActive() {
        return this.entityData.get(EFFECT_TIMER) > 0;
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
}