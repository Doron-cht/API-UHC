package fr.doron.uhc.plugin.listeners;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class PlayerChat implements Listener {

    private List<Player> cooldown = new ArrayList<>();



    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        e.setFormat("%1$s §7> %2$s");
        if (!player.isOp()){


            if(cooldown.contains(player)){
                e.setCancelled(true);
                player.sendMessage("§cMerci de patienter entre chaque messages");
                return;
            }

            cooldown.add(player);
            Bukkit.getScheduler().runTaskLater(UHCAPI.getInstance(), () ->
                cooldown.remove(player), 2 * 20L);

        }
    }


}
