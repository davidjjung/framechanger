package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.registry.FCBlocks;
import com.ordana.dimensional_tears.configs.CommonConfigs;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

import java.util.HashMap;
import java.util.Map;

public class FCConstants {
    public static final Map<Block, Block> CHISEL_MAP = new HashMap<>();
    public static final double dimensionalTearsCryingPortals;
    public static final boolean dimensionalTearsPortalFluid;

    public static void determineChiselMap() {
        CHISEL_MAP.put(FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.CHISELED_OBSIDIAN.get());
        CHISEL_MAP.put(FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_CHISELED_OBSIDIAN.get());
    }

    static {
        dimensionalTearsCryingPortals = ModList.get().isLoaded("dimensional_tears") ? CommonConfigs.PORTAL_DESTRUCTION_CRYING_OBSIDIAN_CHANCE.get() : 0;
        dimensionalTearsPortalFluid = ModList.get().isLoaded("dimensional_tears") ? CommonConfigs.CRYING_OBSIDIAN_DIMENSIONAL_TEARS.get() : false;
    }
}
