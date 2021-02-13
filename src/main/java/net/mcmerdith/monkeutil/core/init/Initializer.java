package net.mcmerdith.monkeutil.core.init;

import net.mcmerdith.monkeutil.core.enums.Keys;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

/**
 * Base class for a {@link net.minecraftforge.registries.ForgeRegistry} initializer
 *
 * @param <T> The type to register
 * @param <V> The type of the {@link #factory(Object)} param
 */
public abstract class Initializer<T extends net.minecraftforge.registries.IForgeRegistryEntry<T>, V> {
    /**
     * The forge register to add to
     */
    public final DeferredRegister<T> REGISTER;

    /**
     * Set the registry
     *
     * @param registry The registry to sue
     */
    protected Initializer(IForgeRegistry<T> registry) {
        this.REGISTER = DeferredRegister.create(registry, Keys.MODID);
    }

    /**
     * Add this initializer to the Forge EventBus
     *
     * @param bus The Forge EventBus
     */
    public void register(IEventBus bus) {
        REGISTER.register(bus);
    }

    /**
     * Register an object using the {@link #factory(Object)} method
     *
     * @param name        Registry name of the object
     * @param factoryargs Argument to pass to {@link #factory(Object)}
     * @return The registered object
     */
    protected final RegistryObject<T> registerFactory(String name, V factoryargs) {
        return registerRaw(name, factory(factoryargs));
    }

    /**
     * Register a special object
     *
     * @param name     Registry name of the object
     * @param supplier A function supplying new instances of the object
     * @param <I>      The custom type
     * @return The registered object
     */
    protected final <I extends T> RegistryObject<I> registerSpecial(String name, Supplier<? extends I> supplier) {
        return REGISTER.register(name, supplier);
    }

    /**
     * Register an object
     *
     * @param name     Registry name of the object
     * @param supplier A function supplying new instances of the object
     * @param <I>      The type of the object
     * @return The registered object
     */
    private <I extends T> RegistryObject<T> registerRaw(String name, Supplier<? extends I> supplier) {
        return REGISTER.register(name, supplier);
    }

    /**
     * Return a new instance of the registrable object
     *
     * @param arg The argument passed from the registration
     * @return A function supplying new instances of the object
     */
    abstract protected Supplier<T> factory(V arg);
}
