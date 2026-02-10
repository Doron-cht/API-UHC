package fr.doron.uhc.plugin.world;

import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.Title;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WorldGeneration {
    public static boolean finished;
    private static double percentage = (double)0.0F;
    private Double currentChunkLoad;
    private final Double totalChunkToLoad;
    private int cx;
    private int cz;
    private final int radius;
    private final List<List<Integer>> integersList = new ArrayList();
    private final World world;

    public WorldGeneration(World world, int r) {
        r += 75;
        finished = false;
        percentage = (double)0.0F;
        this.totalChunkToLoad = Math.pow((double)r, (double)2.0F) / (double)64.0F;
        this.currentChunkLoad = (double)0.0F;
        this.cx = -r;
        this.cz = -r;
        this.world = world;
        this.radius = r;
    }

    public void load() {
        (new BukkitRunnable() {
            public void run() {
                for(int i = 0; i < 30 && !WorldGeneration.isFinished(); ++i) {
                    Location loc = new Location(WorldGeneration.this.world, (double)WorldGeneration.this.cx, (double)0.0F, (double)WorldGeneration.this.cz);
                    if (!loc.getChunk().isLoaded()) {
                        loc.getWorld().loadChunk(loc.getChunk().getX(), loc.getChunk().getZ(), true);
                    }

                    WorldGeneration.this.cx = WorldGeneration.this.cx + 16;
                    WorldGeneration.this.currentChunkLoad = WorldGeneration.this.currentChunkLoad + (double)1.0F;
                    if (WorldGeneration.this.cx > WorldGeneration.this.radius) {
                        WorldGeneration.this.cx = -WorldGeneration.this.radius;
                        WorldGeneration.this.cz = WorldGeneration.this.cz + 16;
                        if (WorldGeneration.this.cz > WorldGeneration.this.radius) {
                            WorldGeneration.this.currentChunkLoad = WorldGeneration.this.totalChunkToLoad;
                            WorldGeneration.setFinished(true);
                        }
                    }
                }

                WorldGeneration.percentage = WorldGeneration.this.currentChunkLoad / WorldGeneration.this.totalChunkToLoad * (double)100.0F;
                if (WorldGeneration.isFinished()) {
                    this.cancel();
                }

            }
        }).runTaskTimer(UHCAPI.getInstance(), 1L, 5L);
        this.sendMessage();
    }

    public static boolean isFinished() {
        return finished;
    }

    public static Double getPercentage() {
        return percentage;
    }

    public static void setFinished(boolean finished) {
        WorldGeneration.finished = finished;
    }

    private void sendMessage() {
        (new BukkitRunnable() {
            public void run() {
                if (WorldGeneration.isFinished()) {
                    this.cancel();
                }

                DecimalFormat format = new DecimalFormat("#.#");
                Title.sendActionBar("§aPrégénération: §8(§a" + format.format(WorldGeneration.getPercentage()) + "%§8) §8» §aChunks: §8(§a" + WorldGeneration.this.currentChunkLoad.intValue() + "§8/§a" + WorldGeneration.this.totalChunkToLoad.intValue() + "§8)  §8» §aTPS: §8(§a" + format.format(MinecraftServer.getServer().recentTps[0]) + "§8)");
            }
        }).runTaskTimer(UHCAPI.getInstance(), 1L, 1L);
    }
}
