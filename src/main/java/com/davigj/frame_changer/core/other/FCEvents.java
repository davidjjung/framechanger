package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.FrameChanger;
import com.davigj.frame_changer.core.other.compat.DTCompat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import static com.davigj.frame_changer.core.other.compat.ModConstants.DIMENSIONAL_TEARS;

@EventBusSubscriber(modid = FrameChanger.MOD_ID)
public class FCEvents {
    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!DIMENSIONAL_TEARS || !DTCompat.dimensionalTearsPortalFluid) {
            return;
        }
        Player player = event.getEntity();
        BlockState clickedBlockState = player.level().getBlockState(event.getPos());
        ItemStack heldItem = player.getItemInHand(event.getHand());

        FCDataMapUtil.fluidDrain(player, clickedBlockState, heldItem, event);
    }
}