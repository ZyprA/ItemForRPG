package net.zypr.itemForRPG.CustomItem;

    import net.zypr.itemForRPG.ItemForRPG;
    import net.zypr.itemForRPG.util.NBTAPI;
    import org.bukkit.inventory.ItemStack;

    import java.util.HashMap;
    import java.util.Map;

    /**
     * The Cooldown class manages cooldowns for custom items in the ItemForRPG plugin.
     * It tracks cooldowns for specific item IDs and ensures that items cannot be used
     * again until their cooldown period has expired.
     */
    public class Cooldown {

        private final Map<String, Long> cooldowns = new HashMap<>();

        /**
         * Checks if a specific item ID is on cooldown.
         *
         * @param id The unique identifier of the item.
         * @param cooldownSeconds The cooldown duration in seconds.
         * @return {@code true} if the item is on cooldown, {@code false} otherwise.
         */
        public boolean isOnCooldown(String id, int cooldownSeconds) {
            long currentTime = System.currentTimeMillis();
            long cooldownTime = cooldowns.getOrDefault(id, 0L);
            if (currentTime - cooldownTime >= cooldownSeconds * 1000L) {
                cooldowns.put(id, currentTime);
                return false; // Not on cooldown
            }
            return true; // On cooldown
        }

        /**
         * Checks if a specific ItemStack is on cooldown based on its NBT data.
         *
         * @param item The ItemStack to check.
         * @return {@code true} if the item is on cooldown, {@code false} otherwise.
         */
        public boolean isOnCooldown(ItemStack item) {
            if (NBTAPI.hasNBT(item, "itemId") && NBTAPI.hasNBT(item, "id")) {
                String id = NBTAPI.getNBT(item, "id");
                String itemId = NBTAPI.getNBT(item, "itemId");
                CustomItem customItem = ItemForRPG.getCustomItemManager().getCustomItem(id);
                if (customItem != null) {
                    int cooldown = customItem.getCooldown();
                    if (cooldown > 0) {
                        return isOnCooldown(itemId, cooldown);
                    }
                }
            }
            return false;
        }
    }