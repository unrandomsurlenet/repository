package com.dimproject.registries


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Function;

public class DimProjectItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DimProjectMod.MOD_ID);

    public static final RegistryObject<Item> ALEXANDRITE = registerItem("alexandrite",
            Item::new);
     
          

    public static RegistryObject<Item> registerItem(String name, Function<Item.Properties, Item> function) {
        return ModItems.ITEMS.register(name, () -> function.apply(new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
