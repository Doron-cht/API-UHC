package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.world.UHCWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUHCtp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSeul un joueur peut utiliser cette commande !");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            // Affiche le monde actuel
            player.sendMessage("§eVous êtes dans le monde : §6" + player.getWorld().getName());
            return true;
        }

        if (args.length == 1) {
            String worldName = args[0];
            World world = Bukkit.getWorld(worldName);

            if (world == null) {
                player.sendMessage("§cLe monde \"" + worldName + "\" n'existe pas !");
                return true;
            }

            player.teleport(world.getSpawnLocation());
            player.sendMessage("§aTéléporté dans le monde : §6" + world.getName());
            return true;
        }

        player.sendMessage("§cUsage : /uhctp [nomDuMonde]");
        return true;
    }
}