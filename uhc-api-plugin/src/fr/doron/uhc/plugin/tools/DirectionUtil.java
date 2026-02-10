package fr.doron.uhc.plugin.tools;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DirectionUtil {

    public static String getArrow(Player p, Location target) {

        double dx = target.getX() - p.getLocation().getX();
        double dz = target.getZ() - p.getLocation().getZ();

        double angleToTarget = Math.toDegrees(Math.atan2(-dx, dz));
        double playerYaw = p.getLocation().getYaw();

        double diff = angleToTarget - playerYaw;

        while (diff < 0) diff += 360;
        while (diff >= 360) diff -= 360;

        if (diff < 22.5) return "⬆";
        if (diff < 67.5) return "↗";
        if (diff < 112.5) return "→";
        if (diff < 157.5) return "↘";
        if (diff < 202.5) return "↓";
        if (diff < 247.5) return "↙";
        if (diff < 292.5) return "←";
        if (diff < 337.5) return "↖";

        return "⬆";
    }
}
