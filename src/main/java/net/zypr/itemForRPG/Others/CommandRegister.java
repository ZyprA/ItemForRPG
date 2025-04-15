package net.zypr.itemForRPG.Others;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import net.zypr.itemForRPG.CustomItem.CustomItem;
import net.zypr.itemForRPG.ItemForRPG;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRegister {
    public static void load() {
        new CommandAPICommand("itemforrpg")
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission(CommandPermission.OP)
                        .executes((sender, args) -> {
                            sender.sendMessage("§eReloading ItemForRPG...");
                            ItemForRPG.getCustomItemManager().loadFromConfig();
                            sender.sendMessage("§aReloaded ItemForRPG!");
                        }))
                .withSubcommand(new CommandAPICommand("give")
                        .withPermission(CommandPermission.OP)
                        .withArguments(new StringArgument("id")
                                .replaceSuggestions(
                                        ArgumentSuggestions.strings(ItemForRPG.getCustomItemManager().getCustomItems().keySet())
                                )
                        )
                        .executes((sender, args) -> {
                            if (!(sender instanceof Player player)) {
                                sender.sendMessage("§cThis command can only be run by a player.");
                                return;
                            }
                            if (args.get("id") == null) {
                                player.sendMessage("§cYou must specify an id.");
                                return;
                            }
                            String id = String.valueOf(args.get("id"));
                            CustomItem customItem = ItemForRPG.getCustomItemManager().getCustomItem(id);
                            if (customItem == null) {
                                player.sendMessage("§cThe item with id " + id + " does not exist.");
                                return;
                            }
                            player.getInventory().addItem(customItem.getItemStack());
                            player.sendMessage("§aYou have been given the item " + customItem.getName() + "!");
                        })
                )
                .withSubcommand(new CommandAPICommand("info")
                        .withPermission(CommandPermission.OP)
                        .withArguments(new StringArgument("id")
                                .replaceSuggestions(
                                        ArgumentSuggestions.strings(ItemForRPG.getCustomItemManager().getCustomItems().keySet())
                                )
                        )
                        .executes((sender, args) -> {
                            if (args.get("id") == null) {
                                sender.sendMessage("§cYou must specify an id.");
                                return;
                            }
                            String id = String.valueOf(args.get("id"));
                            CustomItem customItem = ItemForRPG.getCustomItemManager().getCustomItem(id);
                            if (customItem == null) {
                                sender.sendMessage("§cThe item with id " + id + " does not exist.");
                                return;
                            }
                            sender.sendMessage("§aItem Information for ID: " + id);
                            sender.sendMessage("§aName: " + customItem.getName());
                            sender.sendMessage("§aMaterial: " + customItem.getMaterial().name());
                            sender.sendMessage("§aLore: ");
                            for (String lore : customItem.getLore()) {
                                sender.sendMessage("- " + lore);
                            }
                            sender.sendMessage("§aPlayer Commands: ");
                            for (String command : customItem.getPlayerCommands()) {
                                sender.sendMessage("- " + command);
                            }
                            sender.sendMessage("§aConsole Commands: ");
                            for (String command : customItem.getConsoleCommands()) {
                                sender.sendMessage("- " + command);
                            }
                        })
                )
                .executes((sender, args) -> {
                    sender.sendMessage(getCommandList());
                })
                .register();
    }

    private static String getCommandList() {
        return """
                §6Command Help for ItemForRPG:
                §a/itemforrpg reload §7- Reload the config
                §a/itemforrpg give <id> §7- Give the item with the specified id
                §a/itemforrpg info <id> §7- Get information about the item with the specified id
                """;
    }

    private static Map<String, List<String>> parseDSL(String input) {
        Map<String, List<String>> result = new HashMap<>();

        Pattern pattern = Pattern.compile("(\\w+)=\\\"(.*?)\\\"");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String key = matcher.group(1).toLowerCase();
            String value = matcher.group(2);
            result.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
        return result;
    }

    private String getLastToken(String input) {
        String[] tokens = input.split(" ");
        return tokens[tokens.length - 1]; // 最後のトークンを返す
    }
}
