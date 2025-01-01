package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.other.FCBlockTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {
    @WrapOperation(method = "isPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isPortalFrame(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z"))
    private static boolean isPortalFrame(BlockState instance, BlockGetter blockGetter, BlockPos pos, Operation<Boolean> original, Level level, BlockPos blockPos, @Local BlockPos.MutableBlockPos mutable,
                             @Local boolean flag, @Local(ordinal = 1) Direction direction) {
        if (FCConfig.COMMON.portalFrameTag.get()) {
            return level.getBlockState(mutable.set(pos).move(direction)).is(FCBlockTags.PORTAL_FRAMES);
        } else {
            return original.call(instance, blockGetter, pos);
        }
    }
}
