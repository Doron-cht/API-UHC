package fr.doron.uhc.plugin.gui;


import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class HostTimerGUI implements GUIBuilder {

    private ItemStack FinalHealItem() {
        ItemStack finalHeal = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta metaFH = finalHeal.getItemMeta();
        metaFH.setDisplayName("§e"+puce()+" §fTemps avant le Final Heal");
        metaFH.setLore(Collections.singletonList("§7"+guillemet() + (UHCAPI.getInstance().getGameManager().FinalHealMinutes() == 0 ? "": " §e" + UHCAPI.getInstance().getGameManager().FinalHealMinutes()+ " §fminutes")  + (UHCAPI.getInstance().getGameManager().FinalHealSeconds() == 0 ? "§f." :" §e" + UHCAPI.getInstance().getGameManager().FinalHealSeconds() + " §fsecondes.")));
        finalHeal.setItemMeta(metaFH);
        return finalHeal;
    }
    private ItemStack PvPItem() {
        ItemStack pvpItem = new ItemStack(Material.IRON_SWORD);
        ItemMeta metapvp = pvpItem.getItemMeta();
        metapvp.setDisplayName("§e"+puce()+" §fTemps avant PvP");
        metapvp.setLore(Collections.singletonList("§7"+guillemet() + (UHCAPI.getInstance().getGameManager().PvPTimerMinutes() == 0 ? "" : " §e" +UHCAPI.getInstance().getGameManager().PvPTimerMinutes() + " §fminutes")  + (UHCAPI.getInstance().getGameManager().PvPTimerSeconds() == 0 ? "§f." : " §e" + UHCAPI.getInstance().getGameManager().PvPTimerSeconds() + " §fsecondes.")));
        pvpItem.setItemMeta(metapvp);
        return pvpItem;
    }
    private ItemStack RolesItem() {

        // Item pour gérer les timings d'annonce des rôles
        ItemStack roleTimerItem = new ItemStack(Material.PAPER);
        ItemMeta meta = roleTimerItem.getItemMeta();
        meta.setDisplayName("§e"+puce()+" §fGérer les timers des rôles");
        meta.setLore(Collections.singletonList("§7"+guillemet() + (UHCAPI.getInstance().getGameManager().RoleTimerMinutes() == 0 ? "" : " §e" +UHCAPI.getInstance().getGameManager().RoleTimerMinutes() + " §fminutes")  + (UHCAPI.getInstance().getGameManager().RoleTimerSeconds() == 0 ? "§f." : " §e" + UHCAPI.getInstance().getGameManager().RoleTimerSeconds() + " §fsecondes.")));

        roleTimerItem.setItemMeta(meta);
        return roleTimerItem;
    }


    @Override
    public String name() {
        return "§7§lTimer";
    }

    @Override
    public int getSize() {
        return 5*9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        int size = inv.getSize();

        // Haut
        inv.setItem(0, Corner());
        inv.setItem(1, Corner());
        inv.setItem(7, Corner());
        inv.setItem(8, Corner());

        // Bas
        inv.setItem(size - 9, Corner());
        inv.setItem(size - 8, Corner());
        inv.setItem(size - 2, Corner());
        inv.setItem(size - 1, Corner());

        // Milieu gauche / droite
        inv.setItem(9, Corner());
        inv.setItem(17, Corner());
        inv.setItem(size - 18, Corner());
        inv.setItem(size - 10, Corner());

        inv.setItem(11, FinalHealItem());
        inv.setItem(12, PvPItem());
        inv.setItem(13, RolesItem());




        inv.setItem(40, BackArrow());

    }


    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        switch (current.getType()) {
            case ARROW:
                player.sendMessage("§7Retour au menu principal...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostGUI.class);
                break;
            case GOLDEN_APPLE:
                player.sendMessage("§7Menu Final Heal...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
                break;
            case IRON_SWORD:
                player.sendMessage("§7Menu PvP...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                break;
            case PAPER:
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostRoleGUI.class);


        }
    }
}
