package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.MonkeUtil;
import net.mcmerdith.monkeutil.common.items.RemoteControlItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemInit extends Initializer<Item, Item.Properties> {
    public final RegistryObject<RemoteControlItem> REMOTE_CONTROL;
    public final RegistryObject<Item> WIRELESS_ANTENNA;

    public ItemInit() {
        super(ForgeRegistries.ITEMS);

        REMOTE_CONTROL = registerSpecial("remote_control", RemoteControlItem::new);
        WIRELESS_ANTENNA = registerFactory("wireless_antenna", new Item.Properties().group(MonkeUtil.ITEM_GROUP));
    }

    @Override
    protected Supplier<Item> factory(Item.Properties arg) {
        return () -> new Item(arg);
    }
}
