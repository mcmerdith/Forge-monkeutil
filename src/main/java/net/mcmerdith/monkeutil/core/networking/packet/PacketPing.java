package net.mcmerdith.monkeutil.core.networking.packet;

import net.mcmerdith.monkeutil.core.networking.IWireless;
import net.mcmerdith.monkeutil.core.networking.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPing implements BasePacket {
    private final int networkID;

    public PacketPing(PacketBuffer buf) {
        networkID = buf.readInt();
    }

    public PacketPing(int id) {
        networkID = id;
    }

    @Override
    public void toBytes(PacketBuffer buf) {
        buf.writeInt(networkID);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            IWireless block = NetworkManager.get().get(networkID);
            if (context.getSender() != null)
                context.getSender().sendStatusMessage(ITextComponent.getTextComponentOrEmpty(block.getNetworkStorage().ping().toString()), false);
            context.setPacketHandled(true);
        });
        return true;
    }
}
