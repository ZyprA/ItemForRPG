package net.zypr.itemForRPG.CustomItem;

import net.zypr.itemForRPG.util.NBTAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * A utility class for creating custom items in the RPG system.
 * This class provides methods to define and generate custom items
 * with specific properties such as material, name, lore, amount, and NBT data.
 */
public class CustomItemCreator {

    /**
     * The material type of the custom item.
     */
    private Material material;

    /**
     * The amount of the custom item to be created.
     */
    private int amount;

    /**
     * The unique identifier for the custom item.
     */
    private String id;

    /**
     * The display name of the custom item.
     */
    private String name;

    /**
     * The lore (description) of the custom item.
     */
    private List<String> lore;

    /**
     * The NBT (Named Binary Tag) data associated with the custom item.
     */
    private Map<String, String> nbt;

    /**
     * Constructs a new CustomItemCreator with default values.
     * The default values are:
     * - material: null
     * - name: null
     * - lore: null
     * - nbt: null
     * - amount: 1
     * - id: null
     */
    public CustomItemCreator() {
        this.material = null;
        this.name = null;
        this.lore = null;
        this.nbt = null;
        this.amount = 1;
        this.id = null;
    }

    /**
     * Generates an ItemStack representation of the custom item.
     * This method applies the specified properties such as material, name, lore,
     * amount, and NBT data to the generated ItemStack.
     *
     * @return The generated ItemStack with the specified properties.
     */
    public ItemStack generate() {
        ItemStack itemStack = new ItemStack(this.material, this.amount);
        if (this.id != null) {
            NBTAPI.addNBT(itemStack, "id", this.id);
        }
        if (this.nbt != null) {
            for (Map.Entry<String, String> entry : this.nbt.entrySet()) {
                NBTAPI.addNBT(itemStack, entry.getKey(), entry.getValue());
            }
        }
        NBTAPI.addNBT(itemStack, "itemId", UUID.randomUUID().toString());
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(this.name);
            meta.setLore(this.lore);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    /**
     * Sets the unique identifier for the custom item.
     *
     * @param id The unique identifier to set.
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Resets all properties of the custom item to their default values.
     * The default values are:
     * - material: null
     * - name: null
     * - lore: null
     * - nbt: null
     * - amount: 1
     * - id: null
     *
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator reset() {
        this.material = null;
        this.name = null;
        this.lore = null;
        this.nbt = null;
        this.amount = 1;
        this.id = null;
        return this;
    }

    /**
     * Sets the material type of the custom item.
     *
     * @param material The material type to set.
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Sets the amount of the custom item to be created.
     *
     * @param amount The amount to set.
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Sets the display name of the custom item.
     *
     * @param name The display name to set.
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the lore (description) of the custom item.
     *
     * @param lore The lore to set.
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Sets the NBT (Named Binary Tag) data for the custom item.
     *
     * @param nbt A map containing the NBT key-value pairs to set.
     * @return The current instance of CustomItemCreator for method chaining.
     */
    public CustomItemCreator setNbt(Map<String, String> nbt) {
        this.nbt = nbt;
        return this;
    }
}