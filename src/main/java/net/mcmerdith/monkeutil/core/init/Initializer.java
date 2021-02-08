package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.MonkeUtil;
import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public abstract class Initializer<T extends net.minecraftforge.registries.IForgeRegistryEntry<T>> {
    private final DeferredRegister<T> REGISTER;

    public Initializer(IForgeRegistry<T> registry) {
        MonkeUtil.LOGGER.info("{} Registered", registry.getRegistryName());
        this.REGISTER = DeferredRegister.create(registry, Keys.MODID);
    }

    public void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    protected final <I extends T> void add(String name, Supplier<? extends I> supplier) {
        REGISTER.register(name, supplier);
    }

    abstract <I extends T> Supplier<? extends I> factory();
}
