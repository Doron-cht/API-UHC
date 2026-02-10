package fr.doron.uhc.plugin.tools;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIManager implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(event.getCurrentItem() == null) return;

        UHCAPI.getInstance().getRegisteredMenus().values().stream()
                .filter(menu -> inv.getName().equalsIgnoreCase(menu.name()))
                .forEach(menu -> {
                    menu.onClick(player, inv, current, event.getSlot());
                    event.setCancelled(true);
                });

    }

    public void addMenu(GUIBuilder m){
        UHCAPI.getInstance().getRegisteredMenus().put(m.getClass(), m);
    }

    public void open(Player player, Class<? extends GUIBuilder> gClass){

        if(!UHCAPI.getInstance().getRegisteredMenus().containsKey(gClass)) return;

        GUIBuilder menu = UHCAPI.getInstance().getRegisteredMenus().get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSize(), menu.name());
        menu.contents(player, inv);

        new BukkitRunnable() {

            @Override
            public void run() {
                player.openInventory(inv);
            }

        }.runTaskLater(UHCAPI.getInstance(), 1);

    }

}
