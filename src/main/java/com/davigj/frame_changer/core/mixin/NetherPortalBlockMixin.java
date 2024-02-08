package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.other.FCConstants;
import com.ordana.spelunkery.configs.CommonConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {
    @Shadow @Final public static EnumProperty<Direction.Axis> AXIS;

    @Inject(method = "updateShape", at = @At("HEAD"))
    private void updateObby(BlockState state, Direction dir, BlockState nextState, LevelAccessor level, BlockPos pos, BlockPos nextPos, CallbackInfoReturnable<BlockState> cir) {
        if (!level.isClientSide()) {
            if (ModList.get().isLoaded("spelunkery")) {
                if (CommonConfigs.PORTAL_DESTRUCTION_CRYING_OBSIDIAN.get()) {
                    framechanger$fluidSpread(state, (Level) level, pos, 0.33D);
                }
            } else if (FCConfig.COMMON.contagiousMisery.get()) {
                framechanger$fluidSpread(state, (Level) level, pos, FCConfig.COMMON.convertChance.get());
            }
        }

    }

    @Unique
    private void framechanger$fluidSpread (BlockState state, Level level, BlockPos pos, double cryChance) {
        Direction.Axis axis2 = state.getValue(NetherPortalBlock.AXIS);
        RandomSource random = level.getRandom();
        for (Direction cryDir : Direction.values()) {
            BlockState cryState = level.getBlockState(pos.relative(cryDir));
            if (random.nextDouble() < cryChance && FCConstants.OBBY_MAP.containsKey(cryState.getBlock()) && !(new PortalShape(level, pos, axis2)).isComplete()) {
                BlockState convertedState = FCConstants.OBBY_MAP.get(cryState.getBlock()).defaultBlockState();
                if (cryState.hasProperty(AXIS)) {
                    convertedState.setValue(AXIS, cryState.getValue(AXIS));
                }
                level.setBlock(pos.relative(cryDir), convertedState, 3);
            }
        }
    }
}
