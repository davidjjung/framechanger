package com.davigj.frame_changer.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CryingWallBlock extends WallBlock {
    public CryingWallBlock(Properties p_57964_) {
        super(p_57964_);
    }

    public void animateTick(BlockState p_221055_, Level p_221056_, BlockPos p_221057_, RandomSource p_221058_) {
        if (p_221058_.nextInt(5) == 0) {
            // Ouagh close enough
            Direction direction = Direction.getRandom(p_221058_);
            if (direction != Direction.UP) {
                BlockPos blockpos = p_221057_.relative(direction);
                BlockState blockstate = p_221056_.getBlockState(blockpos);
                if (!p_221055_.canOcclude() || !blockstate.isFaceSturdy(p_221056_, blockpos, direction.getOpposite())) {
                    double d0 = direction.getStepX() == 0 ? p_221058_.nextDouble() : 0.5D + (double) direction.getStepX() * 0.6D;
                    double d1 = direction.getStepY() == 0 ? p_221058_.nextDouble() : 0.5D + (double) direction.getStepY() * 0.6D;
                    double d2 = direction.getStepZ() == 0 ? p_221058_.nextDouble() : 0.5D + (double) direction.getStepZ() * 0.6D;

                    VoxelShape shape = blockstate.getCollisionShape(p_221056_, blockpos);
                    double offsetX = shape.max(Direction.Axis.X) - shape.min(Direction.Axis.X);
                    double offsetY = shape.max(Direction.Axis.Y) - shape.min(Direction.Axis.Y);
                    double offsetZ = shape.max(Direction.Axis.Z) - shape.min(Direction.Axis.Z);

                    p_221056_.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR,
                            (double) p_221057_.getX() + d0 - offsetX + 0.35,
                            (double) p_221057_.getY() + d1 - offsetY + 1.35,
                            (double) p_221057_.getZ() + d2 - offsetZ + 0.35,
                            0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

}
