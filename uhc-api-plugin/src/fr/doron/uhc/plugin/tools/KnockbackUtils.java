package fr.doron.uhc.plugin.tools;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KnockbackUtils {

    public static void repulse(Player source, double radius, double power, double yBoost) {
        Location center = source.getLocation();
        source.getWorld().spigot().playEffect(
                source.getLocation(),
                Effect.EXPLOSION_LARGE,
                0, 0,
                0, 0, 0,
                0, 1, 32
        );

        for (Player target : center.getWorld().getPlayers()) {
            if (target.equals(source)) continue;

            if (target.getLocation().distanceSquared(center) <= radius * radius) {

                Vector direction = target.getLocation().toVector()
                        .subtract(center.toVector())
                        .normalize();

                Vector velocity = direction.multiply(power);
                velocity.setY(yBoost);

                target.setVelocity(velocity);
            }
        }
    }
}