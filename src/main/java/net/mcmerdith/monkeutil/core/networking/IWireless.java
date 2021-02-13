package net.mcmerdith.monkeutil.core.networking;

/**
 * A wireless object
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public interface IWireless {
    /**
     * Get this objects {@link NetworkStorage}
     *
     * @return This objects {@link NetworkStorage}
     */
    NetworkStorage getNetworkStorage();

    /**
     * Clean up anything that this object is holding on to before it's removed from the network
     */
    void close();
}
