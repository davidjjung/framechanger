package com.davigj.frame_changer.core.mixin;

import com.davigj.frame_changer.core.FCConfig;
import com.davigj.frame_changer.core.FrameChanger;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalForcer;
import net.neoforged.fml.ModList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Supplier;

@Mixin(PortalForcer.class)
public class PortalForcerMixin {
    // Bad code, but the best I can make it
    @Unique
    private final static Logger fc$LOGGER = LogManager.getLogger(FrameChanger.MOD_ID);

    @Mutable
    @Final
    @Shadow
    protected final ServerLevel level;

    public PortalForcerMixin(ServerLevel level) {
        this.level = level;
    }

    @WrapOperation(method = "createPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean customExit(ServerLevel instance, BlockPos pos, BlockState state, Operation<Boolean> original, @Local(ordinal = 3) int k3) {
        BlockState blockState = state;
        if (FCConfig.COMMON.customExitPortals.get()) {
            blockState = k3 < 0 ? fc$getCompatBlock(FCConfig.COMMON.exitPortalFrame.get()).get().defaultBlockState() : Blocks.AIR.defaultBlockState();
        }
        return original.call(instance, pos, blockState);
    }

    @WrapOperation(method = "createPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", ordinal = 0))
    private boolean customExitPt2(ServerLevel instance, BlockPos pos, BlockState state, int i, Operation<Boolean> original) {
        BlockState blockState = state;
        if (FCConfig.COMMON.customExitPortals.get()) {
            blockState = fc$getCompatBlock(FCConfig.COMMON.exitPortalFrame.get()).get().defaultBlockState();
        }
        return original.call(instance, pos, blockState, i);
    }

    @Unique
    private static Supplier<Block> fc$getCompatBlock(String fullId) {
        String[] parts = fullId.split(":");
        if (parts.length != 2) {
            fc$LOGGER.warn("Improperly formatted config for generated portal frames. String should be formatted 'modid:blockID'. Defaulting to minecraft:obsidian");
            return () -> Blocks.OBSIDIAN;
        }
        String modid = parts[0];
        String blockID = parts[1];
        if (!ModList.get().isLoaded(modid) && modid != null) {
            fc$LOGGER.warn("Mod '" + modid + "' not loaded, invalid configured exit portal frame blockID. String should be formatted 'modID:blockID'. Defaulting to minecraft:obsidian");
            return () -> Blocks.OBSIDIAN;
        }
        assert modid != null;
        ResourceLocation block = ResourceLocation.fromNamespaceAndPath(modid, blockID);
        if (BuiltInRegistries.BLOCK.get(block) == Blocks.AIR) {
            fc$LOGGER.warn("Invalid configured exit portal frame blockID. String should be formatted 'modID:blockID'. Defaulting to minecraft:obsidian");
            return () -> Blocks.OBSIDIAN;
        }
        return (ModList.get().isLoaded(modid) ? () -> BuiltInRegistries.BLOCK.get(block) : () -> null);
    }

}
