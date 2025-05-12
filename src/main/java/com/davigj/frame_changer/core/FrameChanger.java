package com.davigj.frame_changer.core;

import com.davigj.frame_changer.core.data.client.FCBlockStateProvider;
import com.davigj.frame_changer.core.data.server.FCBlockTagsProvider;
import com.davigj.frame_changer.core.data.server.FCRecipeProvider;
import com.davigj.frame_changer.core.registry.FCBlocks;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static com.davigj.frame_changer.core.other.FCConstants.*;

@Mod(FrameChanger.MOD_ID)
public class FrameChanger {
    public static final String MOD_ID = "frame_changer";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public FrameChanger(IEventBus bus, ModContainer container) {

        REGISTRY_HELPER.register(bus);

        if (FMLEnvironment.dist.isClient()) {
            FCBlocks.buildCreativeTabContents();
        }

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);
        container.registerConfig(ModConfig.Type.COMMON, FCConfig.COMMON_SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            initializeObbyMap();
            determineChiselMap();
            portalFluidMap();
        });
    }

    @SubscribeEvent
    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    @SubscribeEvent
    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeServer = event.includeServer();
        FCBlockTagsProvider blockTags = new FCBlockTagsProvider(output, provider, helper);
        generator.addProvider(includeServer, blockTags);
        generator.addProvider(includeServer, new FCRecipeProvider(output, provider));
//        generator.addProvider(includeServer, new FCLootTableProvider(generator));

        boolean includeClient = event.includeClient();
        generator.addProvider(includeClient, new FCBlockStateProvider(output, helper));
    }
}