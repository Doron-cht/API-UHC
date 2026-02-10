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

public class HostFHGUI implements GUIBuilder {
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

    private ItemStack FinalHealItem() {
        ItemStack finalHeal = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta metaFH = finalHeal.getItemMeta();
        metaFH.setDisplayName("§e"+puce()+" §fTemps avant le Final Heal");
        metaFH.setLore(Collections.singletonList("§7"+guillemet() + (UHCAPI.getInstance().getGameManager().FinalHealMinutes() == 0 ? " §e" + UHCAPI.getInstance().getGameManager().FinalHealSeconds() : " §e" + UHCAPI.getInstance().getGameManager().FinalHealMinutes()+ " §fminutes")  + (UHCAPI.getInstance().getGameManager().FinalHealSeconds() == 0 ? "§f." :" §e" + UHCAPI.getInstance().getGameManager().FinalHealSeconds() + " §fsecondes.")));
        finalHeal.setItemMeta(metaFH);
        return finalHeal;
    }

    @Override
    public String name() {
        return "§e"+puce()+" §fTemps Avant Final Heal";
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
        inv.setItem(13, FinalHealItem());
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
                    UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.getInstance().getGameManager().getFinalHeal() + 15);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);

                break;
            case "§e• §f+30":
                UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.getInstance().getGameManager().getFinalHeal() + 30);
                UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
                break;

            case "§e• §f+60":
                UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.getInstance().getGameManager().getFinalHeal() + 60);
                UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
                break;

            case "§e• §f-15":
                if (UHCAPI.getInstance().getGameManager().getFinalHeal() - 15 <= 20) {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(20);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
                } else {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.gameManager.getFinalHeal() - 15);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
                }
                break;
            case "§e• §f-30":
                if (UHCAPI.getInstance().getGameManager().getFinalHeal() - 30 <= 20) {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(20);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.gameManager.getFinalHeal() - 30);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
                }
                break;

            case "§e• §f-60":
                if (UHCAPI.getInstance().getGameManager().getFinalHeal() - 60 <= 20) {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(20);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);

                } else {
                    UHCAPI.getInstance().getGameManager().setFinalHeal(UHCAPI.gameManager.getFinalHeal() - 60);
                    UHCAPI.getInstance().getGUIManager().open(player, HostFHGUI.class);
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
