package fr.doron.uhc.plugin;

import fr.doron.uhc.api.GameModeManager;
import fr.doron.uhc.api.GameModeModule;
import fr.doron.uhc.plugin.command.*;
import fr.doron.uhc.plugin.game.GameManager;
import fr.doron.uhc.plugin.gui.*;
import fr.doron.uhc.plugin.listeners.*;

import fr.doron.uhc.plugin.scoreboard.UHCScoreboardManager;
import fr.doron.uhc.plugin.tools.GUIBuilder;
import fr.doron.uhc.plugin.tools.GUIManager;
import fr.doron.uhc.plugin.tools.GameModeManagerImpl;
import fr.doron.uhc.plugin.tools.InvisibleBaseManager;
import fr.doron.uhc.plugin.tools.PotionUtils;
import fr.doron.uhc.plugin.world.UHCWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class UHCAPI extends JavaPlugin {

    public static UHCAPI INSTANCE;
    public static GameManager gameManager;
    private GameModeManager gameModeManager;
    private UHCScoreboardManager scoreboardManager;

    public UHCScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }


    private final List<Player> playersforthegame = new ArrayList<>();


    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;

    private GUIManager guimanager;
    private Map<Class<? extends GUIBuilder>, GUIBuilder> registeredMenus;




    @Override
    public void onEnable() {
        INSTANCE = this;
        registerCommands();
        registerListeners();
        gameManager = new GameManager();
        scheduledExecutorService = Executors.newScheduledThreadPool(16);
        executorMonoThread = Executors.newScheduledThreadPool(1);
        this.gameModeManager = new GameModeManagerImpl();
        scoreboardManager = new UHCScoreboardManager();
        InvisibleBaseManager.generate(Bukkit.getWorlds().get(0));

        for (Player p : Bukkit.getOnlinePlayers()) {
            scoreboardManager.setLobbyBoard(p);
            p.teleport(new Location(Bukkit.getWorld("world"), 0.5, 201, 0.5));
        }
        if (gameManager.getHost() != null){
            addPlayer(gameManager.getHost());
        }

        loadGUI();
        new BukkitRunnable() {
            @Override
            public void run() {
                UHCAPI.getInstance().getScoreboardManager().updateAll();
            }
        }.runTaskTimer(this, 0L, 20L);


        super.onEnable();

    }
    public List<Player> getPlayersForTheGame() {
        return playersforthegame;
    }
    public void addPlayer(Player player) {
        if(!playersforthegame.contains(player)) {
            playersforthegame.add(player);
        }
    }


    public void removePlayer(Player player) {
        playersforthegame.remove(player);
    }

    @Override
    public void onDisable() {
        InvisibleBaseManager.clear();
        super.onDisable();
    }

    private void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new GUIManager(), this);
        pm.registerEvents(new HostClickItem(), this);
        pm.registerEvents(new RolesGUI(), this);
        pm.registerEvents(new onBlockListener(), this);
        pm.registerEvents(new PotionUtils(), this);

    }

    private void registerCommands(){
        this.getCommand("host").setExecutor(new CommandHost());
        this.getCommand("hostplay").setExecutor(new CommandHostPlay());
        this.getCommand("players").setExecutor(new CommandPlayers());
        this.getCommand("sethost").setExecutor(new CommandSetHost());
        this.getCommand("currentmodule").setExecutor(new CurrentModuleCommand());
        this.getCommand("player").setExecutor(new CommandPlayer());
        this.getCommand("startuhc").setExecutor((sender, command, label, args) -> {
            if (gameManager.isRunning()) {
                System.out.println("§cUne partie est déjà en cours !");
            } else {
                gameManager.startPreGameCountdown();
            }
            return true;
        });
        this.getCommand("uhcstop").setExecutor(new UHCStopCommand());
        this.getCommand("hsay").setExecutor(new CommandHSay());
        this.getCommand("uhctp").setExecutor(new CommandUHCtp());

        this.getCommand("timers").setExecutor(new TimersCommand());

        this.getCommand("mumble").setExecutor(new CommandMumble());
        getCommand("testuhc").setExecutor((sender, command, label, args) -> {
            GameModeModule m = getGameModeManager().get("dc_comics");
            if (m != null) {
                sender.sendMessage("Gamemode trouvé : " + m.getDisplayName());
                m.onGameStart();
            } else {
                sender.sendMessage("Gamemode introuvable !");
            }
            return true;
        });

    }
    public static UHCAPI getInstance(){
        return INSTANCE;
    }


    public ScheduledExecutorService getScheduledExecutorService(){
        return scheduledExecutorService;
    }

    public ScheduledExecutorService getExecutorMonoThread(){
        return executorMonoThread;
    }


    private void loadGUI(){
        getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Chargement des menus...");
        guimanager = new GUIManager();
        registeredMenus = new HashMap<>();
        guimanager.addMenu(new HostGUI());
        guimanager.addMenu(new HostServeurGUI());
        guimanager.addMenu(new HostSlotsGUI());
        guimanager.addMenu(new HostTimerGUI());
        guimanager.addMenu(new HostFHGUI());
        guimanager.addMenu(new HostPvPGUI());
        guimanager.addMenu(new HostSettingsGUI());
        guimanager.addMenu(new ModuleSelectionGUI());
        guimanager.addMenu(new RolesGUI());
        guimanager.addMenu(new PreGenConfirmGUI());
        guimanager.addMenu(new HostRoleGUI());


    }
    public Map<Class<? extends GUIBuilder>, GUIBuilder> getRegisteredMenus() {
        return registeredMenus;
    }
    public GUIManager getGUIManager(){
        return guimanager;
    }
    public GameManager getGameManager(){
        return gameManager;
    }
    public GameModeManager getGameModeManager() {
        return gameModeManager;
    }

}
