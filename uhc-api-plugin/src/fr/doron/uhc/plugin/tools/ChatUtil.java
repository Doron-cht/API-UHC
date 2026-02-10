package fr.doron.uhc.plugin.tools;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.ChatColor;

public class ChatUtil {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String prefix(String message) {
        return translate(translate(UHCAPI.getInstance().getConfig().get("prefix") + " " + message));
    }
}
