package net.mcmerdith.monkeutil;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.mcmerdith.monkeutil.core.init.BlockInit;
import net.mcmerdith.monkeutil.core.init.ContainerTypeInit;
import net.mcmerdith.monkeutil.core.init.ItemInit;
import net.mcmerdith.monkeutil.core.init.TileEntityTypeInit;
import net.mcmerdith.monkeutil.core.networking.NetworkManager;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
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
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
@Mod(Keys.MODID)
public class Main {
    /**
     * The Instance
     */
    private static Main INSTANCE;

    /**
     * Get the Main instance
     *
     * @return The current instance of this class
     */
    public static Main getInstance() {
        return INSTANCE;
    }

    /**
     * System logger
     */
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * All initializers
     */
    public final ItemInit ITEMS = new ItemInit();
    public final BlockInit BLOCKS = new BlockInit();
    public final ContainerTypeInit CONTAINERS = new ContainerTypeInit();
    public final TileEntityTypeInit TILEENTITIES = new TileEntityTypeInit();

    /**
     * Item Group
     */
    public static ItemGroup ITEM_GROUP = new MonkeUtilGroup();

    public Main() {
        // Initialize this instance
        INSTANCE = this;

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        eventBus.addListener(this::commonSetup);

        NetworkManager.get();

        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        CONTAINERS.register(eventBus);
        TILEENTITIES.register(eventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Preload Event
     *
     * @param event FML Event
     */
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    /**
     * All mod events
     *
     * @author mcmerdith
     * @version 1.0
     * @since 1.0
     */
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            ItemInit init = Main.getInstance().ITEMS;
            Main.getInstance().BLOCKS.REGISTER.getEntries().forEach(obj -> {
                if (!obj.isPresent()) {
                    Main.LOGGER.error("Registering BlockItem failed (registry object not present)");
                    return;
                }

                Block block = obj.get();

                final ResourceLocation resource = block.getRegistryName();
                if (resource == null) {
                    Main.LOGGER.error("Registering BlockItem failed! (no registry name)");
                    return;
                }

                BlockItem item = new BlockItem(block, new Item.Properties().group(Main.ITEM_GROUP));
                item.setRegistryName(resource.toString());

                event.getRegistry().register(item);
            });
        }
    }

    public static class MonkeUtilGroup extends ItemGroup {
        public MonkeUtilGroup() {
            super(Keys.MODID + "tab");
        }

        @Override
        public ItemStack createIcon() {
            return Main.getInstance().ITEMS.REMOTE_CONTROL.get().getDefaultInstance();
        }
    }
}
