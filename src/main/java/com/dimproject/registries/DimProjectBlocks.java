package com.dimproject.registries;

import java.util.function.Supplier;

import com.dimproject.DimProjectMod;
import com.dimproject.content.block.ArcaneLight;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DimProjectBlocks {
	public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DimProjectMod.MODID);

    public static final RegistryObject<Block> ENCHANTED_BOOKSHELF = registerBlock("enchanted_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> SOURCE_BOOKSHELF = registerBlock("source_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> SPELL_BOOKSHELF = registerBlock("spell_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> ARCANE_BOOKSHELF = registerBlock("arcane_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> SAGE_BOOKSHELF = registerBlock("sage_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> MAHOU_BOOKSHELF = registerBlock("mahou_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> ARCANE_LIBRAIRY_BRICKS = registerBlock("arcane_librairy_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    
    public static final RegistryObject<Block> ANGELICA_GRASS = registerBlock("angelica_grass",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    
    public static final RegistryObject<Block> ARCANE_LIGHT = registerBlock("arcane_light",
    		() -> new ArcaneLight(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(0.5f, 0.3f).noOcclusion()));


    
    
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

	 private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
	        return DimProjectItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	    }


    public static void register(IEventBus eventBus) {
    	BLOCKS.register(eventBus);
    }
}