package net.mcmerdith.monkeutil;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class for mod
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
@Mod(Keys.MODID)
public class MonkeUtil {
    // Instances
    /**
     * The Instance
     */
    private static MonkeUtil INSTANCE;

    /**
     * Get the Main instance
     * @return The current instance of this class
     */
    public static MonkeUtil getInstance() {
        return INSTANCE;
    }

    /**
     * System logger
     */
    private static final Logger LOGGER = LogManager.getLogger();

    public MonkeUtil() {
        // Initialize this instance
        INSTANCE = this;

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        eventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Preload Event
     * @param event FML Event
     */
    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    /**
     * All mod events
     * @author mcmerdith
     * @version 1.0
     * @since 1.0
     */
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
