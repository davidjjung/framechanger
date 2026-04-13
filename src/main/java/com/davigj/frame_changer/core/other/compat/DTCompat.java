package com.davigj.frame_changer.core.other.compat;

import com.ordana.dimensional_tears.configs.CommonConfigs;
import com.ordana.dimensional_tears.reg.ModItems;
import com.ordana.dimensional_tears.reg.ModSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import static com.davigj.frame_changer.core.other.compat.ModConstants.DIMENSIONAL_TEARS;

public class DTCompat {
    public static final double dimensionalTearsCryingPortals;
    public static final boolean dimensionalTearsPortalFluid;
    public static final Item dimensionalTears;
    public static final SoundEvent portalBreak;
    public static final SoundEvent bottleFluid;

    static {
        dimensionalTearsCryingPortals = DIMENSIONAL_TEARS ? CommonConfigs.PORTAL_DESTRUCTION_CRYING_OBSIDIAN_CHANCE.get() : 0;
        dimensionalTearsPortalFluid = DIMENSIONAL_TEARS ? CommonConfigs.CRYING_OBSIDIAN_DIMENSIONAL_TEARS.get() : false;
        dimensionalTears = DIMENSIONAL_TEARS ? ModItems.DIMENSIONAL_TEARS_BOTTLE.get() : Items.GLASS_BOTTLE;
        portalBreak = DIMENSIONAL_TEARS ? ModSoundEvents.PORTAL_DESTROY.get() : SoundEvents.RESPAWN_ANCHOR_DEPLETE.value();
        bottleFluid = DIMENSIONAL_TEARS ? ModSoundEvents.BOTTLE_FILL_DIMENSIONAL_TEARS.get() : SoundEvents.BOTTLE_FILL;
    }
}
