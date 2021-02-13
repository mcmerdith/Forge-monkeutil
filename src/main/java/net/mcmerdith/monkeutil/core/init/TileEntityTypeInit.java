package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.Main;
import net.mcmerdith.monkeutil.client.render.tileentity.WirelessLampTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Register this mods TileEntities
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class TileEntityTypeInit extends Initializer<TileEntityType<?>, String> {
    public final RegistryObject<TileEntityType<WirelessLampTileEntity>> WIRELESS_LAMP;

    public TileEntityTypeInit() {
        super(ForgeRegistries.TILE_ENTITIES);

        WIRELESS_LAMP = registerSpecial("wireless_lamp", () -> TileEntityType.Builder.create(WirelessLampTileEntity::new, Main.getInstance().BLOCKS.WIRELESS_LAMP.get()).build(null));
    }

    @Override
    protected Supplier<TileEntityType<?>> factory(String arg) {
        return null;
    }
}
