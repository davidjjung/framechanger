package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.FrameChanger;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ordana.spelunkery.reg.ModItems;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

import static com.davigj.frame_changer.core.other.FCConstants.spelunkeryCryingPortals;

public class FCDataMapUtil {
    private final static Logger LOGGER = LogManager.getLogger(FrameChanger.MOD_ID);

    public record CryingData(String result) {
        public static final Codec<CryingData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("result").forGetter(CryingData::result)
        ).apply(instance, CryingData::new));
    }

    public static final DataMapType<Block, CryingData> CRYING_CONVERTS = DataMapType.builder(
            ResourceLocation.fromNamespaceAndPath(FrameChanger.MOD_ID, "crying_converts"), Registries.BLOCK, CryingData.CODEC
    ).build();

    public static final DataMapType<Block, CryingData> SPELUNKERY_PORTAL_FLUID_DRAIN_CONVERTS = DataMapType.builder(
            ResourceLocation.fromNamespaceAndPath(FrameChanger.MOD_ID, "spelunkery_portal_fluid_drain_converts"), Registries.BLOCK, CryingData.CODEC
    ).build();

    private static Supplier<Block> getConversionBlock(String fullId) {
        String[] parts = fullId.split(":");
        if (parts.length != 2) {
            LOGGER.warn("Improperly formatted config for crying conversion block. String should be formatted 'modID:blockID'. Defaulting to minecraft:crying_obsidian");
            return () -> Blocks.CRYING_OBSIDIAN;
        }
        String modid = parts[0];
        String blockID = parts[1];
        if (!ModList.get().isLoaded(modid) && modid != null) {
            LOGGER.warn("Mod '" + modid + "' not loaded, invalid crying conversion blockID. String should be formatted 'modID:blockID'. Defaulting to minecraft:crying_obsidian");
            return () -> Blocks.CRYING_OBSIDIAN;
        }
        assert modid != null;
        ResourceLocation block = ResourceLocation.fromNamespaceAndPath(modid, blockID);
        if (BuiltInRegistries.BLOCK.get(block) == Blocks.AIR) {
            LOGGER.warn("Invalid crying conversion blockID. String should be formatted 'modID:blockID'. Defaulting to minecraft:crying_obsidian");
            return () -> Blocks.CRYING_OBSIDIAN;
        }
        return (ModList.get().isLoaded(modid) ? () -> BuiltInRegistries.BLOCK.get(block) : () -> null);
    }

    public static void fluidSpread(BlockState state, Level level, BlockPos pos, double cryChance) {
        Direction.Axis axis2 = state.getValue(NetherPortalBlock.AXIS);
        RandomSource random = level.getRandom();
        for (Direction cryDir : Direction.values()) {
            BlockState cryState = level.getBlockState(pos.relative(cryDir));
            Holder<Block> holder = cryState.getBlockHolder();
            FCDataMapUtil.CryingData data = holder.getData(CRYING_CONVERTS);

            if (data != null && random.nextDouble() < cryChance && !(new PortalShape(level, pos, axis2)).isComplete()) {
                BlockState convertedState = BlockUtil.transferAllBlockStates(cryState, getConversionBlock(data.result).get().defaultBlockState());
                if (ModList.get().isLoaded("spelunkery")) {
                    if (!(spelunkeryCryingPortals && cryState.is(Blocks.OBSIDIAN))) {
                        level.setBlock(pos.relative(cryDir), convertedState, 3);
                    }
                } else {
                    level.setBlock(pos.relative(cryDir), convertedState, 3);
                }
            }
        }
    }

    public static void fluidDrain(Player player, BlockState clickedBlockState, ItemStack heldItem, PlayerInteractEvent.RightClickBlock event) {
        Holder<Block> holder = clickedBlockState.getBlockHolder();
        FCDataMapUtil.CryingData data = holder.getData(SPELUNKERY_PORTAL_FLUID_DRAIN_CONVERTS);

        if (data != null && heldItem.is(Items.GLASS_BOTTLE)) {
            BlockState convertedState = BlockUtil.transferAllBlockStates(clickedBlockState, getConversionBlock(data.result).get().defaultBlockState());
            player.level().setBlock(event.getPos(), convertedState, 3);
            player.swing(event.getHand());
            event.setCancellationResult(InteractionResult.SUCCESS);
            ItemStack portalFluid = new ItemStack(ModItems.PORTAL_FLUID_BOTTLE.get());
            player.level().playSound(player, event.getPos(), SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.BLOCKS, 1.0f, 1.0f);
            ParticleUtils.spawnParticlesOnBlockFaces(player.level(), event.getPos(), ParticleTypes.FALLING_OBSIDIAN_TEAR, UniformInt.of(3, 5));
            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
                if (!player.getInventory().add(portalFluid)) {
                    player.drop(portalFluid, false);
                }
            }
        }
    }
}
