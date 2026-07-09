package com.dimproject.network.packet;

import java.util.function.Supplier;
import com.dimproject.content.item.Evanescence;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class ServerboundBetterCombatAttackStartListenerPacket {
   private final ItemStack stack;
   private final int combo;

   public ServerboundBetterCombatAttackStartListenerPacket(ItemStack stack, int combo) {
      this.stack = stack;
      this.combo = combo;
   }

   public ServerboundBetterCombatAttackStartListenerPacket(FriendlyByteBuf buf) {
      this.stack = buf.readItem();
      this.combo = buf.readInt();
   }

   public void encode(FriendlyByteBuf buf) {
      buf.writeItem(this.stack);
      buf.writeInt(this.combo);
   }

   public static void handle(ServerboundBetterCombatAttackStartListenerPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
      NetworkEvent.Context context = (NetworkEvent.Context)contextSupplier.get();
      context.enqueueWork(() -> {
         ServerPlayer player = context.getSender();
         if (player != null) {
            ServerLevel serverLevel = player.serverLevel();
            ItemStack stack = packet.stack;
            Item item = stack.getItem();
            int combo = packet.combo;
            if (item instanceof Evanescence) {
            	Evanescence Evanescence = (Evanescence)item;
            	Evanescence.spawnBookProjectiles(player);
//            } else if (item instanceof SeventhStarSword) {
//               SeventhStarSword seventhStarSword = (SeventhStarSword)item;
//               seventhStarSword.shootTriStar(player);
//            } else if (item instanceof MarianasTrenchSword) {
//               MarianasTrenchSword marianasTrenchSword = (MarianasTrenchSword)item;
//               marianasTrenchSword.performMarianasTrenchAttack(stack, player);
//            } else if (item instanceof ChallengerDeepSword) {
//               ChallengerDeepSword challengerDeepSword = (ChallengerDeepSword)item;
//               challengerDeepSword.performChallengerDeepAttack(stack, player);
//            } else if (item instanceof SupernovaSword) {
//               SupernovaSword supernovaSword = (SupernovaSword)item;
//               if (combo == 2) {
//                  supernovaSword.triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "controller", "stab");
//               } else {
//                  supernovaSword.triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "controller", "swing");
//               }
//            } else if (item instanceof AtomicJudgementHammer) {
//               AtomicJudgementHammer atomicJudgementHammer = (AtomicJudgementHammer)item;
//               atomicJudgementHammer.triggerAnim(player, GeoItem.getOrAssignId(stack, serverLevel), "controller", "attack");
//            } else if (item instanceof BrutalityThrowingItem) {
//               BrutalityThrowingItem throwingItem = (BrutalityThrowingItem)item;
//               throwingItem.handleCooldownAndSound(player, stack);
//            } else if (item instanceof CrimsonScissorBlade) {
//               CrimsonScissorBlade crimsonScissorBlade = (CrimsonScissorBlade)item;
//               crimsonScissorBlade.performBloodSlash(player);
//            } else if (item instanceof ShadowflameScissorBlade) {
//               ShadowflameScissorBlade shadowflameScissorBlade = (ShadowflameScissorBlade)item;
//               shadowflameScissorBlade.performShadowflameSlash(player, stack);
//            } else if (item instanceof RhongomyniadSpear) {
//               RhongomyniadSpear rhongomyniadSpear = (RhongomyniadSpear)item;
//               rhongomyniadSpear.performRayAttack(player);
            }

         }
      });
      context.setPacketHandled(true);
   }
}