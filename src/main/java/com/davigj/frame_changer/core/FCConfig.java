package com.davigj.frame_changer.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class FCConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> portalFrameTag;
        public final ForgeConfigSpec.ConfigValue<Boolean> customExitPortals;
        public final ForgeConfigSpec.ConfigValue<String> exitPortalFrame;
        public final ForgeConfigSpec.ConfigValue<Boolean> contagiousMisery;
        public final ForgeConfigSpec.ConfigValue<Double> convertChance;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("changes");
            builder.push("custom portal frames");
            portalFrameTag = builder.comment("Nether portal frames can be made of anything in the blocktag frame_changer:portal_frames").define("Nether portal frame variety", true);
            builder.pop();
            builder.push("custom exit portals");
            customExitPortals = builder.comment("Entering a portal causes the portal on the other side to be made of a custom block").define("Custom exit portals", false);
            exitPortalFrame = builder.comment("Define the block that exit portal frames are made of. Format should follow 'modid:blockID'. Ideally, the block should also be a valid portal frame.").define("Exit portal frame", "minecraft:obsidian");
            builder.pop();
            builder.push("portal break changes");
            contagiousMisery = builder.comment("Breaking a portal causes obsidian blocks in the frame to possibly convert to their crying variants").define("Contagious misery", false);
            convertChance = builder.comment("Chance of broken portals turning obsidian blocks into their crying variants. Takes in a double value.").define("Crying conversion chance", 0.33D);
            builder.pop();
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final FCConfig.Common COMMON;


    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(FCConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
