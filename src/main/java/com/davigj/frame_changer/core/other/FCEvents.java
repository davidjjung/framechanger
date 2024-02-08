package com.davigj.frame_changer.core.other;

import com.davigj.frame_changer.core.FrameChanger;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FrameChanger.MOD_ID)
public class FCEvents {

    // TODO: Spelunkery Hammer and Chisel compat (sobbing)
    @SubscribeEvent
    public static void onChisel(LivingEntityUseItemEvent.Finish event) {

    }

    @SubscribeEvent
    public static void onHammer(BlockEvent.BlockToolModificationEvent event) {

    }
}