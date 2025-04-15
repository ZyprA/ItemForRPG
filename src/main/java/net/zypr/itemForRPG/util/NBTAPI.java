package net.zypr.itemForRPG.util;

import net.zypr.itemForRPG.ItemForRPG;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class NBTAPI {

    public static @Nullable String getNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(ItemForRPG.getInstance(), "Plugin hasn't been initialized.");
        Objects.requireNonNull(item, "item must not be null");
        if (!item.hasItemMeta()) return null;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta != null ? meta.getPersistentDataContainer() : null;
        NamespacedKey namespacedKey = new NamespacedKey(ItemForRPG.getInstance(), key);
        if (pdc != null && pdc.has(namespacedKey, PersistentDataType.STRING)) {
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public static void addNBT(@NotNull ItemStack item, String key, String value) {
        Objects.requireNonNull(ItemForRPG.getInstance(), "Plugin hasn't been initialized.");
        Objects.requireNonNull(item, "item must not be null");
        ItemMeta meta = item.hasItemMeta() ? item.getItemMeta() : Bukkit.getItemFactory().getItemMeta(item.getType());
        PersistentDataContainer pdc = meta != null ? meta.getPersistentDataContainer() : null;
        NamespacedKey namespacedKey = new NamespacedKey(ItemForRPG.getInstance(), key);
        if (pdc != null) {
            pdc.set(namespacedKey, PersistentDataType.STRING, value);
        }
        item.setItemMeta(meta);
    }

    @SuppressWarnings("unused")
    public static void addNBT(@NotNull Entity entity, String key, String value) {
        Objects.requireNonNull(ItemForRPG.getInstance(), "Plugin hasn't been initialized.");
        Objects.requireNonNull(entity, "entity must not be null");
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(ItemForRPG.getInstance(), key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
    }

    public static boolean hasNBT(@NotNull ItemStack item, String key) {
        Objects.requireNonNull(ItemForRPG.getInstance(), "Plugin hasn't been initialized.");
        Objects.requireNonNull(item, "item must not be null");
        if (!item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta != null ? meta.getPersistentDataContainer() : null;
        return pdc != null && pdc.has(new NamespacedKey(ItemForRPG.getInstance(), key), PersistentDataType.STRING);
    }

}