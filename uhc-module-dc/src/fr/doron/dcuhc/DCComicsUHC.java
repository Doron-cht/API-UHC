package fr.doron.dcuhc;

import fr.doron.dcuhc.roles.BatMan.BatManListener;
import fr.doron.dcuhc.roles.Flash.FlashGUI;
import fr.doron.dcuhc.roles.Flash.FlashListener;
import fr.doron.dcuhc.roles.DCRoles;
import fr.doron.dcuhc.roles.commands.DCCommands;
import fr.doron.uhc.api.GameModeModule;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import fr.doron.uhc.plugin.tools.GUIManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DCComicsUHC extends JavaPlugin implements GameModeModule {
    private Map<Class<? extends GUIBuilder>, GUIBuilder> registeredMenus;


    @Override
    public void onEnable() {
        UHCAPI api = UHCAPI.getInstance();
        if (api != null) {
            api.getGameModeManager().register(this);
            Bukkit.getLogger().info("§eDC Comics UHC registered!");
        } else {
            Bukkit.getLogger().warning("§eUHC API non trouvée ! Le module ne peut pas s'enregistrer.");
        }
        getCommand("dc").setExecutor(new DCCommands());
        Bukkit.getPluginManager().registerEvents(new FlashListener(), this);
        Bukkit.getPluginManager().registerEvents(new BatManListener(), this);
        loadGUI();
        new BukkitRunnable() {
            @Override
            public void run() {
                UHCAPI.getInstance().getScoreboardManager().updateAll();
            }
        }.runTaskTimer(this, 0L, 20L);
    }

    private void loadGUI(){
        GUIManager guimanager = new GUIManager();
        registeredMenus = new HashMap<>();
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Chargement des menus DC COMICS...");
        guimanager.addMenu(new FlashGUI());



    }

    @Override
    public String getId() {
        return "dc_comics";
    }

    @Override
    public String getDisplayName() {
        return "DC Comics UHC";
    }

    @Override
    public void onGameStart() {
        Bukkit.broadcastMessage("§bLa partie de DC Comics UHC est lancé");
    }

    @Override
    public void onGameEnd() {
        Bukkit.broadcastMessage("§bUHC DC COMICS SE TERMINE");
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        for(DCRoles r : DCRoles.values()) {
            try {
                roles.add(r.getRoleClass().newInstance()); // instanciation dynamique
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return roles;
    }
}
//TODO Créer toutes les classes de tout les roles
// TODO Mettre les description de tout les roles dans le JSON
//TODO Tester tout les roles