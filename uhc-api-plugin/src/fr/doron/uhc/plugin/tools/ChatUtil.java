package fr.doron.uhc.plugin.tools;

import fr.doron.uhc.plugin.UHCAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class ChatUtil {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String prefix(String message) {
        return translate(translate(UHCAPI.getInstance().getConfig().get("prefix") + " " + message));
    }

    // Crée un texte simple
    public static TextComponent text(String message) {
        return new TextComponent(message);
    }

     public static TextComponent hover(String message, String hoverText) {
        TextComponent component = new TextComponent(message);
        component.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder(hoverText).create()
            ));
            return component;
        }

        // Envoie plusieurs composants assemblés
        public static void send(Player player, BaseComponent... components) {
        player.spigot().sendMessage(components);
        }
    }
