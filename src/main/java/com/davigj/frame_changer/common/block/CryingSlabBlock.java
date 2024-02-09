package com.davigj.frame_changer.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.NotNull;

public class CryingSlabBlock extends SlabBlock {
    public CryingSlabBlock(Properties p_56359_) {
        super(p_56359_);
    }

    public void animateTick(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, RandomSource random) {
        if (random.nextInt(5) == 0) {
            Direction direction = Direction.getRandom(random);
            if (direction != Direction.UP) {
                BlockPos blockpos = pos.relative(direction);
                BlockState blockstate = world.getBlockState(blockpos);
                if (!state.canOcclude() || !blockstate.isFaceSturdy(world, blockpos, direction.getOpposite())) {
                    double yOffset = state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM ? 0.25D : 0.75D;
                    double xOffset = direction.getStepX() == 0 ? random.nextDouble() : 0.5D + (double) direction.getStepX() * 0.5D;
                    double zOffset = direction.getStepZ() == 0 ? random.nextDouble() : 0.5D + (double) direction.getStepZ() * 0.5D;
                    world.addParticle(ParticleTypes.DRIPPING_OBSIDIAN_TEAR, (double) pos.getX() + xOffset, (double) pos.getY() + yOffset, (double) pos.getZ() + zOffset, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

}
