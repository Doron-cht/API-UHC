package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CurrentModuleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UHCAPI.getInstance().getGameManager().getCurrentModule() != null){
            sender.sendMessage("§eModule actuel : §f" +
                    UHCAPI.getInstance().getGameManager().getCurrentModule().getDisplayName());
        } else {
            sender.sendMessage("§cAucun module sélectionné !");
        }

        return true;
    }
}