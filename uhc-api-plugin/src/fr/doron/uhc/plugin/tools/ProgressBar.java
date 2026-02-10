package fr.doron.uhc.plugin.tools;

import org.bukkit.entity.Player;

public class ProgressBar {

    /**
     * Affiche un message juste au-dessus de la hotbar (ActionBar) avec la progression
     * @param player le joueur à notifier
     * @param current valeur actuelle
     * @param max valeur max
     * @param bars nombre de blocs de la barre (ex: 20)
     */
    public static void send(Player player, int current, int max, int bars) {
        int percent = (int) ((current * 100.0) / max);
        int filled = (int) ((current * bars) / (double) max);

        StringBuilder bar = new StringBuilder();
        bar.append("§a");
        for (int i = 0; i < bars; i++) {
            if (i < filled) bar.append("█");
            else bar.append("§7█");
        }

        String message = bar + " §e" + percent + "%";

        // En 1.8, on n’a pas sendTitle avec int, donc juste le titre/subtitle
        player.sendTitle("", message);
    }

}