package com.jeff_media.cesspool.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * Utility methods for floating point numbers. All mentions of "same" or "equal to" within this class refer to the
 * differences being smaller than {@link #EPSILON_D} or {@link #EPSILON_F}.
 */
public final class FloatingPointUtils {

    /**
     * The smallest difference between two floating point numbers that is considered "equal to zero"
     */
    public static final double EPSILON_D = 1e-6;

    /**
     * The smallest difference between two floating point numbers that is considered "equal to zero"
     */
    public static final float EPSILON_F = 1e-6f;

    private FloatingPointUtils() {
    }

    /**
     * Checks if the given numbers are the same
     *
     * @param number1 First number
     * @param number2 Second number
     * @return Whether the numbers are the same
     */
    public static boolean isEqual(final double number1, final double number2) {
        return isZero(number1 - number2);
    }

    /**
     * Checks if the given number is zero
     *
     * @param number Number to check
     * @return Whether the number is zero
     */
    public static boolean isZero(final double number) {
        return Math.abs(number) < EPSILON_D;
    }

    /**
     * Checks if the given numbers are the same
     *
     * @param number1 First number
     * @param number2 Second number
     * @return Whether the numbers are the same
     */
    public static boolean isEqual(final float number1, final float number2) {
        return isZero(number1 - number2);
    }

    /**
     * Checks if the given number is zero
     *
     * @param number Number to check
     * @return Whether the number is zero
     */
    public static boolean isZero(final float number) {
        return Math.abs(number) < EPSILON_F;
    }

    /**
     * Checks if the given number is zero or positive
     *
     * @param number Number to check
     * @return Whether the number is zero or positive
     */
    public static boolean isZeroOrPositive(final double number) {
        return isZero(number) || number >= EPSILON_D;
    }

    /**
     * Checks if the given number is zero or positive
     *
     * @param number Number to check
     * @return Whether the number is zero or positive
     */
    public static boolean isZeroOrPositive(final float number) {
        return isZero(number) || number >= EPSILON_F;
    }

    /**
     * Checks if the given number is zero or positive. Null values will be treated as zero
     *
     * @param number Number to check
     * @return Whether the number is zero or positive
     */
    @Contract("null -> true")
    public static boolean isZeroOrPositive(@Nullable final Number number) {
        return isZero(number) || number.doubleValue() >= EPSILON_D;
    }

    /**
     * Checks if the given number is zero. Null values will be treated as zero
     */
    @Contract("null -> true")
    public static boolean isZero(@Nullable final Number number) {
        return number == null || isZero(number.doubleValue());
    }

    /**
     * Checks if the given number is zero or negative
     *
     * @param number Number to check
     * @return Whether the number is zero or negative
     */
    public static boolean isZeroOrNegative(final double number) {
        return isZero(number) || number <= EPSILON_D;
    }

    /**
     * Checks if the given number is zero or negative
     *
     * @param number Number to check
     * @return Whether the number is zero or negative
     */
    public static boolean isZeroOrNegative(final float number) {
        return isZero(number) || number <= EPSILON_F;
    }

    /**
     * Checks if the given number is zero or negative. Null values will be treated as zero
     */
    @Contract("null -> true")
    public static boolean isZeroOrNegative(@Nullable final Number number) {
        return isZero(number) || number.doubleValue() <= EPSILON_D;
    }

    /**
     * Checks if the given number is between the given min and max values, inclusive
     * @param number    Number to check
     * @param min      Minimum value
     * @param max     Maximum value
     * @return Whether the number is between the given min and max values, inclusive
     */
    public static boolean isInbetweenInclusive(final int number, final int min, final int max) {
        return number >= min && number <= max;
    }

    /**
     * Checks if a given String is an Integer.
     */
    public boolean isInteger(final String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the given String as Integer, or null if it isn't an Integer.
     */
    @Nullable
    public Integer parseInteger(final String text) {
        try {
            return Integer.parseInt(text);
        } catch (final NumberFormatException e) {
            return null;
        }
    }
}


