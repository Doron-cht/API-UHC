package fr.doron.uhc.plugin.tools;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class InvisibleBaseManager {

    private static final Set<Block> placedBlocks = new HashSet<>();

    private static final int CENTER_X = 0;
    private static final int CENTER_Y = 200;
    private static final int CENTER_Z = 0;

    private static final int SIZE = 50;
    private static final int WALL_HEIGHT = 3;

    public static void generate(World world) {
        clear();

        int half = SIZE / 2;

        // Sol
        for (int x = -half; x < half; x++) {
            for (int z = -half; z < half; z++) {
                place(world, CENTER_X + x, CENTER_Y, CENTER_Z + z);
            }
        }

        // Murs
        for (int y = 1; y <= WALL_HEIGHT; y++) {
            for (int x = -half; x < half; x++) {
                place(world, CENTER_X + x, CENTER_Y + y, CENTER_Z - half);
                place(world, CENTER_X + x, CENTER_Y + y, CENTER_Z + half - 1);
            }

            for (int z = -half; z < half; z++) {
                place(world, CENTER_X - half, CENTER_Y + y, CENTER_Z + z);
                place(world, CENTER_X + half - 1, CENTER_Y + y, CENTER_Z + z);
            }
        }
    }

    private static void place(World world, int x, int y, int z) {
        Block block = world.getBlockAt(x, y, z);
        block.setType(Material.BARRIER);
        placedBlocks.add(block);
    }

    public static void clear() {
        for (Block block : placedBlocks) {
            block.setType(Material.AIR);
        }
        placedBlocks.clear();
    }
}