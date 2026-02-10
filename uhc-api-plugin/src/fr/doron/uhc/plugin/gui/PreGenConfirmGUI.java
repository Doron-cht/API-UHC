package fr.doron.uhc.plugin.gui;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.game.GameManager;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import fr.doron.uhc.plugin.world.UHCWorldManager;
import fr.doron.uhc.plugin.world.WorldGeneration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PreGenConfirmGUI implements GUIBuilder {

    @Override
    public String name() {
        return "§7Confirmer la pré-génération";
    }

    @Override
    public int getSize() {
        return 3 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        ItemStack confirm = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = confirm.getItemMeta();
        meta.setDisplayName("§aConfirmer");
        confirm.setItemMeta(meta);
        inv.setItem(11, confirm);

        ItemStack cancel = new ItemStack(Material.REDSTONE_BLOCK);
        meta = cancel.getItemMeta();
        meta.setDisplayName("§cAnnuler");
        cancel.setItemMeta(meta);
        inv.setItem(15, cancel);
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        if (current == null || !current.hasItemMeta()) return;

        switch (current.getType()) {
            case EMERALD_BLOCK:
                player.closeInventory();
                player.sendMessage("§aPré-génération du monde UHC en cours...");
                player.closeInventory();
                if (!WorldGeneration.isFinished()) {
                    if (!(WorldGeneration.getPercentage() > (double)0.0F)) {
                        Bukkit.broadcastMessage("§7▎ §fCréation du monde §auhc§f.");
                        UHCAPI.getInstance().getGameManager().getLobbyManager().createUHCWorld();
                        Bukkit.getScheduler().runTaskLater(UHCAPI.getInstance(), () -> {
                            Bukkit.broadcastMessage("§7▎ §fLa prégénération vient de commencer...");
                            (new WorldGeneration(Bukkit.getWorld("uhc"),300)).load();
                        }, 160L);
                    }
                }
                if (UHCWorldManager.getWorld() != null) {
                    player.sendMessage("§aPré-génération terminée !");
                } else {
                    player.sendMessage("§cErreur : le monde n'a pas pu être créé !");
                }
                break;

            case REDSTONE_BLOCK:
                player.closeInventory();
                player.sendMessage("§cPré-génération annulée !");
                break;

            default: break;
        }
    }
}