package com.davigj.frame_changer.core;

import com.davigj.frame_changer.core.data.client.FCBlockStateProvider;
import com.davigj.frame_changer.core.data.server.FCBlockTagsProvider;
import com.davigj.frame_changer.core.data.server.FCRecipeProvider;
import com.davigj.frame_changer.core.registry.FCBlocks;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

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

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> FCBlocks::buildCreativeTabContents);

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
        PackOutput output = generator.getPackOutput();
        CompletableFuture<Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean includeServer = event.includeServer();
        FCBlockTagsProvider blockTags = new FCBlockTagsProvider(output, provider, helper);
        generator.addProvider(includeServer, blockTags);
        generator.addProvider(includeServer, new FCRecipeProvider(output));
//        generator.addProvider(includeServer, new FCLootTableProvider(generator));

        boolean includeClient = event.includeClient();
        generator.addProvider(includeClient, new FCBlockStateProvider(output, helper));
    }
}