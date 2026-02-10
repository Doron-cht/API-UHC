package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class CommandHSay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (!UHCAPI.getInstance().getGameManager().isHost((Player) sender)) {
            sender.sendMessage("§cTu n'es pas l'host de la partie.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage : /say <message>");
            return true;
        }

        String message = String.join(" ", args);

        Bukkit.broadcastMessage("§7[§6"+(UHCAPI.getInstance().getGameManager().getCurrentModule() != null ? UHCAPI.getInstance().getGameManager().getCurrentModule().getDisplayName() : "UHC") + "§7] §bHOST " + sender.getName() + " §8» §f" + message );




        return false;
    }
}
