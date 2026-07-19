package com.dimproject.registries;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@EventBusSubscriber(modid = "dimproject", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimProjectSpawnPlacements {

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent  event) {
        //event.register(DimProjectEntities.CHICKEN_WORKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

   
}
