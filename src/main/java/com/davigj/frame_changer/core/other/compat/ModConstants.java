package com.davigj.frame_changer.core.other.compat;

import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.ModList;

import java.util.HashMap;
import java.util.Map;

public class ModConstants {
    public static final boolean DIMENSIONAL_TEARS = ModList.get().isLoaded("dimensional_tears");
    public static final Map<Block, Block> CHISEL_MAP = new HashMap<>();

    public static void determineChiselMap() {
        CHISEL_MAP.put(FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.CHISELED_OBSIDIAN.get());
        CHISEL_MAP.put(FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_CHISELED_OBSIDIAN.get());
    }
}
