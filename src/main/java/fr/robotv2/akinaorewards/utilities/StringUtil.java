package fr.robotv2.akinaorewards.utilities;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StringUtil {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(colorize(message));
    }

    public static void sendMessage(CommandSender sender, String... messages) {
        for(String message : messages) {
            sendMessage(sender, message);
        }
    }
}
