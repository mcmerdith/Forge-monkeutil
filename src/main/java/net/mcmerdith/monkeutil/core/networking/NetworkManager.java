package net.mcmerdith.monkeutil.core.networking;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.mcmerdith.monkeutil.core.networking.packet.PacketPing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Store all the active instances of {@link IWireless}
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public class NetworkManager {
    /**
     * The Manager
     */
    private static NetworkManager INSTANCE;

    /**
     * Get the Manager
     *
     * @return The Manager
     */
    public static NetworkManager get() {
        if (INSTANCE == null) INSTANCE = new NetworkManager();
        return INSTANCE;
    }

    /**
     * Protocol Version we are using
     */
    private final String PROTOCOL_VERSION = "1";

    /**
     * Channel we are using
     */
    public final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Keys.MODID, Keys.MODID + "_wirelessnetwork"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    /**
     * Create a network manager
     */
    private NetworkManager() {
        CHANNEL.messageBuilder(PacketPing.class, 0)
                .encoder(PacketPing::toBytes)
                .decoder(PacketPing::new)
                .consumer(PacketPing::handle)
                .add();
    }

    /**
     * Random
     */
    private final Random r = new Random();
    /**
     * Max value for a networkID
     */
    public static final int NETWORK_ID_RANGE = 2000;

    /**
     * All objects on the network
     */
    private final Map<Integer, IWireless> NETWORK = new HashMap<>();

    /**
     * Register an object to this Network
     *
     * @param obj The object
     * @param id  The object's ID
     */
    public void registerNetworkObject(IWireless obj, int id) {
        NETWORK.put(id, obj);
    }

    /**
     * Change the ID of an object on this Network
     *
     * @param obj           The object
     * @param sourceID      The original ID (this will be de-registered)
     * @param destinationID The new ID (this will be registered)
     */
    public void relocateNetworkObject(IWireless obj, int sourceID, int destinationID) {
        releaseNetworkContract(sourceID);
        registerNetworkObject(obj, destinationID);
    }

    /**
     * Remove an object from this Network
     *
     * @param networkID The ID to remove
     */
    public void releaseNetworkContract(int networkID) {
        IWireless block = get(networkID);
        block.close();
        NETWORK.remove(networkID);
    }

    /**
     * Get an object from this network
     *
     * @param networkID The ID to get
     * @return The object (or null if there isn't an object by that ID)
     */
    public IWireless get(int networkID) {
        if (!NETWORK.containsKey(networkID)) return null;
        return NETWORK.get(networkID);
    }

    /**
     * Get an available Network ID
     *
     * @param id The requested ID
     * @return The requested ID, if available, otherwise a random ID
     */
    public int obtainID(int id) {
        return NETWORK.containsKey(id) ? obtainID() : id;
    }

    /**
     * Get a random Network ID
     *
     * @return An available network ID
     */
    public int obtainID() {
        int id = -1;
        while (id == -1 || NETWORK.containsKey(id)) {
            id = r.nextInt(NETWORK_ID_RANGE);
        }
        return id;
    }
}
