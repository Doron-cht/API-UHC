package fr.doron.uhc.plugin.gui;

import fr.doron.uhc.plugin.tools.GUIBuilder;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.api.Role;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RolesGUI implements GUIBuilder, Listener {

    @Override
    public String name() {
        return "§7§lGestion des rôles";
    }

    @Override
    public int getSize() {
        return 3 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.clear();

        List<Role> roles = new ArrayList<>();
        if (UHCAPI.getInstance().getGameManager().getCurrentModule() != null) {
            roles = UHCAPI.getInstance().getGameManager().getCurrentModule().getRoles();
        }

        List<Role> activeRoles = UHCAPI.getInstance().getGameManager().getActiveRoles();

        // Ajouter un item par rôle
        for (int i = 0; i < roles.size(); i++) {
            Role r = roles.get(i);
            // Compter combien de fois ce rôle est actif
            int count = 0;
            for (Role r2 : activeRoles) {
                if (r2.getName().equals(r.getName())) count++;
            }

            ItemStack item = new ItemStack(Material.PAPER);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName("§a" + r.getName());
            meta.setLore(Arrays.asList(
                    "§7" + r.getDescription(),
                    "§eInstances actives : §f" + count,
                    "§eClic gauche → ajouter",
                    "§eClic droit → retirer"
            ));

            meta.setDisplayName("§a" + r.getName());
            item.setAmount(count);
            item.setItemMeta(meta);
            inv.setItem(i, item);
        }

        ItemStack composition = new ItemStack(Material.BOOK);
        ItemMeta metaComp = composition.getItemMeta();
        metaComp.setDisplayName("§6§lCompo des rôles");

        List<String> lore = new ArrayList<>();
        if (activeRoles.isEmpty()) {
            lore.add("§7Aucun rôle activé pour le moment");
        } else {
            // Compter chaque rôle et stack dans le lore
            List<String> alreadyCounted = new ArrayList<>();
            for (Role r : activeRoles) {
                if (!alreadyCounted.contains(r.getName())) {
                    int count = 0;
                    for (Role r2 : activeRoles) {
                        if (r2.getName().equals(r.getName())) count++;
                    }
                    lore.add("§a- " + r.getName() + " x" + count);
                    alreadyCounted.add(r.getName());
                }
            }
        }

        metaComp.setLore(lore);
        composition.setItemMeta(metaComp);

        // Placer l’item récap au centre du bas
        inv.setItem(getSize() - 5, composition);
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        if (!Objects.equals(event.getInventory().getName(), "§7§lGestion des rôles" ) && event.getRawSlot() == 3*9) return; // Seulement notre GUI

        event.setCancelled(true);

        List<Role> activeRoles = UHCAPI.getInstance().getGameManager().getActiveRoles();

        if (clicked.getType() == Material.PAPER) {
            String roleName = clicked.getItemMeta().getDisplayName().replace("§a", "");
            Role clickedRole = null;
            for (Role r : UHCAPI.getInstance().getGameManager().getCurrentModule().getRoles()) {
                if (r.getName().equals(roleName)) clickedRole = r;
            }
            if (clickedRole == null) return;

            if(event.isLeftClick()){
                UHCAPI.getInstance().getGameManager().addActiveRole(clickedRole);
            }else {
                for (int i = 0; i < activeRoles.size(); i++) {
                    if (activeRoles.get(i).getName().equals(clickedRole.getName())) {
                        UHCAPI.getInstance().getGameManager().removeActiveRole(clickedRole);
                        break;
                    }
                }
            }


            // Rafraîchir le GUI
            UHCAPI.getInstance().getGUIManager().open(player, RolesGUI.class);

        } else if (clicked.getType() == Material.BOOK) {
            if (!Objects.equals(event.getInventory().getName(), "§7§lGestion des rôles" ) && event.getRawSlot() == 3*9) return; // Seulement notre GUI

            player.sendMessage("§6Composition des rôles activés :");

            if (activeRoles.isEmpty()) player.sendMessage("§7Aucun rôle activé");
            else {
                List<String> alreadyCounted = new ArrayList<>();
                for (Role r : activeRoles) {
                    if (!alreadyCounted.contains(r.getName())) {
                        int count = 0;
                        for (Role r2 : activeRoles) {
                            if (r2.getName().equals(r.getName())) count++;
                        }
                        player.sendMessage("§a- " + r.getName() + " x" + count);
                        alreadyCounted.add(r.getName());
                    }
                }
            }
        }
    }



}