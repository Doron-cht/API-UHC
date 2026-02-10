package fr.doron.uhc.plugin.gui;


import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;


public class HostSlotsGUI implements GUIBuilder {





    private ItemStack WoolPlus1(){
        ItemStack woolPlus1 = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
        ItemMeta metaWoolPlus = woolPlus1.getItemMeta();
        metaWoolPlus.setDisplayName("§e"+puce()+" §f+1");
        woolPlus1.setItemMeta(metaWoolPlus);
        return woolPlus1;
    }
    private ItemStack WoolPlus5(){
        ItemStack woolPlus5 = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
        ItemMeta metaWoolPlus = woolPlus5.getItemMeta();
        metaWoolPlus.setDisplayName("§e"+puce()+" §f+5");
        woolPlus5.setItemMeta(metaWoolPlus);
        return woolPlus5;
    }
    private ItemStack WoolPlus10(){
        ItemStack woolPlus10 = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
        ItemMeta metaWoolPlus = woolPlus10.getItemMeta();
        metaWoolPlus.setDisplayName("§e"+puce()+" §f+10");
        woolPlus10.setItemMeta(metaWoolPlus);
        return woolPlus10;
    }
    private ItemStack WoolMoins1(){
        ItemStack woolMoins1 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta metaWoolMoins = woolMoins1.getItemMeta();
        metaWoolMoins.setDisplayName("§e"+puce()+" §f-1");
        woolMoins1.setItemMeta(metaWoolMoins);
        return woolMoins1;
    }
    private ItemStack WoolMoins5(){
        ItemStack woolMoins5 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta metaWoolMoins = woolMoins5.getItemMeta();
        metaWoolMoins.setDisplayName("§e"+puce()+" §f-5");
        woolMoins5.setItemMeta(metaWoolMoins);
        return woolMoins5;
    }
    private ItemStack WoolMoins10(){
        ItemStack woolMoins10 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta metaWoolMoins = woolMoins10.getItemMeta();
        metaWoolMoins.setDisplayName("§e"+puce()+" §f-10");
        woolMoins10.setItemMeta(metaWoolMoins);
        return woolMoins10;
    }

    private ItemStack Slots(){
        ItemStack slots = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
        ItemMeta metaslots = slots.getItemMeta();
        metaslots.setDisplayName("§e"+puce()+" §fSlots");
        metaslots.setLore(Collections.singletonList("§8" + guillemet() + " §e" + UHCAPI.getInstance().getGameManager().getMaxSlot() + " §7Joueurs"));
        slots.setItemMeta(metaslots);
        return slots;
    }



    @Override
    public String name() {
        return "§7§lGestion des Slots";
    }

    @Override
    public int getSize() {
        return 5 * 9;
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


        inv.setItem(21, WoolPlus1());
        inv.setItem(20, WoolPlus5());
        inv.setItem(19, WoolPlus10());


        inv.setItem(22, Slots());

        inv.setItem(23, WoolMoins1());
        inv.setItem(24, WoolMoins5());
        inv.setItem(25, WoolMoins10());

        inv.setItem(40, BackArrow());

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        switch(current.getItemMeta().getDisplayName()){
            case "§e• §f+1":
                if (UHCAPI.getInstance().getGameManager().getMaxSlot() + 1 > 100) {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(100);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(UHCAPI.gameManager.getMaxSlot() + 1);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);

                }
                break;
            case "§e• §f+5":
                if (UHCAPI.getInstance().getGameManager().getMaxSlot() + 5 > 100) {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(100);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                } else {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(UHCAPI.gameManager.getMaxSlot() + 5);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                }
                break;

            case "§e• §f+10":
                 if (UHCAPI.getInstance().getGameManager().getMaxSlot() + 10 > 100) {
                     UHCAPI.getInstance().getGameManager().setMaxSlot(100);
                     UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);

                 } else {
                     UHCAPI.getInstance().getGameManager().setMaxSlot(UHCAPI.gameManager.getMaxSlot() + 10);
                     UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                 }
                 break;

            case "§e• §f-1":
                if (UHCAPI.getInstance().getGameManager().getMaxSlot() - 1 <= 0) {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(1);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                } else {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(UHCAPI.gameManager.getMaxSlot() - 1);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                }
                break;
            case "§e• §f-5":
                if (UHCAPI.getInstance().getGameManager().getMaxSlot() - 5 <= 1) {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(1);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(UHCAPI.gameManager.getMaxSlot() - 5);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                }
                break;

            case "§e• §f-10":
                if (UHCAPI.getInstance().getGameManager().getMaxSlot() - 10 <= 1) {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(1);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setMaxSlot(UHCAPI.gameManager.getMaxSlot() - 10);
                    UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);

                }
                break;

            case "§e• §fRetour":
                player.sendMessage("§e• §7Retour");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostServeurGUI.class);
                break;

            default: break;
        }
    }
}
