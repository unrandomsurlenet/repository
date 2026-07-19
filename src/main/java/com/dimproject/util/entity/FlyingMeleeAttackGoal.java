package com.dimproject.util.entity;

import java.util.EnumSet;

import com.dimproject.content.entity.pet.BatlingEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class FlyingMeleeAttackGoal extends Goal {

    private final PathfinderMob mob;
    private final double speedModifier;
    private int ticksUntilNextAttack = 0;
    private static final int ATTACK_INTERVAL = 20;

    public FlyingMeleeAttackGoal(PathfinderMob mob, double speed) {
        this.mob = mob;
        this.speedModifier = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return mob.getTarget() != null && mob.getTarget().isAlive();
    }

    @Override
    public boolean canContinueToUse() {
        return canUse();
    }

    @Override
    public void start() {
        ticksUntilNextAttack = 0;
    }

    @Override
    public void stop() {
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity target = mob.getTarget();
        if (target == null) return;

        // Position cible : à hauteur des yeux de la cible, légèrement au dessus
        double targetX = target.getX();
        double targetY = target.getY() + target.getBbHeight() * 0.5;
        double targetZ = target.getZ();

        // Déplacement direct via MoveControl — bypasse le pathfinding sol
        mob.getMoveControl().setWantedPosition(targetX, targetY, targetZ, speedModifier);
        mob.getLookControl().setLookAt(target, 30f, 30f);

        double dist = mob.distanceTo(target);
        ticksUntilNextAttack--;

        if (dist <= mob.getBbWidth() + 1.5 && ticksUntilNextAttack <= 0) {
            ticksUntilNextAttack = ATTACK_INTERVAL;
            mob.doHurtTarget(target);
        }
    }
}