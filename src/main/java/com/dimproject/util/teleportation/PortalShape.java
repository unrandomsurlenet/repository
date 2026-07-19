package com.dimproject.util.teleportation;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import com.dimproject.content.block.LibrairyPortalBlock;
import com.dimproject.registries.DimProjectBlocks;

public class PortalShape {

    public static final int MIN_WIDTH  = 2;
    public static final int MAX_WIDTH  = 21;
    public static final int MIN_HEIGHT = 3;
    public static final int MAX_HEIGHT = 21;

    private final LevelAccessor level;
    private final Direction.Axis axis;
    private final Direction rightDir;

    private int numPortalBlocks = 0;
    private BlockPos bottomLeft = null;
    private int width  = 0;
    private int height = 0;

    public PortalShape(LevelAccessor level, BlockPos pos, Direction.Axis axis) {
        this.level    = level;
        this.axis     = axis;
        this.rightDir = axis == Direction.Axis.X ? Direction.EAST : Direction.SOUTH;
        this.bottomLeft = calculateBottomLeft(pos);
        if (bottomLeft != null) {
            this.width = calculateWidth();
            if (width > 0) this.height = calculateHeight();
        }
    }

    private BlockPos calculateBottomLeft(BlockPos pos) {
        // Descend jusqu'au bas du portail
        int minY = Math.max(0, pos.getY() - MAX_HEIGHT);
        while (pos.getY() > minY && isEmptyOrPortal(level.getBlockState(pos.below()))) {
            pos = pos.below();
        }

        // Va vers la gauche jusqu'à trouver le cadre
        Direction opposite = rightDir.getOpposite();
        int j = getDistanceTo(pos, opposite) - 1;

        BlockPos candidate = pos.relative(opposite, j);
        BlockState frameCheck = level.getBlockState(pos.relative(opposite, j + 1));

        // Corrigé : retourne la position si le cadre est valide
        return isValidBlock(frameCheck) ? candidate : null;
    }

    private int calculateWidth() {
        int dist = getDistanceTo(bottomLeft, rightDir);
        return (dist >= MIN_WIDTH && dist <= MAX_WIDTH) ? dist : 0;
    }

    private int calculateHeight() {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int h = getDistanceUpTo(mutable);
        return (h >= MIN_HEIGHT && h <= MAX_HEIGHT) ? h : 0;
    }

    private int getDistanceTo(BlockPos pos, Direction dir) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int i = 0; i <= MAX_WIDTH; i++) {
            mutable.set(pos).move(dir, i);
            BlockState state = level.getBlockState(mutable);
            if (!isEmptyOrPortal(state)) {
                if (isValidBlock(state)) return i;
                return 0;
            }
        }
        return 0;
    }

    private int getDistanceUpTo(BlockPos.MutableBlockPos mutable) {
        for (int i = 0; i <= MAX_HEIGHT; i++) {
            mutable.set(bottomLeft).move(Direction.UP, i);

            // Vérifie chaque bloc de la rangée
            for (int w = 0; w < width; w++) {
                BlockPos check = mutable.relative(rightDir, w);
                BlockState state = level.getBlockState(check);
                if (!isEmptyOrPortal(state)) {
                    // Pas un cadre valide -> portail invalide
                    if (!isValidBlock(state)) return 0;
                    // Cadre au milieu -> invalide
                    if (w != 0 && w != width - 1) return 0;
                }
            }

            // Vérifie si la rangée du dessus est le plafond (tous des cadres)
            BlockPos.MutableBlockPos topCheck = new BlockPos.MutableBlockPos();
            topCheck.set(bottomLeft).move(Direction.UP, i + 1);
            boolean isTop = true;
            for (int w = 0; w < width; w++) {
                if (!isValidBlock(level.getBlockState(topCheck.relative(rightDir, w)))) {
                    isTop = false;
                    break;
                }
            }
            if (isTop) return i + 1;
        }
        return 0;
    }

    private boolean isEmptyOrPortal(BlockState state) {
        return state.isAir() || state.is(DimProjectBlocks.LIBRAIRY_PORTAL_BLOCK.get());
    }

    private boolean isValidBlock(BlockState state) {
        return state.is(DimProjectBlocks.PORTAL_FRAME.get());
    }

    public boolean isValid() {
        return bottomLeft != null && width >= MIN_WIDTH && height >= MIN_HEIGHT;
    }

    public void createPortalBlocks() {
        BlockState portalState = DimProjectBlocks.LIBRAIRY_PORTAL_BLOCK.get()
            .defaultBlockState()
            .setValue(LibrairyPortalBlock.AXIS, axis);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                mutable.set(bottomLeft).move(Direction.UP, h).move(rightDir, w);
                level.setBlock(mutable, portalState, 18);
                System.out.println("Portal block placed at: " + mutable);
            }
        }
    }

    public static boolean hasFrame(LevelAccessor level, BlockPos pos, Direction.Axis axis) {
        PortalShape shape = new PortalShape(level, pos, axis);
        return shape.isValid();
    }

    public boolean isComplete() {
        return isValid() && numPortalBlocks >= width * height;
    }

    public Direction.Axis getAxis() { return axis; }
    public BlockPos getBottomLeft() { return bottomLeft; }
    public int getWidth()  { return width; }
    public int getHeight() { return height; }
}