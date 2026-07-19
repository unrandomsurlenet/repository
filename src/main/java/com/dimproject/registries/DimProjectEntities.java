package com.dimproject.registries;

import com.dimproject.DimProjectMod;
import com.dimproject.client.entity.model.ChickenRooverModel;
import com.dimproject.content.entity.librairy.ChickenDroneEntity;
import com.dimproject.content.entity.librairy.ChickenEngineerEntity;
import com.dimproject.content.entity.librairy.ChickenRooverEntity;
import com.dimproject.content.entity.librairy.ChickenWorkerEntity;
import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;
import com.dimproject.content.entity.tool.SphereEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class DimProjectEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, DimProjectMod.MODID);

    public static final RegistryObject<EntityType<ThrownBookEntity>> THROWN_BOOK =
    		ENTITY_TYPES.register("thrown_book",
                () -> EntityType.Builder.<ThrownBookEntity>of(ThrownBookEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("thrown_book")
            );
    public static final RegistryObject<EntityType<BatlingEntity>> BATLING =
    		ENTITY_TYPES.register("batling",
                () -> EntityType.Builder.<BatlingEntity>of(BatlingEntity::new, MobCategory.MONSTER)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(8)
                    .updateInterval(4)
                    .build("batling")
            );
    public static final RegistryObject<EntityType<ChickenDroneEntity>> CHICKEN_DRONE =
    		ENTITY_TYPES.register("chicken_drone",
                () -> EntityType.Builder.<ChickenDroneEntity>of(ChickenDroneEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(8)
                    .updateInterval(4)
                    .build("chicken_drone")
            );
    public static final RegistryObject<EntityType<ChickenWorkerEntity>> CHICKEN_WORKER =
    		ENTITY_TYPES.register("chicken_worker",
                () -> EntityType.Builder.<ChickenWorkerEntity>of(ChickenWorkerEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 3.0F)
                    .clientTrackingRange(8)
                    .updateInterval(4)
                    .build("chicken_worker")
            );
    public static final RegistryObject<EntityType<ChickenEngineerEntity>> CHICKEN_ENGINEER =
    		ENTITY_TYPES.register("chicken_engineer",
                () -> EntityType.Builder.<ChickenEngineerEntity>of(ChickenEngineerEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 3.0F)
                    .clientTrackingRange(8)
                    .updateInterval(4)
                    .build("chicken_engineer")
            );
    public static final RegistryObject<EntityType<ChickenRooverEntity>> CHICKEN_ROOVER =
    		ENTITY_TYPES.register("chicken_roover",
                () -> EntityType.Builder.<ChickenRooverEntity>of(ChickenRooverEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 3.0F)
                    .clientTrackingRange(8)
                    .updateInterval(4)
                    .build("chicken_roover")
            );
    public static final RegistryObject<EntityType<SphereEntity>> SPHERE_ENTITY = 
    		ENTITY_TYPES.register("sphere_entity", 
    		() -> EntityType.Builder.<SphereEntity>of(SphereEntity::new, MobCategory.MISC)
            .sized(1f, 1f)        // pas de hitbox
            .clientTrackingRange(64)
            .updateInterval(Integer.MAX_VALUE) // jamais besoin de sync de position
            .build("sphere_entity")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
    @SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(BATLING.get(), BatlingEntity.registerAttributes().build());
		event.put(CHICKEN_WORKER.get(), ChickenWorkerEntity.registerAttributes().build());
		event.put(CHICKEN_DRONE.get(), ChickenDroneEntity.registerAttributes().build());
		event.put(CHICKEN_ENGINEER.get(), ChickenEngineerEntity.registerAttributes().build());
		event.put(CHICKEN_ROOVER.get(), ChickenRooverEntity.registerAttributes().build());

    }
}