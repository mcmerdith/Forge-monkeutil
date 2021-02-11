package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

public abstract class Initializer<T extends net.minecraftforge.registries.IForgeRegistryEntry<T>, V> {
    public final DeferredRegister<T> REGISTER;

    protected Initializer(IForgeRegistry<T> registry) {
        this.REGISTER = DeferredRegister.create(registry, Keys.MODID);
    }

    public void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    protected final RegistryObject<T> registerFactory(String name, V factoryargs) {
        return registerRaw(name, factory(factoryargs));
    }

    protected final <I extends T> RegistryObject<I> registerSpecial(String name, Supplier<? extends I> factory) {
        return REGISTER.register(name, factory);
    }

    protected final <I extends T> RegistryObject<T> registerRaw(String name, Supplier<? extends I> supplier) {
        return REGISTER.register(name, supplier);
    }

    abstract protected Supplier<T> factory(V arg);
}
