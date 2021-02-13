package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.Main;
import net.mcmerdith.monkeutil.common.items.RemoteControlItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Register this mods items
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class ItemInit extends Initializer<Item, Item.Properties> {
    public final RegistryObject<RemoteControlItem> REMOTE_CONTROL;
    public final RegistryObject<Item> WIRELESS_ANTENNA;
    public final RegistryObject<Item> COPPER_COIL;
    public final RegistryObject<Item> COPPER_INGOT;

    public ItemInit() {
        super(ForgeRegistries.ITEMS);

        REMOTE_CONTROL = registerSpecial("remote_control", RemoteControlItem::new);
        WIRELESS_ANTENNA = registerFactory("wireless_antenna", new Item.Properties().group(Main.ITEM_GROUP));
        COPPER_COIL = registerFactory("copper_coil", new Item.Properties().group(Main.ITEM_GROUP));
        COPPER_INGOT = registerFactory("copper_ingot", new Item.Properties().group(Main.ITEM_GROUP));
    }

    @Override
    protected Supplier<Item> factory(Item.Properties arg) {
        return () -> new Item(arg);
    }
}
