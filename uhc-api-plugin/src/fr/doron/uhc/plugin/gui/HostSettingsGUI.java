package fr.doron.uhc.plugin.gui;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HostSettingsGUI implements GUIBuilder {



    @Override
    public String name() {
        return "§7§lParamètres de l'UHC";
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
            default: break;
        }
    }
}
