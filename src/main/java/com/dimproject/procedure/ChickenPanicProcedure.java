package com.dimproject.procedure;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ChickenPanicProcedure {
   public static boolean execute(Entity entity) {
      if (entity == null) {
         return false;
      } else {
         float var10000;
         if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            var10000 = _livEnt.getMaxHealth();
         } else {
            var10000 = -1.0F;
         }

         float var10001;
         if (entity instanceof LivingEntity) {
            LivingEntity _livEnt = (LivingEntity)entity;
            var10001 = _livEnt.getMaxHealth();
         } else {
            var10001 = -1.0F;
         }

         return var10000 < var10001 / 2.0F;
      }
   }
}
