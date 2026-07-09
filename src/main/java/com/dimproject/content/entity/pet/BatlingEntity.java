package com.dimproject.content.entity.pet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BatlingEntity extends Monster implements GeoEntity{
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public BatlingEntity(EntityType<? extends Monster> p_27412_, Level p_27413_) {
		super(p_27412_, p_27413_);
		
	}
	@Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            state.getController().setAnimation(
                RawAnimation.begin().thenLoop("living")
            );
            return PlayState.CONTINUE;
        }));
    }
	public static AttributeSupplier.Builder registerAttributes() {
		return Mob.createMobAttributes()
				.add(Attributes.ATTACK_DAMAGE, 3)
				.add(Attributes.FOLLOW_RANGE, 52)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0)
				.add(Attributes.MAX_HEALTH, 10)
				.add(Attributes.MOVEMENT_SPEED, 10)
				.add(Attributes.ARMOR, 1);
		
		
				
				
	}


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    
    @Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<Monster>(this, Monster.class, true));
	}
    public void tick() {
        super.tick();
       
           this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        

     }



}
