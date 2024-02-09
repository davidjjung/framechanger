package com.davigj.frame_changer.core;

import com.davigj.frame_changer.core.data.client.FCBlockStateProvider;
import com.davigj.frame_changer.core.data.server.FCBlockTagsProvider;
import com.davigj.frame_changer.core.data.server.FCLootTableProvider;
import com.davigj.frame_changer.core.data.server.FCRecipeProvider;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.davigj.frame_changer.core.other.FCConstants.*;

@Mod(FrameChanger.MOD_ID)
public class FrameChanger {
    public static final String MOD_ID = "frame_changer";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public FrameChanger() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);
        context.registerConfig(ModConfig.Type.COMMON, FCConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            initializeObbyMap();
            determineChiselMap();
            portalFluidMap();
        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeServer = event.includeServer();
        FCBlockTagsProvider blockTags = new FCBlockTagsProvider(generator, helper);
        generator.addProvider(includeServer, blockTags);
        generator.addProvider(includeServer, new FCRecipeProvider(generator));
        generator.addProvider(includeServer, new FCLootTableProvider(generator));

        boolean includeClient = event.includeClient();
        generator.addProvider(includeClient, new FCBlockStateProvider(generator, helper));
    }
}