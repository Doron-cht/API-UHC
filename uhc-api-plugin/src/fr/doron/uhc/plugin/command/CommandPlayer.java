package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!command.getName().equalsIgnoreCase("player")) return false;
        if(strings.length < 2){
            commandSender.sendMessage("§cUsage: /player add <pseudo>");
            return true;
        }
        String subCommand = strings[0];
        if (subCommand.equalsIgnoreCase("add")) {
            String targetName = strings[1];
            Player target = Bukkit.getPlayerExact(targetName);

            if (target == null) {
                commandSender.sendMessage("§cJoueur introuvable.");
                return true;
            }

            GameManager gm = UHCAPI.getInstance().getGameManager();

            if (UHCAPI.getInstance().getPlayersForTheGame().contains(target)) {
                commandSender.sendMessage("§cLe joueur est déjà dans la partie.");
                return true;
            }

            UHCAPI.getInstance().getPlayersForTheGame().add(target);
            commandSender.sendMessage("§a" + target.getName() + " a été ajouté à la partie !");
            target.sendMessage("§eVous avez été ajouté à la partie par " + commandSender.getName());
            return true;
        }

        commandSender.sendMessage("§cSous-commande inconnue !");
        return true;
    }

}
