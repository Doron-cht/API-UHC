package fr.doron.dcuhc.roles.Flash;

import fr.doron.uhc.api.Power;
import fr.doron.uhc.api.Role;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PowerDimensional implements Power {

    private final Set<UUID> used = new HashSet<>();

    @Override
    public String getName() {
        return "Dimensional Travel";
    }

    @Override
    public boolean canUse(Player player) {
        return !used.contains(player.getUniqueId());
    }

    @Override
    public long getCooldown(Player player) {
        return canUse(player) ? 0 : Long.MAX_VALUE; // 1x par partie
    }

    @Override
    public void use(Player player, Role role) {
        if (!canUse(player)) {
            player.sendMessage("§cDimensional Travel déjà utilisé !");
            return;
        }

        Location base = player.getLocation();
        double angle = Math.random() * 2 * Math.PI;
        double dist = Math.random() * 150;

        double x = base.getX() + Math.cos(angle) * dist;
        double z = base.getZ() + Math.sin(angle) * dist;
        Location tp = new Location(base.getWorld(), x, base.getWorld().getHighestBlockYAt((int)x, (int)z)+1, z);

        player.teleport(tp);
        player.setMaxHealth(player.getMaxHealth() - 2); // -1❤️ permanent
        player.sendMessage("§aDimensional Travel utilisé !");

        used.add(player.getUniqueId());
    }
}
