package net.mcmerdith.monkeutil.core.networking.packet;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface BasePacket {
    /**
     * Save this packet to a {@link PacketBuffer}
     *
     * @param buf the buffer to save to
     */
    void toBytes(PacketBuffer buf);

    /**
     * Handle this packet being received
     *
     * @param ctx The context of this packet
     * @return If the packet was handled
     */
    boolean handle(Supplier<NetworkEvent.Context> ctx);
}
