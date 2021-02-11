package net.mcmerdith.monkeutil.core.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ContainerTypeInit extends Initializer<ContainerType<?>, String> {

    public ContainerTypeInit() {
        super(ForgeRegistries.CONTAINERS);
    }

    @Override
    protected Supplier<ContainerType<?>> factory(String arg) {
        return null;
    }
}