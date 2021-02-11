package net.mcmerdith.monkeutil.common.containers;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nullable;

public abstract class BaseContainer extends Container {
    protected BaseContainer(@Nullable ContainerType<?> type, int id, PlayerInventory inventory, PacketBuffer data) {
        super(type, id);
    }
}
