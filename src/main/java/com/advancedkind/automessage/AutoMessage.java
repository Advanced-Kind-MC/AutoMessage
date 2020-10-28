package com.advancedkind.automessage;

import com.advancedkind.automessage.tasks.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class AutoMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        int timer = getConfig().getInt("timer");
        BukkitTask Messages = new Messages(this).runTaskTimer(this , 0L , 20 * timer);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("AutoMessage")){
            Player player = (Player) sender;
            if(player.hasPermission("AutoMessage.automessage")){
                if(args.length >0){
                    if(player.hasPermission("AutoMessage.reload")){
                        if(args[0].equalsIgnoreCase("reload")){
                            reloadConfig();
                        }
                    }

                }
                else {
                    player.sendMessage("AutoMessage: Version 1.0");
                }
            }
        }
        return false;
    }
}
