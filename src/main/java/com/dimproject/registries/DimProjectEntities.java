package com.dimproject.registries;

import com.dimproject.DimProjectMod;
import com.dimproject.content.entity.pet.BatlingEntity;
import com.dimproject.content.entity.projectile.ThrownBookEntity;

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
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("batling")
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
    @SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(BATLING.get(), BatlingEntity.registerAttributes().build());
		
    }
}