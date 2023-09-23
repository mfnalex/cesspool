package com.jeff_media.cesspool.utils;

import java.util.concurrent.TimeUnit;

public final class TickConverter {

    public static long toTicks(final long time, final TimeUnit timeUnit) {
        return timeUnit.toSeconds(time) * 20;
    }

    public static long fromTicks(final long ticks, final TimeUnit targetUnit) {
        long milliseconds = ticks * 50;
        return targetUnit.convert(milliseconds, TimeUnit.MILLISECONDS);
    }

}
