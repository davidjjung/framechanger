package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.registry.FCBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class FCConstants {
    public static final Map<Block, Block> OBBY_MAP = new HashMap<>();
    public static final Map<Block, Block> CHISEL_MAP = new HashMap<>();
    public static final Map<Block, Block> PORTAL_FLUID_MAP = new HashMap<>();


    public static void initializeObbyMap() {
        OBBY_MAP.put(Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN);
        OBBY_MAP.put(FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_OBSIDIAN_BRICKS.get());
        OBBY_MAP.put(FCBlocks.OBSIDIAN_PILLAR.get(), FCBlocks.CRYING_OBSIDIAN_PILLAR.get());
        OBBY_MAP.put(FCBlocks.CHISELED_OBSIDIAN.get(), FCBlocks.CRYING_CHISELED_OBSIDIAN.get());
        OBBY_MAP.put(FCBlocks.POLISHED_OBSIDIAN.get(), FCBlocks.CRYING_POLISHED_OBSIDIAN.get());
    }

    public static void determineChiselMap() {
        CHISEL_MAP.put(FCBlocks.OBSIDIAN_BRICKS.get(), FCBlocks.CHISELED_OBSIDIAN.get());
        CHISEL_MAP.put(FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.CRYING_CHISELED_OBSIDIAN.get());
    }

    // inefficient! awawawa
    public static void portalFluidMap() {
        PORTAL_FLUID_MAP.put(FCBlocks.CRYING_OBSIDIAN_BRICKS.get(), FCBlocks.OBSIDIAN_BRICKS.get());
        PORTAL_FLUID_MAP.put(FCBlocks.CRYING_OBSIDIAN_PILLAR.get(), FCBlocks.OBSIDIAN_PILLAR.get());
        PORTAL_FLUID_MAP.put(FCBlocks.CRYING_CHISELED_OBSIDIAN.get(), FCBlocks.CHISELED_OBSIDIAN.get());
        PORTAL_FLUID_MAP.put(FCBlocks.CRYING_POLISHED_OBSIDIAN.get(), FCBlocks.POLISHED_OBSIDIAN.get());
    }
}
