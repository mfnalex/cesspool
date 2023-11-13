package com.jeff_media.cesspool.utils;

import java.util.concurrent.TimeUnit;

/**
 * Converts ticks to arbitrary time units and vice versa
 */
public final class TickConverter {

    /**
     * Convers the given time into ticks (assuming 20 ticks per second)
     * @param time Time to convert
     * @param timeUnit Time unit of the time to convert
     * @return Time in ticks
     */
    public static long toTicks(final long time, final TimeUnit timeUnit) {
        return timeUnit.toSeconds(time) * 20;
    }

    /**
     * Converts the given ticks into the given time unit (assuming 20 ticks per second)
     * @param ticks Ticks to convert
     * @param targetUnit Target time unit
     * @return Time in the given time unit
     */
    public static long fromTicks(final long ticks, final TimeUnit targetUnit) {
        long milliseconds = ticks * 50;
        return targetUnit.convert(milliseconds, TimeUnit.MILLISECONDS);
    }

}
