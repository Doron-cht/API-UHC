package fr.doron.uhc.plugin.world;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.ProgressBar;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class UHCWorldManager {

    public static final String WORLD_NAME = "uhc";

    public static void createWorld() {
        World world = Bukkit.getWorld(WORLD_NAME);

        if (world != null) return;

        WorldCreator creator = new WorldCreator(WORLD_NAME);
        creator.type(WorldType.NORMAL);
        creator.environment(World.Environment.NORMAL);
        creator.createWorld();
        UHCWorldManager.generateRoofedForestCenter(350);

        if (world != null) {
            setupRules(world); // passe le monde directement
        }

    }

    public static void unloadWorld(boolean save) {
        World world = Bukkit.getWorld(WORLD_NAME);
        if (world == null) return;

        Bukkit.unloadWorld(world, save);
    }

    public static World getWorld() {
        return Bukkit.getWorld(WORLD_NAME);
    }
    private static void setupRules(World world) {
        world.setGameRuleValue("doDaylightCycle", "false");
        world.setGameRuleValue("doWeatherCycle", "false");
        world.setStorm(false);
        world.setThundering(false);
        world.setTime(6000);
    }
    public static void deleteWorld() {
        World world = Bukkit.getWorld(WORLD_NAME);
        if (world != null) {
            Bukkit.unloadWorld(world, false);
        }

        File folder = new File(Bukkit.getWorldContainer(), WORLD_NAME);
        deleteFolder(folder);
    }

    private static void deleteFolder(File file) {
        if (file == null || !file.exists()) return;

        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                deleteFolder(child);
            }
        }
        file.delete();
    }
    public static void generateRoofedForestCenter(int radius) {
        if (getWorld() == null) return;

        final int chunkRadius = (int) Math.ceil(radius / 16.0);
        final int totalChunks = (chunkRadius * 2 + 1) * (chunkRadius * 2 + 1);

        new BukkitRunnable() {

            int cx = -chunkRadius;
            int cz = -chunkRadius;
            int done = 0;

            @Override
            public void run() {
                if (cx > chunkRadius) {
                    // Pré-génération terminée
                    Bukkit.getLogger().info("Pré-génération terminée !");
                    // Action bar finale pour tous les joueurs
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        sendActionBar(p, "§aPré-génération terminée !");
                    }
                    cancel();
                    return;
                }

                // Charger et générer le chunk
                Chunk chunk = getWorld().getChunkAt(cx, cz);
                chunk.load(true);
                generateChunk(chunk);

                // Avancement
                done++;
                double percent = (done * 100.0) / totalChunks;

                // Affichage action bar pour tous les joueurs
                String bar = getProgressBar(done, totalChunks, 20);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    sendActionBar(p, "§ePré-génération : §6" + String.format("%.1f", percent) + "% " + bar);
                }

                // Avancer les coordonnées
                cz++;
                if (cz > chunkRadius) {
                    cz = -chunkRadius;
                    cx++;
                }
            }
        }.runTaskTimer(UHCAPI.getInstance(), 0L, 1L); // 1 chunk par tick
    }
    public static String getProgressBar(int done, int total, int size) {
        double percent = (double) done / total;
        int progress = (int) (percent * size);
        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            if (i < progress) {
                bar.append('█'); // partie remplie
            } else {
                bar.append(' '); // partie vide
            }
        }
        bar.append("]");
        return bar.toString();
    }
    private static void generateChunk(Chunk chunk) {
        // Exemple : juste pour le moment, on force le biome à Roofed Forest
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunk.getX() * 16 + x;
                int worldZ = chunk.getZ() * 16 + z;
                getWorld().setBiome(worldX + x, worldZ + z, Biome.ROOFED_FOREST);



                // tu peux ici placer tes blocs, arbres, etc.
                // chunk.getBlock(x, y, z).setType(Material.LOG); etc.
            }
        }
    }
    public static void sendActionBar(Player p, String message) {
        try {
            Object packet = Class.forName("net.minecraft.server.v1_8_R3.PacketPlayOutChat")
                    .getConstructor(Class.forName("net.minecraft.server.v1_8_R3.IChatBaseComponent"), byte.class)
                    .newInstance(
                            Class.forName("net.minecraft.server.v1_8_R3.IChatBaseComponent$ChatSerializer")
                                    .getMethod("a", String.class)
                                    .invoke(null, "{\"text\":\"" + message + "\"}"),
                            (byte) 2
                    );

            Object handle = p.getClass().getMethod("getHandle").invoke(p);
            Object connection = handle.getClass().getField("playerConnection").get(handle);
            connection.getClass().getMethod("sendPacket",
                    Class.forName("net.minecraft.server.v1_8_R3.Packet")).invoke(connection, packet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}