package com.dimproject.content.entity.librairy;


import com.dimproject.procedure.ChickenPanicProcedure;
import com.dimproject.procedure.ChickenejectionProcedure;


import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationController.State;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChickenRooverEntity extends Monster implements GeoEntity {
	
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


   public String animationprocedure;
   String prevAnim;

   public ChickenRooverEntity(EntityType<ChickenRooverEntity> type, Level world) {
      super(type, world);
      this.animationprocedure = "empty";
      this.prevAnim = "empty";
      this.xpReward = 7;
      this.setNoAi(false);
      //this.m_274367_(0.6F);
      this.setPersistenceRequired();
   }


   

   protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
      return 1.6F;
   }

   protected void registerGoals() {
      this.goalSelector.addGoal(1, new PanicGoal(this, 1.2) {
         public boolean canUse() {
            double x = ChickenRooverEntity.this.getX();
            double y = ChickenRooverEntity.this.getY();
            double z = ChickenRooverEntity.this.getZ();
            Entity entity = ChickenRooverEntity.this;
            Level world = ChickenRooverEntity.this.level();
            return super.canUse() && ChickenPanicProcedure.execute(entity);
         }
      });
      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
      this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
      this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
      this.goalSelector.addGoal(5, new FloatGoal(this));
      this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, Player.class, true, false));
   }


//   public SoundEvent m_7515_() {
//      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.ambient"));
//   }
//
//   public SoundEvent m_7975_(DamageSource ds) {
//      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door"));
//   }
//
//   public SoundEvent m_5592_() {
//      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door"));
//   }

   

   public void m_6667_(DamageSource source) {
      super.die(source);
      ChickenejectionProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
   }




//   public static AttributeSupplier.Builder createAttributes() {
//      AttributeSupplier.Builder builder = Mob.m_21552_();
//      builder = builder.m_22268_(Attributes.f_22279_, 0.3);
//      builder = builder.m_22268_(Attributes.f_22276_, (double)20.0F);
//      builder = builder.m_22268_(Attributes.f_22284_, (double)0.0F);
//      builder = builder.m_22268_(Attributes.f_22281_, (double)3.0F);
//      builder = builder.m_22268_(Attributes.f_22277_, (double)16.0F);
//      builder = builder.m_22268_(Attributes.f_22278_, 0.1);
//      return builder;
//   }
   public static AttributeSupplier.Builder registerAttributes() {
	   return Mob.createMobAttributes()
      .add(Attributes.MOVEMENT_SPEED, 0.3)
      .add(Attributes.MAX_HEALTH, (double)20.0F)
      .add(Attributes.ATTACK_DAMAGE, (double)2.0F)
      .add(Attributes.FOLLOW_RANGE, (double)5.0F)
      .add(Attributes.KNOCKBACK_RESISTANCE, (double)16.0F)
      .add(Attributes.ATTACK_KNOCKBACK, 0.1);
   }

   private PlayState movementPredicate(AnimationState event) {
      if (this.animationprocedure.equals("empty")) {
         if (this.isDeadOrDying()) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("Death"));
         } else {
            return this.isAggressive() && event.isMoving() ? event.setAndContinue(RawAnimation.begin().thenLoop("attack")) : event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
         }
      } else {
         return PlayState.STOP;
      }
   }

   private PlayState procedurePredicate(AnimationState event) {
      if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == State.STOPPED || !this.animationprocedure.equals(this.prevAnim) && !this.animationprocedure.equals("empty")) {
         if (!this.animationprocedure.equals(this.prevAnim)) {
            event.getController().forceAnimationReset();
         }

         event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
         if (event.getController().getAnimationState() == State.STOPPED) {
            this.animationprocedure = "empty";
            event.getController().forceAnimationReset();
         }
      } else if (this.animationprocedure.equals("empty")) {
         this.prevAnim = "empty";
         return PlayState.STOP;
      }

      this.prevAnim = this.animationprocedure;
      return PlayState.CONTINUE;
   }



   public void registerControllers(AnimatableManager.ControllerRegistrar data) {
      data.add(new AnimationController[]{new AnimationController(this, "movement", 4, this::movementPredicate)});
      data.add(new AnimationController[]{new AnimationController(this, "procedure", 4, this::procedurePredicate)});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }

   
}
