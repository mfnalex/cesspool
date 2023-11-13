package com.jeff_media.cesspool.textformatter;

/*
 * Copyright (c) 2023. JEFF Media GbR / mfnalex et al.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


import java.util.Objects;

import com.jeff_media.cesspool.Validate;
import org.bukkit.ChatColor;

/**
 * Represents a hex color code
 */
public final class LegacyHexColor {

    private static final String REGEX_COLOR_COMPONENT = "[0-9a-zA-Z][0-9a-zA-Z]";

    private int red;
    private int green;
    private int blue;

    /**
     * Creates a HexColor with the given RGB value
     *
     * @param red   Red (0-255)
     * @param green Green (0-255)
     * @param blue  Blue (0-255)
     */
    public LegacyHexColor(final int red, final int green, final int blue) {
        Validate.inclusiveBetween(0, 255, red);
        Validate.inclusiveBetween(0, 255, green);
        Validate.inclusiveBetween(0, 255, blue);
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
    }

    /**
     * Creates a HexColor from a String in the format "rrggbb" (e.g. 00ff00)
     *
     * @param hex Color in hex
     */
    public LegacyHexColor(final String hex) {
        this(hex.substring(0, 2), hex.substring(2, 4), hex.substring(4, 6));
    }

    /**
     * Creates a HexColor from three separate hex color Strings
     *
     * @param red   Red (00-ff)
     * @param green Green (00-ff)
     * @param blue  Blue (00-ff)
     */
    public LegacyHexColor(final String red, final String green, final String blue) {
        Validate.matchesPattern(red, REGEX_COLOR_COMPONENT);
        Validate.matchesPattern(green, REGEX_COLOR_COMPONENT);
        Validate.matchesPattern(blue, REGEX_COLOR_COMPONENT);
        this.setRed(Integer.parseInt(red, 16));
        this.setGreen(Integer.parseInt(green, 16));
        this.setBlue(Integer.parseInt(blue, 16));
    }

    /**
     * Applies a gradient to the given text. Also preserves already existing formatting codes inside the text.
     *
     * @param text  Text to apply gradient to
     * @param start Starting color
     * @param end   End color
     * @return Text with color codes applied to match the given gradient
     */
    public static String applyGradient(final String text, final LegacyHexColor start, final LegacyHexColor end) {
        final char[] chars = text.toCharArray();
        final int length = text.length();
        final StringBuilder sb = new StringBuilder();
        String nextFormat = "";
        for (int i = 0; i < length; i++) {
            //System.out.println("Current char: " + chars[i]);
            if (nextFormat.length() % 2 == 1) {
                if (chars[i] == 'r' || chars[i] == 'R') {
                    //System.out.println("Formatting cleared");
                    sb.append(ChatColor.translateAlternateColorCodes('&', "&r"));
                    nextFormat = "";
                    continue;
                }
                //System.out.println("Found format: " + chars[i]);
                nextFormat = nextFormat + chars[i];
                continue;
            }
            if (chars[i] == '&' || chars[i] == '§') {
                nextFormat = nextFormat + "&";
                continue;
            }
            //System.out.println("Next formatting: " + nextFormat);
            sb.append(getHexAtPositionInGradient(start, end, length, i).toColorCode()).append(ChatColor.translateAlternateColorCodes('&', nextFormat)).append(chars[i]);
            //nextFormat="";
        }
        return sb.toString();
    }

    /**
     * Returns the HexColor for a given gradient at the given position
     *
     * @param start     Starting color
     * @param end       End color
     * @param textLengh Text lengh
     * @param position  Position inside the text
     * @return HexColor needed at this position
     */
    public static LegacyHexColor getHexAtPositionInGradient(final LegacyHexColor start, final LegacyHexColor end, final int textLengh, final int position) {
        //System.out.println("Position " + position);
        if (position == 0) return start;
        if (position == textLengh - 1) return end;
        final int colorsNeeded = textLengh - 1;
        final int r = getSingleValueAtPositionInGradient(start.getRed(), end.getRed(), colorsNeeded, position);
        final int g = getSingleValueAtPositionInGradient(start.getGreen(), end.getGreen(), colorsNeeded, position);
        final int b = getSingleValueAtPositionInGradient(start.getBlue(), end.getBlue(), colorsNeeded, position);
        //System.out.println(result.toHex());
        return new LegacyHexColor(r, g, b);
    }

    /**
     * Returns the value for a specific color component at a given location inside a gradient
     *
     * @param start        Color component value at the gradient's start
     * @param end          Color component value at the gradient's end
     * @param colorsNeeded The amount of different colors needed for this gradient. This is usually the text length - 1
     * @param position     Position to get the color component value for
     * @return Color component value at the given position (0-255)
     */
    private static int getSingleValueAtPositionInGradient(final int start, final int end, final int colorsNeeded, final int position) {
        if (position == 0) return start;
        if (position == colorsNeeded) return end;
        if (start == end) return start;
        final int diff = start - end;
        return start - (diff / colorsNeeded) * position;
    }

    /**
     * Converts the HexColor into color codes readable by {@link ChatColor#translateAlternateColorCodes(char, String)}, using the ampersand (&amp;) as alternate color character
     *
     * @return HexColor as color code, e.g. "&amp;x&amp;0&amp;0&amp;f&amp;f&amp;0&amp;0"
     */
    public String toColorCode() {
        final StringBuilder sb = new StringBuilder("&x");
        final char[] chars = toHex().toCharArray();
        for (final Character aChar : chars) {
            sb.append('&').append(aChar);
        }
        return sb.toString();
    }

    /**
     * Returns the HexColor as a String in the format rrggbb (e.g. 00ff00)
     *
     * @return HexColor as String
     */
    public String toHex() {
        return String.format("%02x", getRed()) + String.format("%02x", getGreen()) + String.format("%02x", getBlue());
    }

    /**
     * Returns the Red color component value
     *
     * @return Red color component value (0-255)
     */
    public int getRed() {
        return red;
    }


    /**
     * Sets the Red color component value
     *
     * @param red Red color component value (0-255)
     */
    public void setRed(final int red) {
        Validate.inclusiveBetween(0, 255, red);
        this.red = red;
    }

    /**
     * Returns the Green color component value
     *
     * @return Red green component value (0-255)
     */
    public int getGreen() {
        return green;
    }

    /**
     * Sets the Green color component value
     *
     * @param green Green color component value (0-255)
     */
    public void setGreen(final int green) {
        Validate.inclusiveBetween(0, 255, green);
        this.green = green;
    }

    /**
     * Returns the Blue color component value
     *
     * @return Blue color component value (0-255)
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Sets the Blue color component value
     *
     * @param blue Blue color component value (0-255)
     */
    public void setBlue(final int blue) {
        Validate.inclusiveBetween(0, 255, blue);
        this.blue = blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LegacyHexColor hexColor = (LegacyHexColor) o;
        return red == hexColor.red && green == hexColor.green && blue == hexColor.blue;
    }

    @Override
    public String toString() {
        return "LegacyHexColor{" + "r=" + red + ", g=" + green + ", b=" + blue + '}';
    }
}

