package com.dimproject.content.entity.projectile;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class ChickenBulletEntity extends AbstractArrow implements ItemSupplier {


   public ChickenBulletEntity(EntityType<? extends ChickenBulletEntity> type, Level world) {
      super(type, world);
   }


   protected ItemStack getDefaultPickupItem() {
      return new ItemStack(Items.EGG);
   }

   protected void doPostHurtEffects(LivingEntity entity) {
      super.doPostHurtEffects(entity);
      entity.setArrowCount(entity.getArrowCount() - 1);
   }

   public void tick() {
      super.tick();
      if (this.inGround) {
         this.discard();
      }

   }

   @Override
   public ItemStack getItem() {
	// TODO Auto-generated method stub
	return null;
   }

   @Override
   protected ItemStack getPickupItem() {
	// TODO Auto-generated method stub
	return null;
   }

//   public static CHKNBulletEntity shoot(Level world, LivingEntity entity, RandomSource source) {
//      return shoot(world, entity, source, 1.0F, (double)2.0F, 0);
//   }
//
//   public static CHKNBulletEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
//      return shoot(world, entity, source, pullingPower * 1.0F, (double)2.0F, 0);
//   }

//   public static CHKNBulletEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
//      CHKNBulletEntity entityarrow = new CHKNBulletEntity((EntityType)StupidWeaponDeluxeModEntities.CHKN_BULLET.get(), entity, world);
//      entityarrow.shoot(entity.getViewVector(1.0F).x, entity.getViewVector(1.0F).y, entity.getViewVector(1.0F).z, power * 2.0F, 0.0F);
//      entityarrow.setSilent(true);
//      entityarrow.setCritArrow(false);
//      entityarrow.setBaseDamage(damage);
//      entityarrow.setKnockback(knockback);
//      world.addFreshEntity(entityarrow);
//      world.playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.blaze.shoot")), SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F);
//      return entityarrow;
//   }
//
//   public static CHKNBulletEntity shoot(LivingEntity entity, LivingEntity target) {
//      CHKNBulletEntity entityarrow = new CHKNBulletEntity((EntityType)StupidWeaponDeluxeModEntities.CHKN_BULLET.get(), entity, entity.level());
//      double dx = target.getX() - entity.getX();
//      double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
//      double dz = target.getZ() - entity.getZ();
//      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * (double)0.2F, dz, 2.0F, 12.0F);
//      entityarrow.setSilent(true);
//      entityarrow.setBaseDamage((double)2.0F);
//      entityarrow.setKnockback(0);
//      entityarrow.setCritArrow(false);
//      entity.level().addFreshEntity(entityarrow);
//      entity.level().playSound((Player)null, entity.getX(), entity.getY(), entity.getZ(), (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(new ResourceLocation("entity.blaze.shoot")), SoundSource.PLAYERS, 1.0F, 1.0F / (RandomSource.create().nextFloat() * 0.5F + 1.0F));
//      return entityarrow;
//   }

   
}