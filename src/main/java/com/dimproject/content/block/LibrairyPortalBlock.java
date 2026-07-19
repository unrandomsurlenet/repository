package com.dimproject.content.block;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.dimproject.util.teleportation.PortalEntityHandler;
import com.dimproject.util.teleportation.PortalShape;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class LibrairyPortalBlock extends Block {

    public static final EnumProperty<Direction.Axis> AXIS =
        BlockStateProperties.HORIZONTAL_AXIS;

    // Compteur de ticks dans le portail par entité
    private static final Map<UUID, Integer> portalTimers = new HashMap<>();
    private static final int PORTAL_TIME = 80;

    public LibrairyPortalBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level.isClientSide()) return;
        if (!entity.isAlive() || entity.isPassenger()) return;
        if (entity.isOnPortalCooldown()) {
            entity.setPortalCooldown();
            portalTimers.remove(entity.getUUID());
            return;
        }

        // Incrémente le timer manuellement
        int timer = portalTimers.getOrDefault(entity.getUUID(), 0) + 1;
        portalTimers.put(entity.getUUID(), timer);

        if (timer >= PORTAL_TIME) {
            portalTimers.remove(entity.getUUID());
            entity.setPortalCooldown(); // empêche re-téléportation immédiate
            PortalEntityHandler.teleportEntity(entity);
        }
        
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState,
                                   LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        // Casse le portail si le cadre est cassé
        Direction.Axis axis = state.getValue(AXIS);
        if (dir.getAxis() != axis && !PortalShape.hasFrame(level, pos, axis)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, dir, neighborState, level, pos, neighborPos);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        // Particules custom du portail
        if (rand.nextInt(100) == 0) {
            level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS,
                0.5f, rand.nextFloat() * 0.4f + 0.8f, false);
        }
        for (int i = 0; i < 4; i++) {
            double x = pos.getX() + rand.nextDouble();
            double y = pos.getY() + rand.nextDouble();
            double z = pos.getZ() + rand.nextDouble();
            level.addParticle(ParticleTypes.PORTAL, x, y, z,
                (rand.nextDouble() - 0.5) * 0.5,
                (rand.nextDouble() - 0.5) * 0.5,
                (rand.nextDouble() - 0.5) * 0.5);
        }
    }

    
}