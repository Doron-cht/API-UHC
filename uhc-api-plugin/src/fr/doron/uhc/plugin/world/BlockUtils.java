package fr.doron.uhc.plugin.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockUtils {
    public static final Map<Location, String> blockTags = new HashMap();

    public static List<Block> getBlocksAroundPlayer(Player player, Location l, int radius, boolean parsedAir, String tag) {
        List<Block> blocks = new ArrayList();
        Location playerLocation = l;
        World world = player.getWorld();
        int minX = l.getBlockX() - radius;
        int maxX = l.getBlockX() + radius;
        int minY = l.getBlockY() - radius;
        int maxY = l.getBlockY() + radius;
        int minZ = l.getBlockZ() - radius;
        int maxZ = l.getBlockZ() + radius;

        for(int x = minX; x <= maxX; ++x) {
            for(int y = minY; y <= maxY; ++y) {
                for(int z = minZ; z <= maxZ; ++z) {
                    Location blockLocation = new Location(world, (double)x, (double)y, (double)z);
                    if (isWithinSphere(playerLocation, blockLocation, radius)) {
                        Block block = world.getBlockAt(blockLocation);
                        if (parsedAir) {
                            if (block.getType() != Material.AIR) {
                                blocks.add(block);
                            }
                        } else {
                            blocks.add(block);
                        }

                        blockTags.put(block.getLocation(), tag);
                    }
                }
            }
        }

        return blocks;
    }

    public static List<Block> getAccurateBlocksAroundPlayer(Player player, Location l, int radius, Material m) {
        List<Block> blocks = new ArrayList();
        Location playerLocation = l;
        World world = player.getWorld();
        int minX = l.getBlockX() - radius;
        int maxX = l.getBlockX() + radius;
        int minY = l.getBlockY() - radius;
        int maxY = l.getBlockY() + radius;
        int minZ = l.getBlockZ() - radius;
        int maxZ = l.getBlockZ() + radius;

        for(int x = minX; x <= maxX; ++x) {
            for(int y = minY; y <= maxY; ++y) {
                for(int z = minZ; z <= maxZ; ++z) {
                    Location blockLocation = new Location(world, (double)x, (double)y, (double)z);
                    if (isWithinSphere(playerLocation, blockLocation, radius)) {
                        Block block = world.getBlockAt(blockLocation);
                        if (block.getType() == m) {
                            blocks.add(block);
                        }
                    }
                }
            }
        }

        return blocks;
    }

    public static void createDemiSphere(Location center, int radius, Material material, Byte data, String tag) {
        World world = center.getWorld();
        Material groundMaterial = material;
        int groundY = center.getBlockY() - radius;

        for(int x = -radius; x <= radius; ++x) {
            for(int z = -radius; z <= radius; ++z) {
                Location groundLoc = new Location(world, center.getX() + (double)x, (double)groundY, center.getZ() + (double)z);
                Block groundBlock = world.getBlockAt(groundLoc);
                groundBlock.setType(groundMaterial);
            }
        }

        for(int y = 0; y <= radius; ++y) {
            for(int x = -radius; x <= radius; ++x) {
                for(int z = -radius; z <= radius; ++z) {
                    if (x * x + z * z + y * y <= radius * radius) {
                        Location currentLoc = center.clone().add((double)x, (double)(y - 1), (double)z);
                        Block block = world.getBlockAt(currentLoc);
                        if (y > 0) {
                            block.setType(material);
                            if (data != null) {
                                block.setData(data);
                            }

                            blockTags.put(block.getLocation(), tag);
                        }
                    }
                }
            }
        }

    }

    public static void createRoundPlatform(Location center, int radius, Material material, Byte data, String tag) {
        World world = center.getWorld();

        for(int x = -radius; x <= radius; ++x) {
            for(int z = -radius; z <= radius; ++z) {
                if (Math.sqrt((double)(x * x + z * z)) <= (double)radius) {
                    int blockX = center.getBlockX() + x;
                    int blockZ = center.getBlockZ() + z;
                    int blockY = center.getBlockY() - 1;
                    Block block = world.getBlockAt(blockX, blockY, blockZ);
                    block.setType(material);
                    if (data != null) {
                        block.setData(data);
                    }

                    blockTags.put(block.getLocation(), tag);
                }
            }
        }

    }

    private static boolean isWithinSphere(Location center, Location blockLocation, int radius) {
        int dx = blockLocation.getBlockX() - center.getBlockX();
        int dy = blockLocation.getBlockY() - center.getBlockY();
        int dz = blockLocation.getBlockZ() - center.getBlockZ();
        return dx * dx + dy * dy + dz * dz <= radius * radius;
    }

    public static void transformBlocks(List<Block> blocks, Material m, String tag) {
        for(Block block : blocks) {
            block.setType(m);
            blockTags.put(block.getLocation(), tag);
        }

    }

    public static void transformBlocksWithData(List<Block> blocks, Material m, byte d, String tag) {
        for(Block block : blocks) {
            block.setType(m);
            block.setData(d);
            if (!tag.equals("")) {
                blockTags.put(block.getLocation(), tag);
            }
        }

    }

    public static void replaceBlockBelowPlayer(final Player player, final Material material, final byte data, final String tag, final int radius) {
        (new BukkitRunnable() {
            int counter = 0;

            public void run() {
                ++this.counter;
                Location playerLocation = player.getLocation();
                int y = playerLocation.getBlockY() - this.counter;

                for(int x = -radius; x <= radius; ++x) {
                    for(int z = -radius; z <= radius; ++z) {
                        Location blockLocation = new Location(playerLocation.getWorld(), (double)(playerLocation.getBlockX() + x), (double)y, (double)(playerLocation.getBlockZ() + z));
                        Block block = blockLocation.getBlock();
                        if (block.getType() != Material.AIR) {
                            block.setType(material);
                            block.setData(data);
                            BlockUtils.blockTags.put(block.getLocation(), tag);
                        }
                    }
                }

                if (this.counter >= radius) {
                    this.cancel();
                }

            }
        }).runTaskTimer(UHCAPI.getInstance(), 0L, 1L);
    }
}
