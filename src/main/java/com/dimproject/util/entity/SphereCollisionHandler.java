package com.dimproject.util.entity;

import java.util.List;

import com.dimproject.DimProjectMod;
import com.dimproject.content.entity.tool.SphereEntity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DimProjectMod.MODID)
public class SphereCollisionHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (event.player.level().isClientSide()) return;
        if (!(event.player instanceof ServerPlayer serverPlayer)) return;

        checkAndCorrect(serverPlayer, serverPlayer.serverLevel());
        
        // Gère aussi les mobs au même moment
        serverPlayer.serverLevel().getEntitiesOfClass(
            LivingEntity.class,
            AABB.ofSize(serverPlayer.position(), 64, 64, 64),
            e -> !(e instanceof Player) // exclut les joueurs déjà gérés
        ).forEach(mob -> checkAndCorrectMob(mob, serverPlayer.serverLevel()));
    }

    private static void checkAndCorrect(ServerPlayer player, ServerLevel level) {
        SphereEntity sphere = getSphere(level, player.position());
        if (sphere == null) return;

        Vec3 center = sphere.position();
        double dist = player.position().distanceTo(center);
        float radius = 30f;

        if (dist > radius - 0.5) {
            Vec3 dir = player.position().subtract(center).normalize();
            Vec3 corrected = center.add(dir.scale(radius - 0.5));
            player.connection.teleport(
                corrected.x, corrected.y, corrected.z,
                player.getYRot(), player.getXRot()
            );
            player.setDeltaMovement(dir.scale(-0.5));
        }
    }

    private static void checkAndCorrectMob(LivingEntity mob, ServerLevel level) {
        SphereEntity sphere = getSphere(level, mob.position());
        if (sphere == null) return;

        Vec3 center = sphere.position();
        double dist = mob.position().distanceTo(center);
        float radius = 30f;

        if (dist > radius - 0.5) {
            Vec3 dir = mob.position().subtract(center).normalize();
            Vec3 corrected = center.add(dir.scale(radius - 0.5));
            mob.setPos(corrected.x, corrected.y, corrected.z);
            mob.setDeltaMovement(dir.scale(-0.5));
        }
    }

    private static SphereEntity getSphere(ServerLevel level, Vec3 pos) {
        List<SphereEntity> spheres = level.getEntitiesOfClass(
            SphereEntity.class,
            AABB.ofSize(pos, 64, 64, 64)
        );
        return spheres.isEmpty() ? null : spheres.get(0);
    }
}