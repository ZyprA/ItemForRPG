package net.zypr.itemForRPG.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for building and executing commands with placeholders.
 * This class allows you to define a command, set placeholders, and execute
 * the command with the placeholders replaced by their respective values.
 */
public class ECommandAPI {

    /**
     * The base command to be executed.
     */
    private final String command;

    /**
     * A map of placeholders and their corresponding replacement values.
     */
    private final Map<String, String> placeholders = new HashMap<>();

    /**
     * Constructs a new ECommandAPI instance with the specified command.
     *
     * @param command The base command to be executed.
     */
    public ECommandAPI(String command) {
        this.command = command;
    }

    /**
     * Sets a placeholder and its replacement value.
     *
     * @param key   The placeholder key (without the '%' symbols).
     * @param value The value to replace the placeholder with.
     * @return The current instance of ECommandAPI for method chaining.
     */
    public ECommandAPI set(String key, String value) {
        placeholders.put(key, value);
        return this;
    }

    /**
     * Executes the command as the console sender.
     */
    public void run() {
        run(Bukkit.getConsoleSender());
    }

    /**
     * Executes the command as the specified sender.
     *
     * @param sender The sender who will execute the command.
     */
    public void run(CommandSender sender) {
        String finalCommand = buildCommand();
        Bukkit.dispatchCommand(sender, finalCommand);
    }

    /**
     * Builds the final command by replacing placeholders with their values.
     *
     * @return The command with all placeholders replaced.
     */
    private String buildCommand() {
        String finalCommand = command;
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            finalCommand = finalCommand.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        return finalCommand;
    }
}
