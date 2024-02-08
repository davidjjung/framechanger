package com.davigj.frame_changer.core.registry;

import com.davigj.frame_changer.core.FrameChanger;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = FrameChanger.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FCBlocks {
    public static final BlockSubRegistryHelper HELPER = FrameChanger.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> CHISELED_OBSIDIAN = HELPER.createBlock("chiseled_obsidian", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(35.0F, 1200.0F)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_CHISELED_OBSIDIAN = HELPER.createBlock("crying_chiseled_obsidian", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> OBSIDIAN_BRICKS = HELPER.createBlock("obsidian_bricks", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_BRICKS = HELPER.createBlock("crying_obsidian_bricks", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> OBSIDIAN_PILLAR = HELPER.createBlock("obsidian_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_OBSIDIAN_PILLAR = HELPER.createBlock("crying_obsidian_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> POLISHED_OBSIDIAN = HELPER.createBlock("polished_obsidian", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CRYING_POLISHED_OBSIDIAN = HELPER.createBlock("crying_polished_obsidian", () -> new Block(Block.Properties.copy(Blocks.STONE)), CreativeModeTab.TAB_BUILDING_BLOCKS);
}