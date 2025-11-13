package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.other.FCDataMapUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.davigj.frame_changer.core.other.FCConstants.spelunkeryCryingPortals;

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin {
    @Inject(method = "updateShape", at = @At("HEAD"))
    private void updateObby(BlockState state, Direction dir, BlockState nextState, LevelAccessor level, BlockPos pos, BlockPos nextPos, CallbackInfoReturnable<BlockState> cir) {
        if (!level.isClientSide()) {
            if (ModList.get().isLoaded("spelunkery")) {
                if (spelunkeryCryingPortals) {
                    FCDataMapUtil.fluidSpread(state, (Level) level, pos, 0.33D);
                }
            } else if (FCConfig.COMMON.contagiousMisery.get()) {
                FCDataMapUtil.fluidSpread(state, (Level) level, pos, FCConfig.COMMON.convertChance.get());
            }
        }

    }
}
