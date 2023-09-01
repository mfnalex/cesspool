package com.jeff_media.cesspool.yamlcommands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Represents a list of commands to be executed
 */
public class CommandList implements PlayerExecutable {

    /**
     * An empty command list
     */
    public static final CommandList EMPTY = new CommandList(Collections.emptyList());

    @NotNull
    private final List<SingleCommand> commands;

    CommandList(@NotNull List<SingleCommand> commands) {
        this.commands = Objects.requireNonNull(commands);
    }

    static CommandList of(@NotNull String command, @NotNull CommandSenderType type) {
        return new CommandList(Collections.singletonList(SingleCommand.of(command, type)));
    }

    /**
     * Creates a new CommandList from a path in a ConfigurationSection. The object at the path can be a String, a ConfigurationSection, or a List of Strings or ConfigurationSections.
     * @param config ConfigurationSection to read from
     * @param path Path to read from
     * @return CommandList
     */
    @SuppressWarnings("unchecked")
    public static CommandList of(@NotNull final ConfigurationSection config, @NotNull final String path) {
        Objects.requireNonNull(config, "Config cannot be null");
        Objects.requireNonNull(path, "Path cannot be null");
        final List<SingleCommand> commands = new ArrayList<>();
        if (config.isConfigurationSection(path)) {
            commands.add(SingleCommand.of(Objects.requireNonNull(config.getConfigurationSection(path))));
        } else if (config.isString(path)) {
            commands.add(SingleCommand.of(Objects.requireNonNull(config.getString(path)), CommandSenderType.CONSOLE));
        } else if (config.isList(path)) {
            List<?> list = Objects.requireNonNull(config.getList(path));
            for (Object item : list) {
                if (item instanceof String) {
                    commands.add(SingleCommand.of(Objects.requireNonNull((String) item)));
                } else if (item instanceof ConfigurationSection) {
                    commands.add(SingleCommand.of(Objects.requireNonNull((ConfigurationSection) item)));
                } else if (item instanceof Map<?, ?>) {
                    commands.add(SingleCommand.of((Map<String, Object>) Objects.requireNonNull(item)));
                } else {
                    throw new IllegalArgumentException("Invalid command list: " + config.get(path));
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid command list: " + config.get(path));
        }
        return new CommandList(commands);
    }

    @Override
    public String toString() {
        return "CommandList{" +
                "commands=" + commands +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandList that = (CommandList) o;
        return Objects.equals(commands, that.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commands);
    }

    @Override
    public boolean run(@Nullable Player player) throws IllegalArgumentException {
        boolean success = true;
        for (SingleCommand command : commands) {
            success &= command.run(player);
        }
        return success;
    }
}
