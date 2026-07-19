package com.dimproject.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.dimproject.procedure.InfiniteVoidProcedure;
import com.dimproject.registries.DimProjectItems;

import org.spongepowered.asm.mixin.injection.At;

import net.mcreator.terramity.init.TerramityModMobEffects;
import net.mcreator.terramity.procedures.ActiveAbilityOnKeyPressedProcedure;
import net.mcreator.terramity.procedures.PrismaticRingMathProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(value = ActiveAbilityOnKeyPressedProcedure.class, remap = false)
public class ActiveAbilityMixin {

    @Inject(method = "execute", at = @At("TAIL"), remap = false)
    private static void onExecute(LevelAccessor world, double x, double y, double z, Entity entity, CallbackInfo ci) {
    	System.out.print("ici");

    	if (entity instanceof LivingEntity) {
            LivingEntity lv = (LivingEntity)entity;
            if (CuriosApi.getCuriosHelper().findEquippedCurio((Item)DimProjectItems.INFINITE_VOID.get(), lv).isPresent()) {
               if (entity instanceof LivingEntity) {
                  LivingEntity _entity = (LivingEntity)entity;
                  if (!_entity.level().isClientSide()) {
                     _entity.addEffect(new MobEffectInstance((MobEffect)TerramityModMobEffects.ABILITY_COOLDOWN.get(), (int)((double)200.0F * PrismaticRingMathProcedure.execute(entity)), 180));
                  }
               }

               InfiniteVoidProcedure.execute(world, x, y, z, entity);
            }
         }
    }
}