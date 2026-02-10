package fr.doron.uhc.plugin.tools;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public interface GUIBuilder {
    public abstract String name();
    public abstract int getSize();
    public abstract void contents(Player player, Inventory inv);
    public abstract void onClick(Player player, Inventory inv, ItemStack current, int slot);
    public default String puce(){
        return "•";
    }
    default String petitePuce(){
        return "·";
    }


    public default String guillemet(){
        return "»";
    }
    default ItemStack Corner(){
        ItemStack corner = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta metaCorner = corner.getItemMeta();
        metaCorner.setDisplayName(" ");
        corner.setItemMeta(metaCorner);
        return corner;
    }
    default ItemStack BackArrow(){
        ItemStack backArrow = new ItemStack(Material.ARROW);
        ItemMeta metaArrow = backArrow.getItemMeta();
        metaArrow.setDisplayName("§e"+puce()+" §fRetour");
        backArrow.setItemMeta(metaArrow);
        return backArrow;
    }
}
