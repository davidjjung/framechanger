package com.davigj.frame_changer.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class FCConfig {
    public static class Common {
        public final ModConfigSpec.ConfigValue<Boolean> portalFrameTag;
        public final ModConfigSpec.ConfigValue<Boolean> customExitPortals;
        public final ModConfigSpec.ConfigValue<String> exitPortalFrame;
        public final ModConfigSpec.ConfigValue<Boolean> contagiousMisery;
        public final ModConfigSpec.ConfigValue<Double> convertChance;

        Common (ModConfigSpec.Builder builder) {
            builder.push("changes");
            builder.push("custom portal frames").translation("frame_changer.configuration.custom_portal_frames");
            portalFrameTag = builder.comment("Nether portal frames can be made of anything in the blocktag frame_changer:portal_frames").translation("frame_changer.configuration.portal_frame_tag").define("Nether portal frame variety", true);
            builder.pop();
            builder.push("custom exit portals").translation("frame_changer.configuration.custom_exit_portals");
            customExitPortals = builder.comment("Entering a portal causes the portal on the other side to be made of a custom block").translation("frame_changer.configuration.custom_exit_portals").define("Custom exit portals", false);
            exitPortalFrame = builder.comment("Define the block that exit portal frames are made of. Format should follow 'modid:blockID'. Ideally, the block should also be a valid portal frame.").translation("frame_changer.configuration.exit_portal_frame").define("Exit portal frame", "minecraft:obsidian");
            builder.pop();
            builder.push("portal break changes").translation("frame_changer.configuration.portal_break_changes");
            contagiousMisery = builder.comment("Breaking a portal causes obsidian blocks in the frame to possibly convert to their crying variants").translation("frame_changer.configuration.contagious_misery").define("Contagious misery", false);
            convertChance = builder.comment("Chance of broken portals turning obsidian blocks into their crying variants. Takes in a double value.").translation("frame_changer.configuration.crying_conversion_chance").define("Crying conversion chance", 0.33D);
            builder.pop();
            builder.pop();
        }
    }

    static final ModConfigSpec COMMON_SPEC;
    public static final FCConfig.Common COMMON;


    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(FCConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
