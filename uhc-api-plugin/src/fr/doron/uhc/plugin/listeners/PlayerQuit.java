package fr.doron.uhc.plugin.listeners;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {



    @EventHandler
    public void OnQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        UHCAPI.getInstance().removePlayer(player);
        e.setQuitMessage("§7[§c-§7] "  + player.getName() +" "+ UHCAPI.getInstance().getPlayersForTheGame().size() + "§7/" + UHCAPI.getInstance().getGameManager().getMaxSlot());


    }
}
