package com.advancedkind.automessage;

import com.advancedkind.automessage.tasks.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import java.util.ArrayList;
import java.util.List;

public final class AutoMessage extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        int timer = 20*getConfig().getInt("timer");
        BukkitTask Messages = new Messages(this).runTaskTimer(this , 0L , timer);
    }

    public boolean toggle = true;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("AutoMessage")){
            Player player = (Player) sender;
            if(player.hasPermission("AutoMessage.automessage")){
                if(args.length >0){
                    if(player.hasPermission("AutoMessage.reload")){
                        if(args[0].equalsIgnoreCase("reload")){
                            reloadConfig();
                            Bukkit.getScheduler().cancelTasks(this);
                            int timer = 20*getConfig().getInt("timer");
                            BukkitTask Messages = new Messages(this).runTaskTimer(this , 0L , timer);
                            player.sendMessage("AutoMessage reloaded");
                        }
                    }
                    if(player.hasPermission("AutoMessage.toggle")){
                        if(args[0].equalsIgnoreCase("toggle")){
                            if(toggle){
                                toggle = false;
                                player.sendMessage("AutoMessage disabled");
                            }
                            else if (!toggle){
                                toggle = true;
                                player.sendMessage("AutoMessage enabled");
                            }
                        }
                    }
                }
                else {
                    player.sendMessage("AutoMessage: Version 1.0.4");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("AutoMessage")){
            if(args.length == 1){
                List<String> tab = new ArrayList<>();
                tab.add("reload");
                tab.add("toggle");
                return tab;
            }
        }

        return null;
    }
}
