package com.davigj.frame_changer.core.registry;

import com.davigj.frame_changer.common.block.CryingRotatedPillarBlock;
import com.davigj.frame_changer.common.block.CryingSlabBlock;
import com.davigj.frame_changer.common.block.CryingWallBlock;
import com.davigj.frame_changer.core.FrameChanger;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static net.minecraft.world.item.crafting.Ingredient.of;

public class FCBlocks {
    public static final BlockSubRegistryHelper BLOCKS = FrameChanger.REGISTRY_HELPER.getBlockSubHelper();

    public static final ItemSubRegistryHelper ITEMS = FrameChanger.REGISTRY_HELPER.getItemSubHelper();

    public static final Supplier<Block> OBSIDIAN_BRICKS = BLOCKS.createBlock("obsidian_bricks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> OBSIDIAN_BRICK_SLAB = BLOCKS.createBlock("obsidian_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> OBSIDIAN_BRICK_STAIRS = BLOCKS.createBlock("obsidian_brick_stairs", () -> new StairBlock(OBSIDIAN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> OBSIDIAN_BRICK_WALL = BLOCKS.createBlock("obsidian_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));

    public static final Supplier<Block> CRYING_OBSIDIAN_BRICKS = BLOCKS.createBlock("crying_obsidian_bricks", () -> new CryingObsidianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CRYING_OBSIDIAN_BRICK_SLAB = BLOCKS.createBlock("crying_obsidian_brick_slab", () -> new CryingSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CRYING_OBSIDIAN_BRICK_STAIRS = BLOCKS.createBlock("crying_obsidian_brick_stairs", () -> new StairBlock(CRYING_OBSIDIAN_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CRYING_OBSIDIAN_BRICK_WALL = BLOCKS.createBlock("crying_obsidian_brick_wall", () -> new CryingWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));

    public static final Supplier<Block> POLISHED_OBSIDIAN = BLOCKS.createBlock("polished_obsidian", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> POLISHED_OBSIDIAN_SLAB = BLOCKS.createBlock("polished_obsidian_slab", () -> new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> POLISHED_OBSIDIAN_STAIRS = BLOCKS.createBlock("polished_obsidian_stairs", () -> new StairBlock(POLISHED_OBSIDIAN.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> POLISHED_OBSIDIAN_WALL = BLOCKS.createBlock("polished_obsidian_wall", () -> new WallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));

    public static final Supplier<Block> CRYING_POLISHED_OBSIDIAN = BLOCKS.createBlock("crying_polished_obsidian", () -> new CryingObsidianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CRYING_POLISHED_OBSIDIAN_SLAB = BLOCKS.createBlock("crying_polished_obsidian_slab", () -> new CryingSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CRYING_POLISHED_OBSIDIAN_STAIRS = BLOCKS.createBlock("crying_polished_obsidian_stairs", () -> new StairBlock(CRYING_POLISHED_OBSIDIAN.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CRYING_POLISHED_OBSIDIAN_WALL = BLOCKS.createBlock("crying_polished_obsidian_wall", () -> new CryingWallBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));

    public static final Supplier<Block> OBSIDIAN_PILLAR = BLOCKS.createBlock("obsidian_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F)));
    public static final Supplier<Block> CRYING_OBSIDIAN_PILLAR = BLOCKS.createBlock("crying_obsidian_pillar", () -> new CryingRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));
    public static final Supplier<Block> CHISELED_OBSIDIAN = BLOCKS.createBlock("chiseled_obsidian", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(35.0F, 1200.0F)));
    public static final Supplier<Block> CRYING_CHISELED_OBSIDIAN = BLOCKS.createBlock("crying_chiseled_obsidian", () -> new CryingObsidianBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(22.5F, 600.0F).lightLevel((p_152651_) -> {return 10;})));

    public static void setUpTabEditors() {
        CreativeModeTabContentsPopulator.mod(FrameChanger.MOD_ID)
                .tab(CreativeModeTabs.BUILDING_BLOCKS)
                .addItemsBefore(of(Items.NETHERRACK), OBSIDIAN_BRICKS, OBSIDIAN_BRICK_STAIRS, OBSIDIAN_BRICK_SLAB, OBSIDIAN_BRICK_WALL,
                        POLISHED_OBSIDIAN, POLISHED_OBSIDIAN_STAIRS, POLISHED_OBSIDIAN_SLAB, POLISHED_OBSIDIAN_WALL,
                        CHISELED_OBSIDIAN, OBSIDIAN_PILLAR,
                        CRYING_OBSIDIAN_BRICKS, CRYING_OBSIDIAN_BRICK_STAIRS, CRYING_OBSIDIAN_BRICK_SLAB, CRYING_OBSIDIAN_BRICK_WALL,
                        CRYING_POLISHED_OBSIDIAN, CRYING_POLISHED_OBSIDIAN_STAIRS, CRYING_POLISHED_OBSIDIAN_SLAB, CRYING_POLISHED_OBSIDIAN_WALL,
                        CRYING_CHISELED_OBSIDIAN, CRYING_OBSIDIAN_PILLAR);
    }
}