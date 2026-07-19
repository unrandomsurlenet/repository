package com.dimproject.content.entity.librairy;

import java.util.UUID;

import javax.annotation.Nullable;

import com.dimproject.DimProjectMod;

import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChickenWorkerEntity extends PathfinderMob implements GeoEntity, NeutralMob {
	private int remainingPersistentAngerTime;
	@Nullable
	private UUID persistentAngerTarget;
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ChickenWorkerEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        // Passif : errance et regard aléatoire
        goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        // Agressif uniquement si attaqué
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.ATTACK_DAMAGE, 3)
            .add(Attributes.FOLLOW_RANGE, 52)
            .add(Attributes.KNOCKBACK_RESISTANCE, 0)
            .add(Attributes.MAX_HEALTH, 10)
            .add(Attributes.MOVEMENT_SPEED, 0.3)
            .add(Attributes.ARMOR, 1);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Controller principal : idle / walk
        controllers.add(new AnimationController<>(this, "movement_controller", 0, state -> {
            if (state.isMoving()) {
                state.getController().setAnimation(RawAnimation.begin().thenLoop("walk"));
            } else {
                state.getController().setAnimation(RawAnimation.begin().thenLoop("stay"));
            }
            return PlayState.CONTINUE;
        }));

        // Controller attaque : déclenché par swingArm
        controllers.add(new AnimationController<>(this, "attack_controller", 0, state -> {
            if (this.swinging) {
                state.getController().setAnimation(RawAnimation.begin().thenPlay("attack"));
                return PlayState.CONTINUE;
            }
            return PlayState.STOP;
        }));
    }
    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnType) {
        if (!(level instanceof Level world))
            return false;

        return world.dimension().equals(DimProjectMod.LIBRAIRY);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
     }

     public void setRemainingPersistentAngerTime(int p_28859_) {
        this.remainingPersistentAngerTime = p_28859_;
     }

     public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
     }

     public void setPersistentAngerTarget(@Nullable UUID p_28855_) {
        this.persistentAngerTarget = p_28855_;
     }

     @Nullable
     public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
     }

	

    
}