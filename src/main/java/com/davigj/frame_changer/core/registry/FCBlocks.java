package com.davigj.frame_changer.core.registry;

import com.davigj.frame_changer.core.FrameChanger;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = FrameChanger.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCBlocks {
    public static final BlockSubRegistryHelper HELPER = FrameChanger.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> OBSIDIAN_BRICKS = HELPER.createBlock("obsidian_bricks", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> OBSIDIAN_BRICK_SLAB = HELPER.createBlock("obsidian_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> OBSIDIAN_BRICK_STAIRS = HELPER.createBlock("obsidian_brick_stairs", () -> new StairBlock(() -> OBSIDIAN_BRICKS.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> OBSIDIAN_BRICK_WALL = HELPER.createBlock("obsidian_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CRYING_OBSIDIAN_BRICKS = HELPER.createBlock("crying_obsidian_bricks", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BRICK_SLAB = HELPER.createBlock("crying_obsidian_brick_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BRICK_STAIRS = HELPER.createBlock("crying_obsidian_brick_stairs", () -> new StairBlock(() -> CRYING_OBSIDIAN_BRICKS.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BRICK_WALL = HELPER.createBlock("crying_obsidian_brick_wall", () -> new WallBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_OBSIDIAN = HELPER.createBlock("polished_obsidian", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_OBSIDIAN_SLAB = HELPER.createBlock("polished_obsidian_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_OBSIDIAN_STAIRS = HELPER.createBlock("polished_obsidian_stairs", () -> new StairBlock(() -> POLISHED_OBSIDIAN.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_OBSIDIAN_WALL = HELPER.createBlock("polished_obsidian_wall", () -> new WallBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CRYING_POLISHED_OBSIDIAN = HELPER.createBlock("crying_polished_obsidian", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_POLISHED_OBSIDIAN_SLAB = HELPER.createBlock("crying_polished_obsidian_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_POLISHED_OBSIDIAN_STAIRS = HELPER.createBlock("crying_polished_obsidian_stairs", () -> new StairBlock(() -> CRYING_POLISHED_OBSIDIAN.get().defaultBlockState(), Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_POLISHED_OBSIDIAN_WALL = HELPER.createBlock("crying_polished_obsidian_wall", () -> new WallBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OBSIDIAN_PILLAR = HELPER.createBlock("obsidian_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_PILLAR = HELPER.createBlock("crying_obsidian_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHISELED_OBSIDIAN = HELPER.createBlock("chiseled_obsidian", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(35.0F, 1200.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_CHISELED_OBSIDIAN = HELPER.createBlock("crying_chiseled_obsidian", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);

}