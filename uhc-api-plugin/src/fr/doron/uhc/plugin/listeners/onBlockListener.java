package fr.doron.uhc.plugin.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class onBlockListener implements Listener {


    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.BARRIER) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType() == Material.BARRIER) {
            e.setCancelled(true);
        }
    }
}
