package fr.doron.uhc.plugin.gui;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ModuleSelectionGUI implements GUIBuilder {
    @Override
    public String name() {
        return "§7§lSélection du Module";
    }

    @Override
    public int getSize() {
        int count = UHCAPI.getInstance().getGameModeManager().getAll().size();
        int rows = ((count - 1) / 9 + 1); // arrondi supérieur
        if (rows > 6) rows = 6;
        return rows * 9;

    }

    @Override
    public void contents(Player player, Inventory inv) {
        final int[] slot = {0};

        UHCAPI.getInstance().getGameModeManager().getAll().forEach(module -> {
            ItemStack item = new ItemStack(Material.BOOK);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e" + module.getDisplayName());
            List<String> lore = new ArrayList<>();
            lore.add("§7Clique pour sélectionner ce module");
            meta.setLore(lore);
            item.setItemMeta(meta);

            inv.setItem(slot[0], item);
            slot[0]++;
        });
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        if (current == null || current.getType() != Material.BOOK || !current.hasItemMeta()) return;

        String moduleName = current.getItemMeta().getDisplayName().replace("§e", "");

        // Récupérer le module correspondant
        UHCAPI.getInstance().getGameModeManager().getAll().forEach(module -> {
            if (module.getDisplayName().equals(moduleName)) {
                UHCAPI.getInstance().getGameManager().setCurrentModule(module);
                player.sendMessage("§aModule sélectionné : §f" + module.getDisplayName());
                player.closeInventory();
            }
        });
    }
}