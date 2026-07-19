package com.dimproject.procedure;

import com.dimproject.content.entity.tool.SphereEntity;
import com.dimproject.registries.DimProjectEntities;
import com.dimproject.registries.DimProjectSounds;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class InfiniteVoidProcedure {
	   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
	        Level level = entity.level();
	        SphereEntity domain = new SphereEntity(DimProjectEntities.SPHERE_ENTITY.get(), level);
	        domain.setOwner(entity);
	        domain.moveTo(x, y , z, 0f, 0f);
            level.addFreshEntity(domain);
            level.playLocalSound(entity.blockPosition(), DimProjectSounds.INFINITE_VOID.get(), SoundSource.PLAYERS, 1.0f, 1.0f, false);

	   }
	}