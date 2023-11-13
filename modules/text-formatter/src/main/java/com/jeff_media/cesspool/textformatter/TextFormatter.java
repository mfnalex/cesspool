package com.jeff_media.cesspool.textformatter;

import com.jeff_media.cesspool.textformatting.ItemsAdderEmojiReplacer;
import com.jeff_media.cesspool.textformatting.PapiReplacer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Formats text by applying PAPI placeholders, converting color codes, applying ItemsAdder Emojis, etc.
 */
public class TextFormatter {

    /**
     * Formats the given text by applying PAPI placeholders, converting color codes, and applying ItemsAdder Emojis.
     * @param player Player to apply PAPI placeholders or ItemsAdder emojis for. Can be null
     * @param input Text to format
     * @return Formatted text
     */
    public static String format(final @Nullable OfflinePlayer player, final @NotNull String input) {
        String text = input;

        Player onlinePlayer = player == null ? null : player.getPlayer();

        text = ItemsAdderEmojiReplacer.replaceEmojis(onlinePlayer, text);
        text = PapiReplacer.apply(text, player);
        text = colorize(text);

        return text;
    }

    /**
     * Converts color codes
     * @param text Text to convert
     * @return Text with color codes converted
     * // TODO: Document how hex colors and gradients work, also support MiniMessage
     */
    private static String colorize(String text) {
        return LegacyTextFormatter.color(text);
    }

    /**
     * Replaces placeholders in a list of Strings.
     *
     * @see #replaceInString(String, Map)
     */
    public static List<String> replaceInString(final List<String> strings, final Map<String, String> placeholders) {
        strings.replaceAll(string -> replaceInString(string, placeholders));
        return strings;
    }

    /**
     * Replaces placeholders in a String. Example:
     * <pre>
     * Map&lt;String,String> placeholders = new HashMap&lt;>();
     * placeholders.put("{player}",player.getName());
     * placeholders.put("{killer}",killer.getName());
     * String result = replaceInString("{player} was killed by {killer}.",placeholders);
     * </pre>
     */
    public static String replaceInString(String string, final Map<String, String> placeholders) {
        for (final Map.Entry<String, String> entry : placeholders.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) continue;
            string = string.replace(entry.getKey(), entry.getValue());
        }
        return string;
    }

    /**
     * Replaces placeholders in a list of Strings.
     *
     * @see #replaceInString(String, String...)
     */
    public static List<String> replaceInString(final List<String> strings, final String... placeholders) {
        strings.replaceAll(string -> replaceInString(string, placeholders));
        return strings;
    }

    /**
     * Replaces placeholders in a String. Example:
     * <pre>
     * String result = replaceInString("{name} is {age} years old.",
     *      "{name}", "mfnalex",
     *      "{age}","27"
     * );
     * </pre>
     */
    public static String replaceInString(String string, final String... placeholders) {
        if (placeholders.length % 2 != 0) {
            throw new IllegalArgumentException("placeholders must have an even length");
        }
        for (int i = 0; i < placeholders.length; i += 2) {
            if (placeholders[i] == null || placeholders[i + 1] == null) continue;
            string = string.replace(placeholders[i], placeholders[i + 1]);
        }
        return string;
    }


}
