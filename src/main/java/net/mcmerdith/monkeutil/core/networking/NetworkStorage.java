package net.mcmerdith.monkeutil.core.networking;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

/**
 * Storing the state and ID of IWireless
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class NetworkStorage {
    /**
     * The current state
     */
    public NetworkState state;
    /**
     * The current networkID
     */
    public int networkID;

    /**
     * Create a new NetworkStorage
     *
     * @param state     The initial state
     * @param networkID The initial ID
     */
    public NetworkStorage(NetworkState state, int networkID) {
        this.state = state;
        this.networkID = networkID;
    }

    /**
     * Create a new NetworkStorage with default parameters
     * <p>Equivalent to {@link #NetworkStorage(NetworkState, int)} with NetworkState.IDLE and an obtained ID</p>
     */
    public NetworkStorage() {
        this(NetworkState.IDLE, NetworkManager.get().obtainID());
    }

    /**
     * Change the properties of this Object
     *
     * @param state     The new NetworkState
     * @param networkID The new network ID
     * @apiNote If the requested networkID is in use, it will automatically be changed to a new ID (See {@link NetworkManager#obtainID(int)})
     */
    public void update(NetworkState state, int networkID) {
        this.state = state;
        this.networkID = NetworkManager.get().obtainID(networkID);

        this.stateChanged();
    }

    public void writeToNBT(CompoundNBT nbt) {
        nbt.putInt(Keys.NBT_NETWORKID, this.networkID);
        nbt.putString(Keys.NBT_NETWORKSTATE, this.state.STATE);
    }

    public void readFromNBT(CompoundNBT nbt) {
        NetworkState nState = NetworkState.valueOrIdle(nbt.getString(Keys.NBT_NETWORKSTATE));
        int id = nbt.getInt(Keys.NBT_NETWORKID);
        this.update(nState, id);
    }

    public void stateChanged() {
    }

    public BlockPos ping() {
        return null;
    }
}
