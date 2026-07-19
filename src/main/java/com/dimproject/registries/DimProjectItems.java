package com.dimproject.registries;





import com.dimproject.DimProjectMod;
import com.dimproject.content.item.SkulrexItem;
import com.dimproject.content.item.curios.FocusReticleItem;
import com.dimproject.content.item.curios.InfiniteVoidItem;
import com.dimproject.content.tiers.ModTiers;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectItems {
	public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DimProjectMod.MODID);

    public static final RegistryObject<Item> SKULREX = ITEMS.register("skulrex",
            () -> new SkulrexItem(new Item.Properties()));
    
    public static final RegistryObject<Item> FOCUS_RETICLE = ITEMS.register("focus_reticle",
            () -> new FocusReticleItem(new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> INFINITE_VOID = ITEMS.register("infinite_void",
            () -> new InfiniteVoidItem(new Item.Properties().rarity(DimProjectRarities.VOID)));
    public static final RegistryObject<Item> BOOK = ITEMS.register("book",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EVANESCENCE = ITEMS.register("evanescence",
    	    () -> new com.dimproject.content.item.Evanescence(
    	        ModTiers.MON_MATERIAU,
    	        7,      // dégâts supplémentaires
    	        -2.8F,  // vitesse d'attaque (valeur vanilla épée)
    	        new Item.Properties()
    	    )
    	);
    public static final RegistryObject<Item> NIGHT_TERROR = ITEMS.register("night_terror",
    	    () -> new com.dimproject.content.item.NightTerror(
    	        ModTiers.MON_MATERIAU,
    	        7,      // dégâts supplémentaires
    	        -2.8F,  // vitesse d'attaque (valeur vanilla épée)
    	        new Item.Properties()
    	    )
    	);
    
    public static final RegistryObject<Item> SPACE_DIVIDER = ITEMS.register("space_divider",
    	    () -> new com.dimproject.content.item.SpaceDivider(
    	        ModTiers.MON_MATERIAU,
    	        7,      // dégâts supplémentaires
    	        -2.8F,  // vitesse d'attaque (valeur vanilla épée)
    	        new Item.Properties()
    	    )
    	);
    public static final RegistryObject<Item> LUCRECIA = ITEMS.register("lucrecia",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLOOD_TOME = ITEMS.register("blood_tome",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARCANE_TOME = ITEMS.register("arcane_tome",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ETHEREAL_TOME = ITEMS.register("ethereal_tome",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIVINE_TOME = ITEMS.register("divine_tome",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARCANE_ESSENCE = ITEMS.register("arcane_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PULSATING_MAGIC_ORB = ITEMS.register("pulsating_magic_orb",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METANOVA_INGOT = ITEMS.register("metanova_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_METANOVA = ITEMS.register("raw_metanova",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WARDBREAKER_RING = ITEMS.register("wardbreaker_ring",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ARCANE_LIBRAIRY_REALMSTONE = ITEMS.register("arcane_librairy_realmstone",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}