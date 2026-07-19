package com.dimproject.procedure;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;

public class ChickenejectionProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof ServerLevel _level) {
         Entity entityToSpawn = EntityType.CHICKEN.spawn(_level, BlockPos.containing(x, y + (double)1.0F, z), MobSpawnType.MOB_SUMMONED);
         if (entityToSpawn != null) {
         }
      }

   }
}