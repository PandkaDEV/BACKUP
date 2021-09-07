package pl.pieszku.backup.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils {

    public static String colored(String color){
        return ChatColor.translateAlternateColorCodes('&', color);
    }
    public static boolean sendMessage(CommandSender commandSender, String message){
        commandSender.sendMessage(colored(message));
        return false;
    }
}
