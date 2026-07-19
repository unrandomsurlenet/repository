package com.dimproject.registries;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class DimProjectRarities {
   public static final Rarity VOID;
   public static final Rarity STRANGE;

   static {
      VOID = Rarity.create("calamitous", ChatFormatting.BLACK);
      STRANGE = Rarity.create("strange", ChatFormatting.LIGHT_PURPLE);
   }
}