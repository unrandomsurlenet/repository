package com.dimproject.registries;

import java.util.function.Supplier;

import com.dimproject.DimProjectMod;
import com.dimproject.content.block.ArcaneGrass;
import com.dimproject.content.block.ArcaneLight;
import com.dimproject.content.block.ArcanePlant;
import com.dimproject.content.block.LibrairyLauncherBlock;
import com.dimproject.content.block.LibrairyPortalBlock;
import com.dimproject.content.block.PaperBlock;
import com.dimproject.content.block.PortalFrameBlock;
import com.dimproject.content.block.PulsatingBookshelf;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TallGrassBlock;
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
    
    public static final RegistryObject<Block> DIVINE_BOOKSHELF = registerBlock("divine_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> SAGE_BOOKSHELF = registerBlock("sage_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> PULSATING_SAGE_BOOKSHELF = registerBlock("pulsating_sage_bookshelf",
            () -> new PulsatingBookshelf(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));

    public static final RegistryObject<Block> MAHOU_BOOKSHELF = registerBlock("mahou_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> LIBRAIRY_BRICKS = registerBlock("librairy_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    
    public static final RegistryObject<Block> GILDED_LIBRAIRY_BRICKS = registerBlock("gilded_librairy_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    
    public static final RegistryObject<Block> LIBAIRY_BANNER = registerBlock("librairy_banner",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BLACK_BANNER)));
    
    public static final RegistryObject<Block> LIBRAIRY_LAUNCHER = registerBlock("librairy_launcher",
            () -> new LibrairyLauncherBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    
    public static final RegistryObject<Block> ANGELICA_GRASS = registerBlock("angelica_grass",
            () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    
    public static final RegistryObject<Block> SAGE_LOG = registerBlock("sage_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    
    public static final RegistryObject<Block> SAGE_SOIL = registerBlock("sage_soil",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> SAGE_GRASS = registerBlock("sage_grass",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    
    public static final RegistryObject<Block> METANOVA_ORE = registerBlock("metanova_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    
    public static final RegistryObject<Block> LIBRAIRY_TILE = registerBlock("librairy_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES)));
    
    public static final RegistryObject<Block> BLOOD_BOOKSHELF = registerBlock("blood_bookshelf",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF)));
    
    public static final RegistryObject<Block> SAGE_LEAVES = registerBlock("sage_leaves",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()));
    
    public static final RegistryObject<Block> ARCANE_ROOTS = registerBlock("arcane_roots",
            () -> new ArcanePlant(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion()));
    
    public static final RegistryObject<Block> LIT_ARCANE_ROOTS = registerBlock("lit_arcane_roots",
            () -> new ArcanePlant(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion())); 
    
    public static final RegistryObject<Block> ARCANE_GRASS = registerBlock("arcane_grass",
            () -> new ArcaneGrass(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion()));
    
    public static final RegistryObject<Block> LIT_ARCANE_GRASS = registerBlock("lit_arcane_grass",
            () -> new ArcaneGrass(BlockBehaviour.Properties.copy(Blocks.TALL_GRASS).noOcclusion())); 
    
    public static final RegistryObject<Block> SAGE_PLANKS = registerBlock("sage_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    
    public static final RegistryObject<Block> LIBRAIRY_BRICKS_STAIRS = registerBlock("librairy_bricks_stairs",
            () -> new StairBlock(DimProjectBlocks.LIBRAIRY_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.BRICKS)));
    
    public static final RegistryObject<Block> SAGE_PLANKS_STAIRS = registerBlock("sage_planks_stairs",
            () -> new StairBlock(DimProjectBlocks.SAGE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    
    public static final RegistryObject<Block> ARCANE_LIGHT = registerBlock("arcane_light",
    		() -> new ArcaneLight(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).strength(0.5f, 0.3f).noOcclusion()));
    
    public static final RegistryObject<Block> PAPER = registerBlock("paper",
    		() -> new PaperBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES).strength(0.5f, 0.3f).noOcclusion()));



    //Portal
    public static final RegistryObject<Block> LIBRAIRY_PORTAL_BLOCK = registerBlock("librairy_portal_block",
            () -> new LibrairyPortalBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_PORTAL)));
    
    public static final RegistryObject<Block> PORTAL_FRAME = registerBlock("portal_frame",
            () -> new PortalFrameBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    
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