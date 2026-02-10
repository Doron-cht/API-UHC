package fr.doron.dcuhc.roles.Flash;

import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class FlashGUI implements GUIBuilder {

    @Override
    public String name() {
        return "§eFlash Powers";
    }

    @Override
    public int getSize() {
        return 3*9;
    }

    @Override
    public void contents(Player p, Inventory inv) {

        inv.setItem(11, powerItem(
                Material.FEATHER,
                "§bAerokinesis",
                "§7Repousse les joueurs dans un rayon de 15 blocs",
                "§e3 utilisations - 15 min cooldown"
        ));

        inv.setItem(13, powerItem(
                Material.ENDER_PEARL,
                "§5Dimensional Travel",
                "§7Téléportation aléatoire rayon 150 blocs",
                "§c-1 coeur permanent"
        ));

        inv.setItem(15, powerItem(
                Material.WATCH,
                "§aTime Travel",
                "§7Retour à ta position il y a 10 secondes"
        ));
    }

    private ItemStack powerItem(Material mat, String name, String... lore) {
        ItemStack it = new ItemStack(mat);
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        it.setItemMeta(meta);
        return it;
    }


    @Override
    public void onClick(Player p, Inventory inv, ItemStack clicked, int slot) {
        if (clicked == null || clicked.getType() == Material.AIR) return;

        Role r = UHCAPI.getInstance().getGameManager().getRole(p);
        if (!(r instanceof FlashRole)) return;

        FlashRole role = (FlashRole) r;

        switch (clicked.getType()) {
            case FEATHER:
                role.usePower(p, "aerokinesis");
                break;

            case ENDER_PEARL:
                role.usePower(p, "dimensional");
                break;

            case WATCH:
                role.usePower(p, "time");
                break;

            default:
                p.sendMessage("§cPouvoir inconnu !");
                break;
        }

        p.closeInventory(); // ferme le GUI après usage

    }
}