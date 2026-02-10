package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandMumble implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        if(UHCAPI.getInstance().getGameManager().isMumble()){
            sender.sendMessage("§bLE MB: TKTMEMEPASBEBOU");
        }else{
            sender.sendMessage("§cLe mumble n'est pas activé");
        }


        return false;
    }
}
