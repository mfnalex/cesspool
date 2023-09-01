package com.jeff_media.cesspool.yamlcommands;

import com.jeff_media.cesspool.papireplacer.PapiReplacer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

class SingleCommand implements PlayerExecutable {

    @NotNull
    private final List<String> command;
    @NotNull
    private final CommandSenderType senderType;

    SingleCommand(@NotNull List<String> command, @NotNull CommandSenderType senderType) {
        this.command = Objects.requireNonNull(command, "Command cannot be null");
        this.senderType = Objects.requireNonNull(senderType, "Sender type cannot be null");
    }

    static SingleCommand of(@NotNull String command) {
        return of(command, CommandSenderType.CONSOLE);
    }

    static SingleCommand of(@NotNull String command, @NotNull CommandSenderType commandSenderType) {
        return new SingleCommand(Collections.singletonList(command), commandSenderType);
    }

    static SingleCommand of(@NotNull Map<String, Object> map) {
        List<String> commands = new ArrayList<>();

        Object command = map.get("command");
        if (command instanceof String) {
            commands.add((String) command);
        } else if (command instanceof List<?>) {
            for (Object item : (List<?>) command) {
                if (item instanceof String) {
                    commands.add((String) item);
                } else {
                    throw new IllegalArgumentException("Invalid command: " + item);
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid command: " + command);
        }


        String senderTypeString = (String) map.getOrDefault("sender", "console");
        CommandSenderType type;

        if (senderTypeString.equalsIgnoreCase("console")) {
            type = CommandSenderType.CONSOLE;
        } else if (senderTypeString.equalsIgnoreCase("player")) {
            type = CommandSenderType.PLAYER;
        } else {
            throw new IllegalArgumentException("Invalid sender type: " + senderTypeString);
        }

        if (commands.isEmpty()) {
            throw new IllegalArgumentException("Missing command");
        }

        return new SingleCommand(commands, type);
    }

    static SingleCommand of(@NotNull ConfigurationSection section) {
        return of(section.getValues(false));
    }

    @Override
    public String toString() {
        return "SingleCommand{" +
                "command='" + command + '\'' +
                ", senderType=" + senderType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleCommand that = (SingleCommand) o;
        return Objects.equals(command, that.command) && senderType == that.senderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, senderType);
    }

    @Override
    public boolean run(@Nullable Player player) throws IllegalArgumentException {
        CommandSender sender = Bukkit.getConsoleSender();
        if (senderType == CommandSenderType.PLAYER) {
            if (player == null) {
                throw new IllegalArgumentException("Player cannot be null when sender type is PLAYER");
            }
            sender = player;
        }
        boolean success = true;
        for (String command : command) {
            success &= Bukkit.dispatchCommand(sender, PapiReplacer.apply(command, player));
        }
        return success;
    }

}
