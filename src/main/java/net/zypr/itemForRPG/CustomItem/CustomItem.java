package net.zypr.itemForRPG.CustomItem;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Represents a custom item in the RPG system.
 * This class provides functionality to define and generate custom items
 * with specific properties such as name, material, lore, and commands.
 */
@Getter
@Setter
public class CustomItem {

    /**
     * The unique identifier for the custom item.
     */
    private String id;

    /**
     * The display name of the custom item.
     */
    private String name;

    /**
     * The material type of the custom item.
     */
    private Material material;

    /**
     * The lore (description) of the custom item.
     */
    private List<String> lore;

    /**
     * The list of commands executed by the player when using the item.
     */
    private List<String> playerCommands;

    /**
     * The list of commands executed by the console when the item is used.
     */
    private List<String> consoleCommands;

    /**
     * Constructs a new CustomItem with the specified properties.
     *
     * @param id              The unique identifier for the item.
     * @param name            The display name of the item.
     * @param material        The material type of the item.
     * @param lore            The lore (description) of the item.
     * @param playerCommands  The commands executed by the player.
     * @param consoleCommands The commands executed by the console.
     */
    public CustomItem(String id, String name, Material material, List<String> lore, List<String> playerCommands, List<String> consoleCommands) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.lore = lore;
        this.playerCommands = playerCommands;
        this.consoleCommands = consoleCommands;
    }

    /**
     * Generates an ItemStack representation of the custom item.
     *
     * @return The generated ItemStack.
     */
    public ItemStack getItemStack() {
        CustomItemCreator itemCreator = new CustomItemCreator();
        return itemCreator.setId(this.id)
                .setMaterial(this.material)
                .setName(this.name)
                .setLore(this.lore)
                .setAmount(1)
                .generate();
    }
}
