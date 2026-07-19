package com.dimproject.content.entity.librairy;

import java.util.UUID;

import javax.annotation.Nullable;

import com.dimproject.DimProjectMod;
import com.dimproject.util.entity.FlyingMeleeAttackGoal;

import net.minecraft.core.BlockPos;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChickenDroneEntity extends PathfinderMob implements GeoEntity, NeutralMob {
	private int remainingPersistentAngerTime;
	@Nullable
	private UUID persistentAngerTarget;
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ChickenDroneEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        nav.setCanPassDoors(true);
        return nav;
    }

    @Override
    protected void registerGoals() {
    	goalSelector.addGoal(1, new FloatGoal(this));
        goalSelector.addGoal(2, new FlyingMeleeAttackGoal(this, 1.0));
        goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    	
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));}

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
    }

    @Override
    public void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        // désactivé
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.ATTACK_DAMAGE, 3)
            .add(Attributes.FOLLOW_RANGE, 52)
            .add(Attributes.KNOCKBACK_RESISTANCE, 0)
            .add(Attributes.MAX_HEALTH, 10)
            .add(Attributes.FLYING_SPEED, 0.8)
            .add(Attributes.ARMOR, 1);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            state.getController().setAnimation(RawAnimation.begin().thenLoop("living"));
            return PlayState.CONTINUE;
        }));
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