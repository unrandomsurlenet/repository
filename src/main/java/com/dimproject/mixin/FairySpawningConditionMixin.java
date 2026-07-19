package com.dimproject.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.mcreator.terramity.procedures.FairySpawningConditionProcedure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.dimproject.DimProjectMod;

@Mixin(value = FairySpawningConditionProcedure.class, remap = false)
public class FairySpawningConditionMixin {

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onExecute(LevelAccessor world, double x, double y, double z ,CallbackInfoReturnable<Boolean> cir) {
    		if (world instanceof Level level) {
                if (level.dimension().equals(DimProjectMod.LIBRAIRY)) {
                    boolean notWater = !level.getBiome(BlockPos.containing(x, y, z))
                        .is(TagKey.create(Registries.BIOME, new ResourceLocation("forge:is_water")));
                    cir.setReturnValue(notWater);
                }
            }
}	
}