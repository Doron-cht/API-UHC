package fr.doron.uhc.plugin.game;

import fr.doron.uhc.api.GameModeModule;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.world.LobbyManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class GameManager {
    private boolean isHostPlay = true;
    private int MaxSlot = 20;
    private int OnlinePlayer = UHCAPI.getInstance().getPlayersForTheGame().size();
    private int FinalHeal = 10*60;
    private int PvPTimer = 20*60;
    private int RoleTimer = 20*60;
    private int borderTime = 60*60;
    private int gameTimer = 0;
    private boolean isMumble = false;
    private GameModeModule currentModule;
    private BukkitRunnable timerTask;
    private int secondsElapsed= 0;
    // Indique si une partie est en cours
    private boolean isRunning = false;
    private List<Role> activeRoles = new ArrayList<>();
    // Dans GameManager
    private Map<Player, Role> playerRoles = new HashMap<>();
    private boolean preGame = false;
    private World lobbyworld = Bukkit.getWorld("world");
    private World uhcworld = Bukkit.getWorld("uhc");
    private final Location lobby = new Location(lobbyworld, 0 ,201 ,0);
    private LobbyManager lobbyManager = new LobbyManager(this);
    ;

    public boolean isPreGame() {
        return preGame;
    }

    public void setPreGame(boolean preGame) {
        this.preGame = preGame;
    }

    // Méthodes utilitaires
    public void clearRoles() {
        playerRoles.clear();
    }

    public Role getRole(Player p) {
        return playerRoles.get(p);
    }

    public boolean hasRole(Player p, Class<? extends Role> roleClass) {
        Role r = getRole(p);
        return r != null && roleClass.isInstance(r);
    }

    public void addActiveRole(Role r) {
        activeRoles.add(r);
    }
    public void removeActiveRole(Role r) {
        for (int i = activeRoles.size() - 1; i >= 0; i--) {
            if (activeRoles.get(i).getName().equals(r.getName())) {
                activeRoles.remove(i);
                break;
            }
        }
    }
    public void HostSendMessage(String message){
        if(hasHost()){
            host.sendMessage(message);
        }

    }
    public static void teleportAllToUHC() {
        World world = Bukkit.getWorld("uhc");
        if (world == null) return;

        Location spawn = new Location(world, 0.5, 90, 0.5);

        for (Player p : UHCAPI.getInstance().getPlayersForTheGame()) {
            p.teleport(spawn);
        }
    }


    public Map<Player, Role> getPlayerRoles() {
        return playerRoles;
    }

    public List<Role> getActiveRoles() {
        return activeRoles;
    }

    // Getter
    public boolean isRunning() {
        return isRunning;
    }




    private Player host;

    public boolean IsHostPlay(){
        return isHostPlay;
    }

    public Location getLobby() {
        return this.lobby;
    }
    public void setHostPlay(boolean truefalse){
        isHostPlay = truefalse;
    }

    public int getMaxSlot(){
        return MaxSlot;
    }
    public int getOnlinePlayer(){
        return OnlinePlayer;
    }

    public void setMaxSlot(int maxSlot) {
        MaxSlot = maxSlot;
    }
    public void setHost(Player player) {
        this.host = player;
    }

    public Player getHost() {
        return host;
    }

    public boolean isHost(Player player) {
        return host != null && host.equals(player);
    }

    public boolean hasHost() {
        return host != null;
    }

    public void clearHost() {
        this.host = null;
    }

    public int getFinalHeal() {
        return FinalHeal;
    }

    public void setFinalHeal(int finalHeal) {
        FinalHeal = finalHeal;
    }
    public int FinalHealMinutes(){
        return getFinalHeal() / 60;
    }
    public int FinalHealSeconds(){
        return getFinalHeal() % 60;
    }
    public int getPvPTimer() {
        return PvPTimer;
    }

    public void setPvPTimer(int pvptime) {
        PvPTimer = pvptime;
    }
    public int PvPTimerMinutes(){
        return getPvPTimer() / 60;
    }
    public int PvPTimerSeconds(){
        return getPvPTimer() % 60;
    }
    public int getRoleTimer() {
        return RoleTimer;
    }

    public void setRoleTimer(int roleTimer) {
        RoleTimer = roleTimer;
    }
    public int RoleTimerMinutes(){
        return getRoleTimer() / 60;
    }
    public int RoleTimerSeconds(){
        return getRoleTimer() % 60;
    }


    //public Location getCenter() {
   //     return new Location(this.getCenter().getWorld(), (double)0.0F, (double)100.0F, (double)0.0F);
    //}

    public World getUhcWorld() {
        return this.uhcworld;
    }

    public void setUhcWorld(World uhcworld) {
        this.uhcworld = uhcworld;
    }



    public void clearAllPlayers(){
        UHCAPI.getInstance().getPlayersForTheGame().forEach((player) -> {
            player.getActivePotionEffects().forEach((potionEffect) -> player.removePotionEffect(potionEffect.getType()));
            player.getInventory().clear();
            player.getInventory().setArmorContents((ItemStack[])null);
            player.setMaxHealth((double)20.0F);
            player.setSaturation(20.0F);
            player.setFoodLevel(20);
            player.setWalkSpeed(0.2F);
            player.setGameMode(GameMode.SURVIVAL);
        });
    }
    public boolean isMumble() {
        return isMumble;
    }

    public void setCurrentModule(GameModeModule module) {
        this.currentModule = module;
    }
    public void startPreGameCountdown() {
        List<Player> players = UHCAPI.getInstance().getPlayersForTheGame();
        final int countdownSeconds = 10;
        setPreGame(true); // on est en pré-game

        new BukkitRunnable() {
            int timer = countdownSeconds;

            @Override
            public void run() {
                if (timer <= 0) {
                    for (Player p : players) {
                        p.setLevel(0);
                        p.setExp(0f);

                    }

                    setPreGame(false); // fin du pré-game
                    startGame();       // ton startGame() actuel, les timers démarrent ici
                    cancel();
                    return;
                }

                for (Player p : players) {
                    p.setLevel(timer);
                    p.setExp((float) timer / countdownSeconds);
                    p.sendTitle("§6Démarrage dans", "§e" + timer);
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 1f);
                }

                timer--;
            }
        }.runTaskTimer(UHCAPI.getInstance(), 0L, 20L);
        teleportAllToUHC();
        clearAllPlayers();
    }
    public int resertTimer(){return secondsElapsed = 0;}

    public GameModeModule getCurrentModule() {
        return currentModule;
    }
    public void startGame() {
        List<Player> players = UHCAPI.getInstance().getPlayersForTheGame();

        if (isRunning || isPreGame()) {
            Bukkit.getLogger().info("[UHC] La partie est déjà en cours !");
            return;
        }

        if (currentModule == null) {
            System.out.println("[UHC] Aucun module actif ! La partie ne peut pas démarrer.");
            return;
        }
        if (players == null || players.isEmpty()) {
            System.out.println("[UHC] Aucun joueur dans la partie ! La partie ne peut pas démarrer.");
            return;
        }
        if(canStart()){
            for (Player p : Bukkit.getOnlinePlayers()) {
                UHCAPI.getInstance().getScoreboardManager().setGameBoard(p);
            }

            isRunning = true; // ← partie démarrée

            // Message début de partie
            Bukkit.broadcastMessage("§aPartie démarrée ! Module : §f" + currentModule.getDisplayName());
            HostSendMessage("§eEH L'HOST LA PARTIE ELLE A DEMMARRE");
            // Appel onGameStart du module
            currentModule.onGameStart();

            // Démarrage des timers
            if (isRunning() && !isPreGame()) {
                startTimers();
            }

        }else{
            HostSendMessage("§eIl n'a pas le meme nombre de joueurs que de roles");
        }


    }

    public void setMumble(boolean mumble) {
        isMumble = mumble;
    }
    public void endGame() {
        if (!isRunning) return;
        if(preGame) return;

        if (currentModule != null) currentModule.onGameEnd();
        if (timerTask != null) timerTask.cancel();
        List<Player> players = new ArrayList<>(UHCAPI.getInstance().getPlayersForTheGame());
        for (Player p : players) {
            p.getActivePotionEffects().clear();
        }

        isRunning = false;

        Bukkit.broadcastMessage("§cLa partie a été stoppée !");
        for (Player p : Bukkit.getOnlinePlayers()) {
            UHCAPI.getInstance().getScoreboardManager().setLobbyBoard(p);
        }
        this.clearRoles();

    }
    public boolean canStart() {
        int players = UHCAPI.getInstance().getPlayersForTheGame().size();
        int roles = activeRoles.size();

        return players == roles;
    }
    private void startTimers() {
        secondsElapsed = 0;

        timerTask = new BukkitRunnable() {
            @Override
            public void run() {
                secondsElapsed++; // ← incrément chaque seconde

                // PvP
                if(secondsElapsed == PvPTimer) {
                    Bukkit.broadcastMessage("§cLe PvP est maintenant activé !");
                }

                // Final Heal
                if(secondsElapsed == FinalHeal) {
                    Bukkit.broadcastMessage("§aFinal Heal !");
                }

                // Bordure
                if(secondsElapsed == borderTime) {
                    Bukkit.broadcastMessage("§eLa bordure commence à se rétracter !");
                }
                if(secondsElapsed == RoleTimer){
                    Bukkit.broadcastMessage("§aAnnonce des roles...");
                    assignRoles();
                }

            }
        };

        timerTask.runTaskTimer(UHCAPI.getInstance(), 20, 20); // 20 ticks = 1 sec
    }

    public void assignRoles() {
        List<Role> rolesToAssign = new ArrayList<>(this.getActiveRoles()); // <-- la compo choisie
        List<Player> players = new ArrayList<>(UHCAPI.getInstance().getPlayersForTheGame());

        if (rolesToAssign.isEmpty()) {
            Bukkit.getLogger().warning("Aucun rôle actif pour cette partie !");
            return;
        }

        java.util.Collections.shuffle(rolesToAssign);
        java.util.Collections.shuffle(players);


        int roleIndex = 0;
        for (Player p : players) {
            if (roleIndex >= rolesToAssign.size()) roleIndex = 0;// reboucle si moins de rôles que de joueurs
            p.getActivePotionEffects().clear();
            Role r = rolesToAssign.get(roleIndex);
            playerRoles.put(p, r); // stocke directement dans la map
            r.onAssign(p);

            roleIndex++;

        }

    }

    public int getBorderTime() {
        return borderTime;
    }
    public int getElapsedSeconds() {
        return secondsElapsed;
    }

    public int getGameTime() {
        return secondsElapsed;
    }


    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }
}
