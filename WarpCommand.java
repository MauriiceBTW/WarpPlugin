package me.maurice.warpplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    private WarpPlugin plugin;

    public WarpCommand(WarpPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (label.equalsIgnoreCase("warp")) {

            if (!(sender instanceof Player))
                return true;

            Player player = (Player) sender;

            if (args.length == 1) {

                String name = args[0].toLowerCase();

                if (name.equalsIgnoreCase("create")) {
                    player.sendMessage("§cThis name can not be used!");
                    return true;
                }

                Location loc = plugin.getConfig().getLocation("Warps." + name);
                if (loc == null)
                    return true;

                player.teleport(loc);
                player.sendMessage("§aYou got teleported to §2" + name);

            }

            else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("create")) {

                    String name = args[1].toLowerCase();

                    if (plugin.getConfig().contains("Warps." + name)) {
                        player.sendMessage("§cThis warp already exists!");
                        return true;
                    }

                    if (name.equalsIgnoreCase("create")) {
                        player.sendMessage("§cThe name of the warp can not be create!");
                        return true;
                    }

                    Location loc = player.getLocation();

                    plugin.getConfig().set("Warps." + name, loc);
                    plugin.saveConfig();

                    player.sendMessage("§aWarp created at §2X:" + (int)loc.getX() + ", Y:" + (int)loc.getY() + ", Z:" + (int)loc.getZ());
                }
            }


        }

        return false;
    }
}
