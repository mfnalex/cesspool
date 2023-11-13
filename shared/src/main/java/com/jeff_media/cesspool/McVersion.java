package com.jeff_media.cesspool;


import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents comparable Minecraft versions.
 */
public class McVersion implements Comparable<McVersion> {

    /**
     * 1.18.1 - added {@link org.bukkit.profile.PlayerProfile}
     */
    public static final McVersion V1_18_1 = new McVersion(1, 18, 1);

    private static final McVersion CURRENT_VERSION;
    //private static final McVersion v1_17 = new McVersion(1, 17);

    static {
        final int currentMajor = Integer.parseInt(Bukkit.getBukkitVersion().split("\\.")[0]);
        final int currentMinor = Integer.parseInt(Bukkit.getBukkitVersion().split("\\.")[1].split("-")[0]);
        boolean hasPatch = Bukkit.getBukkitVersion().chars().filter(ch -> ch == '.').count() == 3;
        final int currentPatch = hasPatch ? Integer.parseInt(Bukkit.getBukkitVersion().split("\\.")[2].split("-")[0]) : 0;

        CURRENT_VERSION = new McVersion(currentMajor, currentMinor, currentPatch);
    }

    private final int major;
    private final int minor;
    private final int patch;

    /**
     * Creates a new McVersion
     * @param major The major version
     * @param minor The minor version
     */
    public McVersion(final int major, final int minor) {
        this(major, minor, 0);
    }

    /**
     * Creates a new McVersion
     * @param major The major version
     * @param minor The minor version
     * @param patch The patch version
     */
    public McVersion(final int major, final int minor, final int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * Gets the currently running McVersion
     *
     * @return The currently running McVersion
     */
    public static McVersion current() {
        return CURRENT_VERSION;
    }

    /**
     * Returns true if this McVersion is at least the specified McVersion
     * @param other McVersion to compare to
     * @return true if this McVersion is at least the specified McVersion
     */
    public boolean isAtLeast(final McVersion other) {
        return this.compareTo(other) >= 0;
    }

    @Override
    public int compareTo(@NotNull final McVersion other) {
        if (this.major > other.major) return 3;
        if (other.major > this.major) return -3;
        if (this.minor > other.minor) return 2;
        if (other.minor > this.minor) return -2;
        return Integer.compare(this.patch, other.patch);
    }

    /**
     * Gets the "major" part of this McVersion. For 1.16.5, this would be 1
     *
     * @return The major part of this McVersion
     */
    public int getMajor() {
        return major;
    }

    /**
     * Gets the "minor" part of this McVersion. For 1.16.5, this would be 16
     *
     * @return The minor part of this McVersion
     */
    public int getMinor() {
        return minor;
    }

    /**
     * Gets the "patch" part of this McVersion. For 1.16.5, this would be 5.
     *
     * @return The patch part of this McVersion
     */
    public int getPatch() {
        return patch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final McVersion mcVersion = (McVersion) o;
        return major == mcVersion.major && minor == mcVersion.minor && patch == mcVersion.patch;
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Gets the name of this McVersion, e.g. "1.16.5" or "1.20"
     *
     * @return The name of this McVersion
     */
    public String getName() {
        if (patch == 0) {
            return major + "." + minor;
        } else {
            return major + "." + minor + "." + patch;
        }
    }

    /**
     * Returns true if this McVersion is at least the specified McVersion
     * @param major The major version
     * @param minor The minor version
     * @param patch The patch version
     * @return true if this McVersion is at least the specified McVersion
     */
    public boolean isAtLeast(final int major, final int minor, final int patch) {
        return this.isAtLeast(new McVersion(major, minor, patch));
    }

    /**
     * Returns true if this McVersion is at least the specified McVersion
     * @param major The major version
     * @param minor The minor version
     * @return true if this McVersion is at least the specified McVersion
     */
    public boolean isAtLeast(final int major, final int minor) {
        return this.isAtLeast(new McVersion(major, minor));
    }

}
