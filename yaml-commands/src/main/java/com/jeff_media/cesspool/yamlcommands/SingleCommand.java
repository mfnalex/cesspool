package com.jeff_media.cesspool.yamlcommands;

public class YamlCommand {

    private final String command;
    private final CommandSender sender;

    public YamlCommand(String command, CommandSender sender) {
        this.command = command;
        this.sender = sender;
    }

    public enum CommandSender {
        CONSOLE,
        PLAYER
    }

    public boolean run()

}
