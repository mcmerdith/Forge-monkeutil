package net.mcmerdith.monkeutil.core.networking;

import java.util.ArrayList;
import java.util.List;

public class NetworkManager {
    private static NetworkManager INSTANCE;
    public static NetworkManager get() {
        if (INSTANCE == null) INSTANCE = new NetworkManager();
        return INSTANCE;
    }
    private NetworkManager() {}

    private List<NetworkObject> NETWORK = new ArrayList<>();

    public void registerNetworkObject(NetworkObject obj) {
        NETWORK.add(obj);
    }

    public void releaseNetworkContract(int networkID) {

    }
}
