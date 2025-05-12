package com.davigj.frame_changer.core.registry;

import com.davigj.frame_changer.common.block.CryingRotatedPillarBlock;
import com.davigj.frame_changer.common.block.CryingSlabBlock;
import com.davigj.frame_changer.common.block.CryingWallBlock;
import com.davigj.frame_changer.core.FrameChanger;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FCBlocks {
    public static final BlockSubRegistryHelper HELPER = FrameChanger.REGISTRY_HELPER.getBlockSubHelper();

    public static final DeferredBlock<Block> OBSIDIAN_BRICKS = HELPER.createBlock("obsidian_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> OBSIDIAN_BRICK_SLAB = HELPER.createBlock("obsidian_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> OBSIDIAN_BRICK_STAIRS = HELPER.createBlock("obsidian_brick_stairs", () -> new StairBlock(OBSIDIAN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> OBSIDIAN_BRICK_WALL = HELPER.createBlock("obsidian_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));

    public static final DeferredBlock<Block> CRYING_OBSIDIAN_BRICKS = HELPER.createBlock("crying_obsidian_bricks", () -> new CryingObsidianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CRYING_OBSIDIAN_BRICK_SLAB = HELPER.createBlock("crying_obsidian_brick_slab", () -> new CryingSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CRYING_OBSIDIAN_BRICK_STAIRS = HELPER.createBlock("crying_obsidian_brick_stairs", () -> new StairBlock(CRYING_OBSIDIAN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CRYING_OBSIDIAN_BRICK_WALL = HELPER.createBlock("crying_obsidian_brick_wall", () -> new CryingWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));

    public static final DeferredBlock<Block> POLISHED_OBSIDIAN = HELPER.createBlock("polished_obsidian", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> POLISHED_OBSIDIAN_SLAB = HELPER.createBlock("polished_obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> POLISHED_OBSIDIAN_STAIRS = HELPER.createBlock("polished_obsidian_stairs", () -> new StairBlock(POLISHED_OBSIDIAN.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> POLISHED_OBSIDIAN_WALL = HELPER.createBlock("polished_obsidian_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));

    public static final DeferredBlock<Block> CRYING_POLISHED_OBSIDIAN = HELPER.createBlock("crying_polished_obsidian", () -> new CryingObsidianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CRYING_POLISHED_OBSIDIAN_SLAB = HELPER.createBlock("crying_polished_obsidian_slab", () -> new CryingSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CRYING_POLISHED_OBSIDIAN_STAIRS = HELPER.createBlock("crying_polished_obsidian_stairs", () -> new StairBlock(CRYING_POLISHED_OBSIDIAN.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CRYING_POLISHED_OBSIDIAN_WALL = HELPER.createBlock("crying_polished_obsidian_wall", () -> new CryingWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));

    public static final DeferredBlock<Block> OBSIDIAN_PILLAR = HELPER.createBlock("obsidian_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final DeferredBlock<Block> CRYING_OBSIDIAN_PILLAR = HELPER.createBlock("crying_obsidian_pillar", () -> new CryingRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final DeferredBlock<Block> CHISELED_OBSIDIAN = HELPER.createBlock("chiseled_obsidian", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(35.0F, 1200.0F)));
    public static final DeferredBlock<Block> CRYING_CHISELED_OBSIDIAN = HELPER.createBlock("crying_chiseled_obsidian", () -> new CryingObsidianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));

    public static void buildCreativeTabContents() {
        CreativeModeTabContentsPopulator.mod(FrameChanger.MOD_ID)
                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                .addItems(OBSIDIAN_BRICKS, OBSIDIAN_BRICK_STAIRS, OBSIDIAN_BRICK_SLAB, OBSIDIAN_BRICK_WALL,
                        POLISHED_OBSIDIAN, POLISHED_OBSIDIAN_STAIRS, POLISHED_OBSIDIAN_SLAB, POLISHED_OBSIDIAN_WALL,
                        CHISELED_OBSIDIAN, OBSIDIAN_PILLAR,
                        CRYING_OBSIDIAN_BRICKS, CRYING_OBSIDIAN_BRICK_STAIRS, CRYING_OBSIDIAN_BRICK_SLAB, CRYING_OBSIDIAN_BRICK_WALL,
                        CRYING_POLISHED_OBSIDIAN, CRYING_POLISHED_OBSIDIAN_STAIRS, CRYING_POLISHED_OBSIDIAN_SLAB, CRYING_POLISHED_OBSIDIAN_WALL,
                        CRYING_CHISELED_OBSIDIAN, CRYING_OBSIDIAN_PILLAR);
    }
}