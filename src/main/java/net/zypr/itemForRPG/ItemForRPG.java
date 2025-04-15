package net.zypr.itemForRPG;

import lombok.Getter;
import net.zypr.itemForRPG.CustomItem.CustomItemEventListner;
import net.zypr.itemForRPG.CustomItem.CustomItemManager;
import net.zypr.itemForRPG.Others.CommandRegister;
import org.bukkit.plugin.java.JavaPlugin;


public final class ItemForRPG extends JavaPlugin {

    @Getter
    private static ItemForRPG instance;

    @Getter
    private static CustomItemManager customItemManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        customItemManager = new CustomItemManager();
        instance = this;

        customItemManager.loadFromConfig();
        CommandRegister.load();

        getServer().getPluginManager().registerEvents(new CustomItemEventListner(), this);

        getLogger().info("ItemForRPG has been enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
