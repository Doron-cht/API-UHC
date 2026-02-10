package fr.doron.uhc.plugin.gui;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class HostGUI implements GUIBuilder {

    private ItemStack Serveur(){
        ItemStack serveur = new ItemStack(Material.TORCH);
        ItemMeta metaServeur = serveur.getItemMeta();
        metaServeur.setDisplayName("§e"+ puce() + " §fServeur");
        metaServeur.setLore(Arrays.asList(" ","§8"+ guillemet() +" §7Configurer cet onglet"));
        serveur.setItemMeta(metaServeur);
        return serveur;
    }

    private ItemStack ModuleSelector() {
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName("§e" + puce() + " §fSélection du Module");

        // Liste des modules disponibles
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        UHCAPI.getInstance().getGameModeManager().getAll().forEach(module ->
                lore.add("§e- §f" + module.getDisplayName())
        );
        meta.setLore(lore);

        book.setItemMeta(meta);
        return book;
    }
    private ItemStack PreGen() {
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§e" + puce() + " §fPré-génération du monde");
        meta.setLore(Arrays.asList("§7Clique pour lancer la pré-génération"));
        item.setItemMeta(meta);
        return item;
    }


    private ItemStack Timer(){
        ItemStack timer = new ItemStack(Material.WATCH);
        ItemMeta metatimer = timer.getItemMeta();
        metatimer.setDisplayName("§e"+ puce() + " §fTimers");
        metatimer.setLore(Arrays.asList(" ","§8"+ guillemet() +" §7Configurer cet onglet"));
        timer.setItemMeta(metatimer);
        return timer;
    }
    private ItemStack ModeVocal() {
        ItemStack modevocal = new ItemStack(Material.NOTE_BLOCK);
        ItemMeta metamodevocal = modevocal.getItemMeta();
        metamodevocal.setDisplayName("§e"+ puce() + " §fMode Vocal");
        metamodevocal.setLore(Arrays.asList("§f ","§e" + petitePuce() + (UHCAPI.getInstance().getGameManager().isMumble() ? " §cAucun": " §aAucun"), " §f","§e" + petitePuce() + (UHCAPI.getInstance().getGameManager().isMumble() ? " §aMumble" : " §cMumble")));
        modevocal.setItemMeta(metamodevocal);
        return modevocal;
    }
    private ItemStack SettingsUHC() {
        ItemStack settings = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta metasettings = settings.getItemMeta();
        metasettings.setDisplayName("§e"+ puce() + " §fParamètres de l'UHC");
        metasettings.setLore(Arrays.asList(" ","§8"+ guillemet() +" §7Configurer cet onglet"));
        settings.setItemMeta(metasettings);
        return settings;
    }
    private ItemStack StartGameButton() {
        ItemStack start = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = start.getItemMeta();
        meta.setDisplayName("§e" + puce() + "§a§lStart Partie");
        meta.setLore(Arrays.asList("§7Cliquez pour démarrer la partie UHC"));
        start.setItemMeta(meta);
        return start;
    }
    private ItemStack RolesButton() {
        ItemStack roles = new ItemStack(Material.PAPER);
        ItemMeta meta = roles.getItemMeta();
        meta.setDisplayName("§e§lListe des rôles");
        meta.setLore(Arrays.asList("§7Cliquez pour gérer les rôles disponibles"));
        roles.setItemMeta(meta);
        return roles;
    }
    // Dans ta classe HostGUI, méthode pour ouvrir le GUI principal


// Dans ton listener InventoryClickEvent






    @Override
    public String name() {
        return "§7§lMenu Principal";
    }

    @Override
    public int getSize() {
        return 6 * 9;
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


        inv.setItem(3, Serveur());
        //inv.setItem(3, beacon());
        inv.setItem(5, Timer());
        inv.setItem(13, ModeVocal());
        inv.setItem(19, SettingsUHC());


        inv.setItem(21, ModuleSelector());
        inv.setItem(22, RolesButton());
        inv.setItem(23, PreGen());


        inv.setItem(40, StartGameButton());




    }



    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        switch(current.getType()){
            case BOOK:
                player.sendMessage("§7Sélection du module...");
                player.closeInventory();
                // Ici tu peux ouvrir un nouveau GUI qui permet de choisir un module à lancer
                UHCAPI.getInstance().getGUIManager().open(player, ModuleSelectionGUI.class);
                break;
            case TORCH:
                player.sendMessage("§7Menu Serveur...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostServeurGUI.class);
                break;
            case WATCH:
                player.sendMessage("§7Menu Timer...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostTimerGUI.class);
                break;
            case NOTE_BLOCK:
                UHCAPI.getInstance().getGameManager().setMumble(!UHCAPI.getInstance().getGameManager().isMumble());
                player.sendMessage((UHCAPI.getInstance().getGameManager().isMumble() ? "§7Mumble §aActivé": "§fMumble §cDésactivé"));
                UHCAPI.getInstance().getGUIManager().open(player, HostGUI.class);
                break;
            case GOLDEN_APPLE:
                player.sendMessage("§7Menu Settings...");
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, HostSettingsGUI.class);
            case EMERALD_BLOCK:
                player.closeInventory();
                if (UHCAPI.getInstance().getGameManager() != null) {
                    if (UHCAPI.getInstance().getGameManager().isRunning()) {
                        player.sendMessage("§cUne partie est déjà en cours !");
                    } else {
                        UHCAPI.getInstance().getGameManager().startPreGameCountdown();
                    }
                } else {
                    player.sendMessage("§cLe GameManager n'est pas initialisé !");
                }
                break;
            case ENDER_PEARL:
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, PreGenConfirmGUI.class);
                break;
            case PAPER:
                player.closeInventory();
                UHCAPI.getInstance().getGUIManager().open(player, RolesGUI.class);
                break;

            default: break;
        }
    }
}
