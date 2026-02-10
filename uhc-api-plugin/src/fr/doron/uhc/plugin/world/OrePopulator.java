package fr.doron.uhc.plugin.world;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.v1_8_R3.ChunkSnapshot;
import net.minecraft.server.v1_8_R3.WorldGenCanyon;
import net.minecraft.server.v1_8_R3.WorldGenCaves;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.generator.BlockPopulator;

public class OrePopulator extends BlockPopulator {
    private final ArrayList<Rule> rules = new ArrayList();

    private int randInt(Random rand, int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    public void addRule(Rule rule) {
        if (!this.rules.contains(rule)) {
            this.rules.add(rule);
        }

    }

    public void populate(World world, Random random, Chunk chunk) {
        if (chunk != null) {
            CraftWorld handle = (CraftWorld)world;
            int xr = this.randInt(random, -200, 200);
            if (xr >= 50) {
                (new WorldGenCaves()).a(handle.getHandle().chunkProviderServer, handle.getHandle(), chunk.getX(), chunk.getZ(), new ChunkSnapshot());
            } else if (xr <= -50) {
                (new WorldGenCanyon()).a(handle.getHandle().chunkProviderServer, handle.getHandle(), chunk.getX(), chunk.getZ(), new ChunkSnapshot());
            }

            for(Rule bloc : this.rules) {
                for(int i = 0; i < bloc.round; ++i) {
                    int x = chunk.getX() * 16 + random.nextInt(16);
                    int y = bloc.minY + random.nextInt(bloc.maxY - bloc.minY);
                    int z = chunk.getZ() * 16 + random.nextInt(16);
                    this.generate(world, random, x, y, z, bloc.size, bloc);
                }
            }

        }
    }

    private void generate(World world, Random rand, int x, int y, int z, int size, Rule material) {
        double rpi = rand.nextDouble() * Math.PI;
        double x2 = (double)(x + 8) + Math.sin(rpi) * (double)size / (double)8.0F;
        double x3 = (double)(x + 8) - Math.sin(rpi) * (double)size / (double)8.0F;
        double z2 = (double)(z + 8) + Math.cos(rpi) * (double)size / (double)8.0F;
        double z3 = (double)(z + 8) - Math.cos(rpi) * (double)size / (double)8.0F;
        double y2 = (double)(y + rand.nextInt(3) + 2);
        double y3 = (double)(y + rand.nextInt(3) + 2);

        for(int i = 0; i <= size; ++i) {
            double xPos = x2 + (x3 - x2) * (double)i / (double)size;
            double yPos = y2 + (y3 - y2) * (double)i / (double)size;
            double zPos = z2 + (z3 - z2) * (double)i / (double)size;
            double fuzz = rand.nextDouble() * (double)size / (double)16.0F;
            double fuzzXZ = (Math.sin((double)((float)((double)i * Math.PI / (double)size))) + (double)1.0F) * fuzz + (double)1.0F;
            double fuzzY = (Math.sin((double)((float)((double)i * Math.PI / (double)size))) + (double)1.0F) * fuzz + (double)1.0F;
            int xStart = (int)Math.floor(xPos - fuzzXZ / (double)2.0F);
            int yStart = (int)Math.floor(yPos - fuzzY / (double)2.0F);
            int zStart = (int)Math.floor(zPos - fuzzXZ / (double)2.0F);
            int xEnd = (int)Math.floor(xPos + fuzzXZ / (double)2.0F);
            int yEnd = (int)Math.floor(yPos + fuzzY / (double)2.0F);
            int zEnd = (int)Math.floor(zPos + fuzzXZ / (double)2.0F);

            for(int ix = xStart; ix <= xEnd; ++ix) {
                double xThresh = ((double)ix + (double)0.5F - xPos) / (fuzzXZ / (double)2.0F);
                if (xThresh * xThresh < (double)1.0F) {
                    for(int iy = yStart; iy <= yEnd; ++iy) {
                        double yThresh = ((double)iy + (double)0.5F - yPos) / (fuzzY / (double)2.0F);
                        if (xThresh * xThresh + yThresh * yThresh < (double)1.0F) {
                            for(int iz = zStart; iz <= zEnd; ++iz) {
                                double zThresh = ((double)iz + (double)0.5F - zPos) / (fuzzXZ / (double)2.0F);
                                if (xThresh * xThresh + yThresh * yThresh + zThresh * zThresh < (double)1.0F) {
                                    Block block = this.getBlock(world, ix, iy, iz);
                                    if (block != null && block.getType() == Material.STONE) {
                                        block.setType(material.id);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private Block getBlock(World world, int x, int y, int z) {
        int cx = x >> 4;
        int cz = z >> 4;
        if (!world.isChunkLoaded(cx, cz) && !world.loadChunk(cx, cz, false)) {
            return null;
        } else {
            Chunk chunk = world.getChunkAt(cx, cz);
            return chunk == null ? null : chunk.getBlock(x & 15, y, z & 15);
        }
    }

    public static class Rule {
        public Material id;
        public int round;
        public int minY;
        public int maxY;
        public int size;

        public Rule(Material type, int round, int minY, int maxY, int size) {
            this.id = type;
            this.round = round;
            this.minY = minY;
            this.maxY = maxY;
            this.size = size;
        }
    }
}
