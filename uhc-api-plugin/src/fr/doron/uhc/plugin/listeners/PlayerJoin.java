package fr.doron.uhc.plugin.listeners;

import fr.doron.uhc.plugin.UHCAPI;

import fr.doron.uhc.plugin.tools.HostItem;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(UHCAPI.getInstance().getGameManager().getOnlinePlayer() + 1 >= UHCAPI.getInstance().getGameManager().getMaxSlot()){
            player.kickPlayer("§cDésolé le serveur est plein");
        }else{
            UHCAPI.getInstance().addPlayer(player);
        }


        e.setJoinMessage("§7[§a+§7] " + player.getName() +" "+ UHCAPI.getInstance().getPlayersForTheGame().size() + "§7/" + UHCAPI.getInstance().getGameManager().getMaxSlot());
        player.getInventory().clear();
        player.getEquipment().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setExp(0);
        player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 201, 0.5));
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1f, 1f);
        if(!player.isOp()) {
            player.setCanPickupItems(false);
            player.setGameMode(GameMode.ADVENTURE);
        }else{
            player.setGameMode(GameMode.CREATIVE);
            player.setItemInHand(new HostItem().HostItem());
        }






    }


}
