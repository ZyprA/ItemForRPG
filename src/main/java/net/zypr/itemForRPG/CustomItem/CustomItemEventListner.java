package net.zypr.itemForRPG.CustomItem;

import net.zypr.itemForRPG.ItemForRPG;
import net.zypr.itemForRPG.util.ECommandAPI;
import net.zypr.itemForRPG.util.NBTAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Listener class for handling custom item interactions in the RPG system.
 * This class listens for player interaction events and executes commands
 * associated with custom items when they are used.
 */

public class CustomItemEventListner implements Listener {

    @EventHandler
    public void onCustomItemClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        ItemStack item = event.getItem();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;
        if (ItemForRPG.getCooldownManager().isOnCooldown(item)) {
            player.sendMessage("§cYou are on cooldown for this item.");
            return;
        }
        if (item == null) return;
        if (!item.hasItemMeta()) return;
        if (NBTAPI.hasNBT(item, "id")) {
            String id = NBTAPI.getNBT(item, "id");
            CustomItem customItem = ItemForRPG.getCustomItemManager().getCustomItem(id);
            if (customItem == null) return;
            for (String command : customItem.getPlayerCommands()) {
                ECommandAPI commandAPI = new ECommandAPI(command);
                commandAPI.set("player", player.getName());
                commandAPI.run(player);
            }
            for (String command : customItem.getConsoleCommands()) {
                ECommandAPI commandAPI = new ECommandAPI(command);
                commandAPI.set("player", player.getName());
                commandAPI.run();
            }
        }

    }
}
