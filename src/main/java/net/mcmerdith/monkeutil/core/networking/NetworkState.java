package net.mcmerdith.monkeutil.core.networking;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/**
 * All states of an {@link NetworkStorage} instance
 *
 * @author mcmerdith
 * @version 1.0
 * @since 1.0
 */
public enum NetworkState implements IStringSerializable {
    IDLE("idle"),
    BUSY("busy"),
    FAIL("fail"),
    SUCCESS("success");

    public final String STATE;

    NetworkState(String s) {
        this.STATE = s;
    }

    @Override
    @Nonnull
    public String getString() {
        return STATE;
    }

    public static NetworkState valueOrIdle(String s) {
        for (NetworkState value : values()) {
            if (value.STATE.equals(s)) return value;
        }

        return IDLE;
    }
}
