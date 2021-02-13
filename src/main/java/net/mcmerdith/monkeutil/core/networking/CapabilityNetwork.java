package net.mcmerdith.monkeutil.core.networking;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * A TileEntity Capability to access the network
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class CapabilityNetwork {
    @CapabilityInject(NetworkStorage.class)
    public static Capability<NetworkStorage> NETWORK = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(NetworkStorage.class, new Capability.IStorage<NetworkStorage>() {
                    @Override
                    public INBT writeNBT(Capability<NetworkStorage> capability, NetworkStorage instance, Direction side) {
                        CompoundNBT nbt = new CompoundNBT();
                        nbt.put(Keys.NBT_NETWORKSTATE, StringNBT.valueOf(instance.state.getString()));
                        nbt.put(Keys.NBT_NETWORKID, IntNBT.valueOf(instance.networkID));
                        return nbt;
                    }

                    @Override
                    public void readNBT(Capability<NetworkStorage> capability, NetworkStorage instance, Direction side, INBT nbt) {
                        if (!(nbt instanceof CompoundNBT))
                            throw new IllegalArgumentException("NBT for IWirelessBlock was not a CompoundNBT tag! This is not acceptable");
                        CompoundNBT data = (CompoundNBT) nbt;
                        NetworkState state = NetworkState.valueOrIdle(data.getString(Keys.NBT_NETWORKSTATE));
                        int id = data.getInt(Keys.NBT_NETWORKID);

                        instance.update(state, id);
                    }
                },
                NetworkStorage::new);
    }
}
