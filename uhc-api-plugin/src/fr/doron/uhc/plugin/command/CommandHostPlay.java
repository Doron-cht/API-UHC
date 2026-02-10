package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHostPlay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (label.equals("hostplay")) {
            if (args.length == 0) {
                p.sendMessage((UHCAPI.getInstance().getGameManager().IsHostPlay() ? "§aTrue" : "§cFalse"));
            }
        }
        return false;
    }
}
