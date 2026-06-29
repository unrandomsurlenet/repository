//package com.dimproject.registries;
//
//import com.dimproject.DimProjectMod;
//
//import net.minecraft.core.registries.Registries;
//import net.minecraft.world.level.levelgen.structure.Structure;
//import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.RegistryObject;
//
//// Dans ta classe principale ou un registre dédié
//public class DimProjectStructures {
//    public static final DeferredRegister<Structure> STRUCTURES =
//        DeferredRegister.create(Registries.STRUCTURE, DimProjectMod.MODID);
//
//    public static final RegistryObject<Structure> MA_STRUCTURE =
//        STRUCTURES.register("ma_structure",
//            () -> new JigsawStructure(/* config */));
//}