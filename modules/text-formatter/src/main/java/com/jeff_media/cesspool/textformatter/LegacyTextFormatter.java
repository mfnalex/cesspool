package com.jeff_media.cesspool.textformatter;




import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;

/**
 * Methods related to color translation, placeholder and emoji application and more
 */
public class LegacyTextFormatter {

    private static final String REGEX_HEX = "[\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F]";

    private static final String REGEX_HEX_GRADIENT = "<#([\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F])>(.*?)<#/([\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F])>";
    private static final Pattern PATTERN_HEX_GRADIENT = Pattern.compile(REGEX_HEX_GRADIENT);

    private static final String REGEX_AMPERSAND_HASH = "&#(" + REGEX_HEX + ")";
    private static final Pattern PATTERN_AMPERSAND_HASH = Pattern.compile(REGEX_AMPERSAND_HASH);
    private static final String REGEX_XML_LIKE_HASH = "<#(" + REGEX_HEX + ")>";
    private static final Pattern PATTERN_XML_LIKE_HASH = Pattern.compile(REGEX_XML_LIKE_HASH);



    /**
     * Replaces color codes using &amp;. Also supports hex colors using <pre>&amp;x&amp;r&amp;r&amp;g&amp;g&amp;b&amp;b</pre>, <pre>&amp;#rrggbb</pre> and <pre>&lt;#rrggbb></pre>,
     * and gradients using <pre>&lt;#rrggbb> &lt;#/rrggbb></pre>
     *
     * @param text Text to translate
     * @return Translated text
     */
    public static String color(String text) {
        text = text.replace("&&", "{ampersand}");
        text = replaceGradients(text);
        text = replaceRegexWithGroup(text, PATTERN_XML_LIKE_HASH, 1, LegacyTextFormatter::addAmpersandsToHex);
        text = replaceRegexWithGroup(text, PATTERN_AMPERSAND_HASH, 1, LegacyTextFormatter::addAmpersandsToHex);
        text = ChatColor.translateAlternateColorCodes('&', text);
        text = text.replace("{ampersand}", "&");
        return text;
    }

    private static String replaceGradients(String text) {

        text = text.replaceAll("<#/([\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F][\\da-fA-F])>", "<#/$1><#$1>");

        final Matcher matcher = PATTERN_HEX_GRADIENT.matcher(text);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            final LegacyHexColor startColor = new LegacyHexColor(matcher.group(1));
            final LegacyHexColor endColor = new LegacyHexColor(matcher.group(3));
            final String partText = matcher.group(2);
            matcher.appendReplacement(sb, LegacyHexColor.applyGradient(partText, startColor, endColor));
        }
        matcher.appendTail(sb);
        String result = sb.toString();
        while (result.matches(".*&x&[\\da-zA-Z]&[\\da-zA-Z]&[\\da-zA-Z]&[\\da-zA-Z]&[\\da-zA-Z]&[\\da-zA-Z]$")) {
            result = result.substring(0, result.length() - 14);
        }
        return result;
    }

    @SuppressWarnings("SameParameterValue")
    private static String replaceRegexWithGroup(final CharSequence text, final Pattern pattern, final int group, final Function<String, String> function) {
        final Matcher matcher = pattern.matcher(text);
        final StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, function.apply(matcher.group(group)));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String addAmpersandsToHex(final String hex) {
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Hex-Codes must always have 6 letters.");
        }
        final char[] chars = hex.toCharArray();
        final StringBuilder sb = new StringBuilder("&x");
        for (final char aChar : chars) {
            sb.append("&").append(aChar);
        }
        return sb.toString();
    }

}
