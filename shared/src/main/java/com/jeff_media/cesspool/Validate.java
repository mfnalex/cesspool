package com.jeff_media.cesspool;


import java.util.regex.Pattern;

import com.jeff_media.cesspool.exceptions.ParameterCannotBeNullException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Validates arguments
 */
public final class Validate {

    private static final String DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE = "The value %s is not in the specified inclusive range of %s to %s";

    private static final String DEFAULT_MATCHES_PATTERN_EX = "The string %s does not match the pattern %s";

    /**
     * <p>Validate that the specified argument object fall between the two
     * inclusive values specified; otherwise, throws an exception.</p>
     *
     * <pre>Validate.inclusiveBetween(0, 2, 1);</pre>
     *
     * @param <T>   the type of the argument object
     * @param start the inclusive start value, not null
     * @param end   the inclusive end value, not null
     * @param value the object to validate, not null
     * @throws IllegalArgumentException if the value falls outside the boundaries
     * @since 3.0
     */
    public static <T> void inclusiveBetween(final T start, final T end, final Comparable<T> value) {
        // TODO when breaking BC, consider returning value
        if (value.compareTo(start) < 0 || value.compareTo(end) > 0) {
            throw new IllegalArgumentException(String.format(DEFAULT_INCLUSIVE_BETWEEN_EX_MESSAGE, value, start, end));
        }
    }

    /**
     * <p>Validate that the specified argument character sequence matches the specified regular
     * expression pattern; otherwise throwing an exception.</p>
     *
     * <pre>Validate.matchesPattern("hi", "[a-z]*");</pre>
     *
     * <p>The syntax of the pattern is the one used in the {@link Pattern} class.</p>
     *
     * @param input   the character sequence to validate, not null
     * @param pattern the regular expression pattern, not null
     * @throws IllegalArgumentException if the character sequence does not match the pattern
     * @see #matchesPattern(CharSequence, String, String, Object...)
     * @since 3.0
     */
    public static void matchesPattern(final CharSequence input, final String pattern) {
        // TODO when breaking BC, consider returning input
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(String.format(DEFAULT_MATCHES_PATTERN_EX, input, pattern));
        }
    }

    /**
     * <p>Validate that the specified argument character sequence matches the specified regular
     * expression pattern; otherwise throwing an exception with the specified message.</p>
     *
     * <pre>Validate.matchesPattern("hi", "[a-z]*", "%s does not match %s", "hi" "[a-z]*");</pre>
     *
     * <p>The syntax of the pattern is the one used in the {@link Pattern} class.</p>
     *
     * @param input   the character sequence to validate, not null
     * @param pattern the regular expression pattern, not null
     * @param message the {@link String#format(String, Object...)} exception message if invalid, not null
     * @param values  the optional values for the formatted exception message, null array not recommended
     * @throws IllegalArgumentException if the character sequence does not match the pattern
     * @see #matchesPattern(CharSequence, String)
     * @since 3.0
     */
    public static void matchesPattern(final CharSequence input, final String pattern, final String message, final Object... values) {
        // TODO when breaking BC, consider returning input
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

//    @Contract(value = "null, _ -> fail", pure = true)
//    public static <T> T notNull(@Nullable final T object, String name) {
//        if (object == null) {
//            throw new NullPointerException(name + " cannot be null");
//        }
//        return object;
//    }

    /**
     * Throws a NullPointerException if the object is null
     * @param object Object
     * @param name Name of the object
     * @return Object
     * @param <T> Type of the object
     */
    @NotNull
    @Contract(value = "null, _ -> fail; _, _ -> param1", pure = true)
    public static  <T> T paramNotNull(@Nullable T object, String name) throws ParameterCannotBeNullException {
        if(object != null) {
            return object;
        } else {
            throw new ParameterCannotBeNullException(name);
        }
    }

}

