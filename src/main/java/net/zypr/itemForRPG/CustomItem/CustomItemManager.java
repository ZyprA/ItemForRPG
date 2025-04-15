package net.zypr.itemForRPG.CustomItem;

import lombok.Getter;
import net.zypr.itemForRPG.ItemForRPG;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Manages the loading and retrieval of custom items in the RPG system.
 * This class handles the configuration files and provides access to
 * {@link CustomItem} instances based on their unique identifiers.
 */
@Getter
public class CustomItemManager {

    private final Map<String, CustomItem> customItems = new HashMap<>();

    /**
     * Loads custom items from the configuration files specified in the plugin's main configuration.
     * Clears the current custom items before loading new ones.
     */
    public void loadFromConfig() {
        customItems.clear();
        ItemForRPG.getInstance().saveDefaultConfig();

        List<String> fileNames = ItemForRPG.getInstance().getConfig().getStringList("files");

        for (String fileName : fileNames) {
            String path = ItemForRPG.getInstance().getDataFolder() + File.separator + fileName;
            FileConfiguration config = YamlConfiguration.loadConfiguration(new File(path));
            loadFromConfig(config);
        }
    }

    /**
     * Loads custom items from a specific configuration file.
     *
     * @param file The {@link FileConfiguration} containing custom item definitions.
     */
    public void loadFromConfig(FileConfiguration file) {
        ItemForRPG.getInstance().getLogger().info(file.getKeys(false).toString());
        for (String id : file.getKeys(false)) {
            ConfigurationSection sections = file.getConfigurationSection(id);
            if (sections == null) {
                continue;
            }
            String name = sections.getString("name", "§cUnknown Item");
            Material material = Material.getMaterial(sections.getString("material", "AIR"));
            String[] loreArray = sections.getStringList("lore").toArray(new String[0]);
            int cooldown = sections.getInt("cooldown", 0);
            String[] playerCommands = sections.getStringList("commands.player").toArray(new String[0]);
            String[] consoleCommands = sections.getStringList("commands.console").toArray(new String[0]);
            CustomItem customItem = new CustomItem(id, name, material, List.of(loreArray),cooldown , List.of(playerCommands), List.of(consoleCommands));
            customItems.put(id, customItem);
        }
    }

    /**
     * Retrieves a custom item by its unique identifier.
     *
     * @param id The unique identifier of the custom item.
     * @return The {@link CustomItem} associated with the given ID, or null if not found.
     */
    public CustomItem getCustomItem(@NotNull String id) {
        if (!customItems.containsKey(id)) {
            return null;
        }
        return customItems.get(id);
    }

}