package fr.doron.uhc.plugin.command;

import fr.doron.uhc.plugin.gui.HostGUI;
import fr.doron.uhc.plugin.tools.HostItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHost implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;


            if(label.equals("host")){
                if(sender.isOp()){
                    if(args.length == 0){
                        p.getInventory().setItem(4, new HostItem().HostItem());
                   }
                }
            }



        return false;
    }
}
