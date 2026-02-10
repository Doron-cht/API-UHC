package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandPlayers implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        List<Player> players = UHCAPI.getInstance().getPlayersForTheGame();

        if(players.isEmpty()) {
            sender.sendMessage("§7Aucun joueur dans la partie.");
            return true;
        }

        sender.sendMessage("§eJoueurs dans la partie (§6" + players.size() + "§e) :");

        players.forEach(p -> sender.sendMessage(" §8- §f" + p.getName()));

        return true;
    }
}