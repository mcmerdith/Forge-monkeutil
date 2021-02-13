package net.mcmerdith.monkeutil.core.networking;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

/**
 * Store data on a WirelessBlock
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public abstract class IWirelessBlock extends TileEntity implements IWireless {
    /**
     * This blocks {@link NetworkStorage}
     */
    protected final NetworkStorage networkStorage = createNetworkStorage();

    /**
     * {@link Capability} handler for NetworkStorage
     */
    protected final LazyOptional<NetworkStorage> handler = LazyOptional.of(() -> networkStorage);

    /**
     * Create a new {@link NetworkStorage}
     *
     * @return {@link NetworkStorage} representing this instance
     */
    public abstract NetworkStorage createNetworkStorage();

    /**
     * Get this blocks {@link NetworkStorage}
     *
     * @return This blocks {@link NetworkStorage}
     */
    @Override
    public final NetworkStorage getNetworkStorage() {
        return networkStorage;
    }

    /**
     * Define this TileEntity
     *
     * @param tileentity Select from {@link net.mcmerdith.monkeutil.core.init.TileEntityTypeInit}
     */
    public IWirelessBlock(TileEntityType<?> tileentity) {
        super(tileentity);
        NetworkManager.get().registerNetworkObject(this, networkStorage.networkID);
    }

    /**
     * Apply the {@link CapabilityNetwork} to this TileEntity
     */
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        // If it's looking for the Network capability return this capability
        if (cap == CapabilityNetwork.NETWORK) {
            return handler.cast();
        }

        // Otherwise, super
        return super.getCapability(cap);
    }

    /**
     * Update this NetworkStorage with NBT data
     */
    @Override
    public void read(@Nonnull BlockState state, @Nonnull CompoundNBT nbt) {
        // The old NetworkID
        int source = networkStorage.networkID;
        // Update the object
        networkStorage.readFromNBT(nbt);
        // Re-register this object with the new ID
        NetworkManager.get().relocateNetworkObject(this, source, networkStorage.networkID);
        super.read(state, nbt);
    }

    /**
     * Save this NetworkStorage to NBT
     */
    @Nonnull
    @Override
    public CompoundNBT write(@Nonnull CompoundNBT nbt) {
        networkStorage.writeToNBT(nbt);
        return super.write(nbt);
    }
}
