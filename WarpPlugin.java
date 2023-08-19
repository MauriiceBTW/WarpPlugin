 package me.maurice.warpplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class WarpPlugin extends JavaPlugin {

    private WarpPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        saveConfig();

        getCommand("warp").setExecutor(new WarpCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public WarpPlugin getInstance() {
        return instance;
    }
}
