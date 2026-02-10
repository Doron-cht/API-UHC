package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetHost implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length != 1) {
            sender.sendMessage("§c/sethost <pseudo>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {
            sender.sendMessage("§cJoueur introuvable.");
            return true;
        }

        UHCAPI.getInstance().getGameManager().setHost(target);

        sender.sendMessage("§aHost défini : §f" + target.getName());
        target.sendMessage("§eTu es Maintenant le host de la partie.");

        return true;
    }
}