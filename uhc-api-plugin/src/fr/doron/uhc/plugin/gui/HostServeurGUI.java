package fr.doron.uhc.plugin.gui;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HostServeurGUI implements GUIBuilder {


    private ItemStack PlayerHost(){
        ItemStack swordhostplay = new ItemStack(Material.IRON_SWORD);
        ItemMeta metaswordhostplay = swordhostplay.getItemMeta();
        swordhostplay.setAmount((UHCAPI.getInstance().getGameManager().IsHostPlay() ? 1 : 0));
        metaswordhostplay.setDisplayName("§e"+puce()+" §fJouer en tant qu'host");
        metaswordhostplay.setLore(Arrays.asList("§8"+ guillemet() +" §7Modifier cette élément"," ","§8"+guillemet()+(UHCAPI.getInstance().getGameManager().IsHostPlay() ? " §a§lActivé" : " §c§lDésactivé")));
        swordhostplay.setItemMeta(metaswordhostplay);
        return swordhostplay;
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
        return "§7§lServeur Host";
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


        inv.setItem(21, PlayerHost());
        inv.setItem(23, Slots());
        inv.setItem(40, BackArrow());

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        switch(current.getType()){
            case ARROW:
                player.sendMessage("§7Retour au menu principal...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostGUI.class);
                break;

            case IRON_SWORD:
                UHCAPI.getInstance().getGameManager().setHostPlay(!UHCAPI.getInstance().getGameManager().IsHostPlay());
                UHCAPI.getInstance().getGUIManager().open(player, HostServeurGUI.class);
                if (UHCAPI.getInstance().getGameManager().IsHostPlay()) {
                    UHCAPI.getInstance().addPlayer(player);
                } else {
                    UHCAPI.getInstance().removePlayer(player);
                }
                player.sendMessage((UHCAPI.getInstance().getGameManager().IsHostPlay() ? "§aVous faites désormais partie de la liste des joueurs " : "§cVous ne faites désormais plus partie de la liste des joueurs"));
                break;

            case SKULL_ITEM:
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostSlotsGUI.class);
                break;



            default: break;
        }
    }
}
