package com.advancedkind.automessage.tasks;
import com.advancedkind.automessage.AutoMessage;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

public class Messages extends BukkitRunnable {
    AutoMessage plugin;

    public Messages(AutoMessage plugin) {
        this.plugin = plugin;
    }

    private static String translateHexColorCodes(final String startTag, final String endTag, final String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        final Matcher matcher = hexPattern.matcher(message);
        final StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            final String group = matcher.group(1);
            matcher.appendReplacement(buffer,
                    COLOR_CHAR + "x" + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1) + COLOR_CHAR
                            + group.charAt(2) + COLOR_CHAR + group.charAt(3) + COLOR_CHAR + group.charAt(4) + COLOR_CHAR
                            + group.charAt(5));
        }
        return matcher.appendTail(buffer).toString();
    }
    private int messageNum = 0;
    @Override
    public void run() {
    if(plugin.toggle){
        messageNum++;
        if(messageNum == plugin.getConfig().getStringList("messages").size()){
            messageNum = 0;
        }
        String message = plugin.getConfig().getStringList("messages").get(messageNum);
        String color = ChatColor.translateAlternateColorCodes('&', message);
        String placeholder = PlaceholderAPI.setPlaceholders(null, color);
        plugin.getServer().broadcastMessage(translateHexColorCodes("&#", "", placeholder));
    }
    }
}
