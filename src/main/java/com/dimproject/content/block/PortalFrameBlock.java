package com.dimproject.content.block;

import com.dimproject.registries.DimProjectItems;
import com.dimproject.util.teleportation.PortalShape;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;

public class PortalFrameBlock extends Block {

    public PortalFrameBlock(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                  Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

    	if (stack.is(DimProjectItems.ARCANE_LIBRAIRY_REALMSTONE.get())) {
            if (!level.isClientSide()) {
                // Cherche dans les blocs adjacents au cadre cliqué
                boolean spawned = false;
                for (Direction dir : Direction.values()) {
                    BlockPos adjacent = pos.relative(dir);
                    if (level.getBlockState(adjacent).isAir()) {
                        spawned = trySpawnPortal(level, adjacent);
                        if (spawned) break;
                    }
                }
                if (!spawned) trySpawnPortal(level, pos);

               
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }
        return InteractionResult.PASS;
    }

    public static boolean trySpawnPortal(Level level, BlockPos pos) {
        for (Direction.Axis axis : new Direction.Axis[]{Direction.Axis.X, Direction.Axis.Z}) {
            PortalShape shape = new PortalShape(level, pos, axis);
            if (shape.isValid()) {
                shape.createPortalBlocks();
                return true;
            }
        }
        return false;
    }
}