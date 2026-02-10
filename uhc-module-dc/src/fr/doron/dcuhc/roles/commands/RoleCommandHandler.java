package fr.doron.dcuhc.roles.commands;

import org.bukkit.entity.Player;

public interface RoleCommandHandler {
    boolean handleCommand(Player player, String[] args);
}