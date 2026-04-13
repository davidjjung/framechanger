package com.davigj.frame_changer.core.other.compat;

import com.ordana.dimensional_tears.configs.CommonConfigs;
import net.neoforged.fml.ModList;

public class DTCompat {
    public static final double dimensionalTearsCryingPortals;
    public static final boolean dimensionalTearsPortalFluid;

    static {
        dimensionalTearsCryingPortals = ModList.get().isLoaded("dimensional_tears") ? CommonConfigs.PORTAL_DESTRUCTION_CRYING_OBSIDIAN_CHANCE.get() : 0;
        dimensionalTearsPortalFluid = ModList.get().isLoaded("dimensional_tears") ? CommonConfigs.CRYING_OBSIDIAN_DIMENSIONAL_TEARS.get() : false;
    }
}
