package fr.doron.uhc.plugin.tools;

import org.bukkit.entity.Player;

public class HeartUtils {
    public static void addHealth(Player player, int amount) {
        player.setMaxHealth(player.getMaxHealth() + (double)amount);
    }

    public static void removeHealth(Player player, int amount) {
        player.setMaxHealth(player.getMaxHealth() - (double)amount);
    }

    public static void setHealth(Player player, int amount) {
        player.setMaxHealth((double)amount);
    }
}
