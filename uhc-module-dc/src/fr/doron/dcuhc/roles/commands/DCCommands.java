package fr.doron.dcuhc.roles.commands;

import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DCCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Role role = UHCAPI.getInstance().getGameManager().getRole((Player) commandSender);

        if (role instanceof RoleCommandHandler) {
            if (((RoleCommandHandler) role).handleCommand((Player) commandSender, strings)) {
                return true;
            }
        }

        commandSender.sendMessage("§cPouvoir inconnu.");

        return false;
    }
}
