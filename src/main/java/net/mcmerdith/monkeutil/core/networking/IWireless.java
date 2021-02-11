package net.mcmerdith.monkeutil.core.networking;

public interface IWireless<T extends NetworkObject> {
    int getNetworkID();
    NetworkState getState();
    T get();
}
