package fr.doron.uhc.plugin.tools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HostItem {

    public ItemStack HostItem(){
        ItemStack config = new ItemStack(Material.COMPASS);
        ItemMeta metaconfig = config.getItemMeta();
        metaconfig.setDisplayName("§e§l• §6§lConfig");
        config.setItemMeta(metaconfig);
        return config;
    }


}
