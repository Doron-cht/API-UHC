package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UHCStopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(UHCAPI.getInstance().getGameManager() == null){
            sender.sendMessage("§cLe GameManager n'est pas initialisé !");
            return true;
        }

        UHCAPI.getInstance().getGameManager().endGame();
        sender.sendMessage("§cLa partie UHC a été stoppée !");
        return true;
    }
}