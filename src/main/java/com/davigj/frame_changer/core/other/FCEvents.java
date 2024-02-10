package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.FrameChanger;
import com.ordana.spelunkery.reg.ModItems;
import com.teamabnormals.blueprint.core.util.BlockUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import static com.davigj.frame_changer.core.other.FCConstants.OBBY_MAP;
import static com.davigj.frame_changer.core.other.FCConstants.PORTAL_FLUID_MAP;
import static net.minecraft.world.level.block.RotatedPillarBlock.AXIS;

@Mod.EventBusSubscriber(modid = FrameChanger.MOD_ID)
public class FCEvents {
    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!ModList.get().isLoaded("spelunkery")) {
            return;
        }
        Player player = event.getEntity();
        ItemStack heldItem = player.getItemInHand(event.getHand());
        BlockState clickedBlockState = player.level.getBlockState(event.getPos());
        if (PORTAL_FLUID_MAP.containsKey(clickedBlockState.getBlock()) && heldItem.is(Items.GLASS_BOTTLE)) {
            BlockState convertedState = BlockUtil.transferAllBlockStates(clickedBlockState, PORTAL_FLUID_MAP.get(clickedBlockState.getBlock()).defaultBlockState());
            player.level.setBlock(event.getPos(), convertedState, 3);
            player.swing(event.getHand());
            event.setCancellationResult(InteractionResult.SUCCESS);
            ItemStack portalFluid = new ItemStack(ModItems.PORTAL_FLUID_BOTTLE.get());
            player.level.playSound(player, event.getPos(), SoundEvents.RESPAWN_ANCHOR_DEPLETE, SoundSource.BLOCKS, 1.0f, 1.0f);
            ParticleUtils.spawnParticlesOnBlockFaces(player.level, event.getPos(), ParticleTypes.FALLING_OBSIDIAN_TEAR, UniformInt.of(3, 5));
            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
                if (!player.getInventory().add(portalFluid)) {
                    player.drop(portalFluid, false);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onHammer(BlockEvent.BlockToolModificationEvent event) {

    }
}