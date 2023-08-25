package io.github.elihuso.backrefactor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Back_Refactor extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Please use back command as a player.");
            return false;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("back")) {
            String filePath = this.getDataFolder() + "/data/" + player.getUniqueId() + ".yml";
            FileConfiguration config = new YamlConfiguration();
            try {
                config.load(filePath);
                Location location = config.getLocation("location");
                player.teleport(location);
                player.sendMessage(ChatColor.GREEN + "Teleport to your previous location.");
            } catch (Exception exception) {
                player.sendMessage(ChatColor.RED + "Cannot back to your previous location");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
