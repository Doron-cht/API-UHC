package fr.doron.uhc.plugin.world;

import fr.doron.uhc.api.GameModeModule;
import fr.doron.uhc.plugin.game.GameManager;
import org.bukkit.Material;

import fr.doron.uhc.plugin.world.WorldUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;



public class LobbyManager {
    private final GameManager gameManager;

    public LobbyManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void createUHCWorld() {
        GameModeModule module = gameManager.getCurrentModule();
        OrePopulator orePopulator = new OrePopulator();
        orePopulator.addRule(new OrePopulator.Rule(Material.COAL_ORE, 7, 0, 128, 18));
        orePopulator.addRule(new OrePopulator.Rule(Material.IRON_ORE, 5, 0, 64, 10));
        orePopulator.addRule(new OrePopulator.Rule(Material.GOLD_ORE, 4, 0, 32, 10));
        orePopulator.addRule(new OrePopulator.Rule(Material.REDSTONE_ORE, 5, 0, 16, 10));
        orePopulator.addRule(new OrePopulator.Rule(Material.DIAMOND_ORE, 3, 0, 16, 10));
        orePopulator.addRule(new OrePopulator.Rule(Material.LAPIS_ORE, 5, 0, 32, 10));
        orePopulator.addRule(new OrePopulator.Rule(Material.EMERALD_ORE, 4, 0, 32, 10));
        WorldUtils.init();
        World world = (new WorldCreator("uhc")).createWorld();

        world.getPopulators().add(orePopulator);
        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "false");
        //world.getWorldBorder().setCenter(this.gameManager.getCenter());
        }

    public static void getCarre(World world, Location location, Material material) {
        int radius = 10;
        int yOffset = -1;

        for(int x = -radius; x <= radius; ++x) {
            for(int z = -radius; z <= radius; ++z) {
                Location blockLocation = location.clone().add((double)x, (double)yOffset, (double)z);
                Block block = world.getBlockAt(blockLocation);
                if (block.getType() != Material.AIR && block.getType() != Material.WATER && block.getType() != Material.LAVA) {
                    block.setType(material);
                }
            }
        }

    }

    public GameManager getGameManager() {
        return this.gameManager;
    }
}
