package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.game.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimersCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        GameManager gm = UHCAPI.getInstance().getGameManager();

        int elapsed = gm.getElapsedSeconds(); // On aura besoin d'ajouter cette méthode
        int pvp = gm.getPvPTimer();
        int finalHeal = gm.getFinalHeal();
        int border = gm.getBorderTime();
        int game = gm.getGameTime();
        int role = gm.getRoleTimer();

        sender.sendMessage("§e--- Timers UHC ---");
        sender.sendMessage("§fPvP : §c" + formatTime(pvp - elapsed));
        sender.sendMessage("§fFinalHeal : §a" + formatTime(finalHeal - elapsed));
        sender.sendMessage("§fBordure : §e" + formatTime(border - elapsed));
        sender.sendMessage("§fPartie : §c" + formatTime(elapsed));
        sender.sendMessage("§fRoles : §a " + formatTime(role - elapsed));

        return true;
    }

    private String formatTime(int seconds) {
        if(seconds < 0) seconds = 0;
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }
}