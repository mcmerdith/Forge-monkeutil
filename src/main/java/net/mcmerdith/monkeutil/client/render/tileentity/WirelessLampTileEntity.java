package net.mcmerdith.monkeutil.client.render.tileentity;

import net.mcmerdith.monkeutil.Main;
import net.mcmerdith.monkeutil.core.networking.IWirelessBlock;
import net.mcmerdith.monkeutil.core.networking.NetworkStorage;
import net.minecraft.util.math.BlockPos;

/**
 * Wireless Lamp TileEntity
 * <p>Store the data of a {@link net.mcmerdith.monkeutil.common.blocks.WirelessLampBlock}</p>
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class WirelessLampTileEntity extends IWirelessBlock {
    /**
     * Create a new TileEntity
     */
    public WirelessLampTileEntity() {
        super(Main.getInstance().TILEENTITIES.WIRELESS_LAMP.get());
    }

    @Override
    public NetworkStorage createNetworkStorage() {
        WirelessLampTileEntity te = this;
        return new NetworkStorage() {
            @Override
            public void stateChanged() {
                markDirty();
            }

            @Override
            public BlockPos ping() {
                return te.pos;
            }
        };
    }

    @Override
    public void close() {
    }
}
