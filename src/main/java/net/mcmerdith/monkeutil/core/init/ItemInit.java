package net.mcmerdith.monkeutil.core.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemInit extends Initializer<Item> {
    public ItemInit() {
        super(ForgeRegistries.ITEMS);

        add("remote_control", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
    }

    @Override
    <I extends Item> Supplier<? extends I> factory() {
        return null;
    }
}
