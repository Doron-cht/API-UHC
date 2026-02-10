package fr.doron.uhc.plugin.gui;


import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class HostPvPGUI implements GUIBuilder {
    private ItemStack WoolPlus15(){
        ItemStack woolPlus1 = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
        ItemMeta metaWoolPlus = woolPlus1.getItemMeta();
        metaWoolPlus.setDisplayName("§e"+puce()+" §f+15");
        woolPlus1.setItemMeta(metaWoolPlus);
        return woolPlus1;
    }
    private ItemStack WoolPlus30(){
        ItemStack woolPlus5 = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
        ItemMeta metaWoolPlus = woolPlus5.getItemMeta();
        metaWoolPlus.setDisplayName("§e"+puce()+" §f+30");
        woolPlus5.setItemMeta(metaWoolPlus);
        return woolPlus5;
    }
    private ItemStack WoolPlus60(){
        ItemStack woolPlus10 = new ItemStack(Material.WOOL, 1, DyeColor.GREEN.getData());
        ItemMeta metaWoolPlus = woolPlus10.getItemMeta();
        metaWoolPlus.setDisplayName("§e"+puce()+" §f+60");
        woolPlus10.setItemMeta(metaWoolPlus);
        return woolPlus10;
    }
    private ItemStack WoolMoins15(){
        ItemStack woolMoins1 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta metaWoolMoins = woolMoins1.getItemMeta();
        metaWoolMoins.setDisplayName("§e"+puce()+" §f-15");
        woolMoins1.setItemMeta(metaWoolMoins);
        return woolMoins1;
    }
    private ItemStack WoolMoins30(){
        ItemStack woolMoins5 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta metaWoolMoins = woolMoins5.getItemMeta();
        metaWoolMoins.setDisplayName("§e"+puce()+" §f-30");
        woolMoins5.setItemMeta(metaWoolMoins);
        return woolMoins5;
    }
    private ItemStack WoolMoins60(){
        ItemStack woolMoins10 = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta metaWoolMoins = woolMoins10.getItemMeta();
        metaWoolMoins.setDisplayName("§e"+puce()+" §f-60");
        woolMoins10.setItemMeta(metaWoolMoins);
        return woolMoins10;
    }

    private ItemStack PvPItem() {
        ItemStack pvpItem = new ItemStack(Material.IRON_SWORD);
        ItemMeta metapvp = pvpItem.getItemMeta();
        metapvp.setDisplayName("§e"+puce()+" §fTemps avant PvP");
        metapvp.setLore(Collections.singletonList("§7"+guillemet() + (UHCAPI.getInstance().getGameManager().PvPTimerMinutes() == 0 ? " §e" + UHCAPI.getInstance().getGameManager().PvPTimerSeconds() : " §e" +UHCAPI.getInstance().getGameManager().PvPTimerMinutes()+ " §fminutes")  + (UHCAPI.getInstance().getGameManager().PvPTimerSeconds() == 0 ? "§f." : " §e" + UHCAPI.getInstance().getGameManager().PvPTimerSeconds() + " §fsecondes.")));
        pvpItem.setItemMeta(metapvp);
        return pvpItem;
    }

    @Override
    public String name() {
        return "§e"+puce()+" §fTemps Avant PvP";
    }

    @Override
    public int getSize() {
        return 3*9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.setItem(0, BackArrow());
        inv.setItem(1, Corner());
        inv.setItem(2, Corner());
        inv.setItem(3, Corner());
        inv.setItem(4, Corner());
        inv.setItem(5, Corner());
        inv.setItem(6, Corner());
        inv.setItem(7, Corner());
        inv.setItem(8, Corner());
        inv.setItem(9, Corner());

        inv.setItem(10, WoolMoins60());
        inv.setItem(11, WoolMoins30());
        inv.setItem(12, WoolMoins15());
        inv.setItem(13, PvPItem());
        inv.setItem(14, WoolPlus15());
        inv.setItem(15, WoolPlus30());
        inv.setItem(16, WoolPlus60());


        inv.setItem(17, Corner());
        inv.setItem(18, Corner());
        inv.setItem(19, Corner());
        inv.setItem(20, Corner());
        inv.setItem(21, Corner());
        inv.setItem(22, Corner());
        inv.setItem(23, Corner());
        inv.setItem(24, Corner());
        inv.setItem(25, Corner());
        inv.setItem(26, Corner());
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        switch(current.getItemMeta().getDisplayName()){
            case "§e• §f+15":
                UHCAPI.getInstance().getGameManager().setPvPTimer(UHCAPI.getInstance().getGameManager().getPvPTimer() + 15);
                UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);

                break;
            case "§e• §f+30":
                UHCAPI.getInstance().getGameManager().setPvPTimer(UHCAPI.getInstance().getGameManager().getPvPTimer() + 30);
                UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                break;

            case "§e• §f+60":
                UHCAPI.getInstance().getGameManager().setPvPTimer(UHCAPI.getInstance().getGameManager().getPvPTimer() + 60);
                UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                break;

            case "§e• §f-15":
                if (UHCAPI.getInstance().getGameManager().getPvPTimer() - 15 <= 20) {
                    UHCAPI.getInstance().getGameManager().setPvPTimer(20);
                    UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                } else {
                    UHCAPI.getInstance().getGameManager().setPvPTimer(UHCAPI.gameManager.getPvPTimer() - 15);
                    UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                }
                break;
            case "§e• §f-30":
                if (UHCAPI.getInstance().getGameManager().getPvPTimer() - 30 <= 20) {
                    UHCAPI.getInstance().getGameManager().setPvPTimer(20);
                    UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.gameManager.getPvPTimer() - 30);
                    UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                }
                break;

            case "§e• §f-60":
                if (UHCAPI.getInstance().getGameManager().getPvPTimer() - 60 <= 20) {
                    UHCAPI.getInstance().getGameManager().setPvPTimer(20);
                    UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setPvPTimer(UHCAPI.gameManager.getPvPTimer() - 60);
                    UHCAPI.getInstance().getGUIManager().open(player, HostPvPGUI.class);
                }
                break;

            case "§e• §fRetour":
                player.sendMessage("§e• §7Retour");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostTimerGUI.class);
                break;

            default: break;
        }
    }
}
