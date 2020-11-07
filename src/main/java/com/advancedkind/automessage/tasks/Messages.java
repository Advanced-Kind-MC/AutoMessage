package com.advancedkind.automessage.tasks;
import com.advancedkind.automessage.AutoMessage;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Messages extends BukkitRunnable {

    Plugin plugin1 = AutoMessage.getPlugin(AutoMessage.class);

    AutoMessage plugin;

    public Messages(AutoMessage plugin) {
        this.plugin = plugin;
    }

    private int messageNum = 0;
    @Override
    public void run() {
        messageNum++;
        if(messageNum == plugin1.getConfig().getStringList("messages").size()){
            messageNum = 0;
        }
        String message = plugin1.getConfig().getStringList("messages").get(messageNum);
        String color = ChatColor.translateAlternateColorCodes('&', message);
        String placeholder = PlaceholderAPI.setPlaceholders(null, color);
        plugin.getServer().broadcastMessage(placeholder);
    }
}
