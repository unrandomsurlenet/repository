package com.dimproject.content.entity.librairy;

import com.dimproject.DimProjectMod;
import com.dimproject.procedure.ChickenejectionProcedure;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationController.State;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ChickenEngineerEntity extends Monster implements GeoEntity {
	
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	

   private boolean swinging;
//   private boolean lastloop;
   private long lastSwing;
   public String animationprocedure;
   String prevAnim;


   public ChickenEngineerEntity(EntityType<ChickenEngineerEntity> type, Level world) {
      super(type, world);
      this.animationprocedure = "empty";
      this.prevAnim = "empty";
      this.xpReward = 7;
      this.setNoAi(false);
      //this.m_274367_(0.6F);
      this.setPersistenceRequired();
   }

//   protected void m_8097_() {
//      super.m_8097_();
//      this.f_19804_.m_135372_(SHOOT, false);
//      this.f_19804_.m_135372_(ANIMATION, "undefined");
//      this.f_19804_.m_135372_(TEXTURE, "chkn_engineer");
//   }

   protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
      return 2.2F;
   }


   protected void registerGoals() {
      this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.5F, false));
      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true, false));
      this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
      this.goalSelector.addGoal(5, new FloatGoal(this));
      this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
   }


//   public SoundEvent m_7515_() {
//      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.ambient"));
//   }
//
//   public void m_7355_(BlockPos pos, BlockState blockIn) {
//      this.m_5496_((SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.step")), 0.15F, 1.0F);
//   }
//
//   public SoundEvent m_7975_(DamageSource ds) {
//      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door"));
//   }
//
//   public SoundEvent m_5592_() {
//      return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door"));
   

  

   public void die(DamageSource source) {
      super.die(source);
      ChickenejectionProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
   }
   @Override
   public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnType) {
       if (!(level instanceof Level world))
           return false;

       return world.dimension().equals(DimProjectMod.LIBRAIRY);
   }


//   public void aiStep() {
//      super.m_6075_();
//      this.m_6210_();
//   }

   

   public static AttributeSupplier.Builder registerAttributes() {
	   return Mob.createMobAttributes()
      .add(Attributes.MOVEMENT_SPEED, 0.2)
      .add(Attributes.MAX_HEALTH, (double)30.0F)
      .add(Attributes.ATTACK_DAMAGE, (double)2.0F)
      .add(Attributes.FOLLOW_RANGE, (double)5.0F)
      .add(Attributes.KNOCKBACK_RESISTANCE, (double)16.0F)
      .add(Attributes.ATTACK_KNOCKBACK, 0.1);
   }

   private PlayState movementPredicate(AnimationState event) {
      if (!this.animationprocedure.equals("empty")) {
         return PlayState.STOP;
      } else if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F) || !(event.getLimbSwingAmount() < 0.15F)) && !this.isAggressive()) {
         return event.setAndContinue(RawAnimation.begin().thenLoop("walk"));
      } else if (this.isDeadOrDying()) {
         return event.setAndContinue(RawAnimation.begin().thenPlay("death"));
      } else {
         return this.isAggressive() && event.isMoving() ? event.setAndContinue(RawAnimation.begin().thenLoop("aggression")) : event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
      }
   }

   private PlayState attackingPredicate(AnimationState event) {
      double d1 = this.getX() - this.xo;
      double d0 = this.getZ() - this.zo;
      float velocity = (float)Math.sqrt(d1 * d1 + d0 * d0);
      if (this.getAttackAnim(event.getPartialTick()) > 0.0F && !this.swinging) {
         this.swinging = true;
         this.lastSwing = this.level().getGameTime();
      }

      if (this.swinging && this.lastSwing + 7L <= this.level().getGameTime()) {
         this.swinging = false;
      }

      if (this.swinging && event.getController().getAnimationState() == State.STOPPED) {
         event.getController().forceAnimationReset();
         return event.setAndContinue(RawAnimation.begin().thenPlay("attack"));
      } else {
         return PlayState.CONTINUE;
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
      data.add(new AnimationController[]{new AnimationController(this, "movement", 2, this::movementPredicate)});
      data.add(new AnimationController[]{new AnimationController(this, "attacking", 2, this::attackingPredicate)});
      data.add(new AnimationController[]{new AnimationController(this, "procedure", 2, this::procedurePredicate)});
   }

   public AnimatableInstanceCache getAnimatableInstanceCache() {
      return this.cache;
   }

   
}