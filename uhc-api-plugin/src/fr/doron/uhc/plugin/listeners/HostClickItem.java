package fr.doron.uhc.plugin.listeners;


import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.gui.HostGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HostClickItem implements Listener {

    @EventHandler
    public void Onclick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.isOp()){
            if (event.getItem() == null) return;
            Action action = event.getAction();
            if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK &&
                    action != Action.LEFT_CLICK_AIR && action != Action.LEFT_CLICK_BLOCK) return;
            if(event.getItem().getItemMeta().getDisplayName().equals("§e§l• §6§lConfig") && event.getItem().getType() == Material.COMPASS){
                UHCAPI.getInstance().getGUIManager().open(player, HostGUI.class);
            }
        }

    }

}
