package fr.doron.dcuhc.roles.Flash;

import fr.doron.uhc.api.Power;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.tools.KnockbackUtils;
import org.bukkit.entity.Player;

import java.util.*;

public class PowerAerokinesis implements Power {

    private final int maxUses = 3;
    private final Map<UUID, Long> lastUse = new HashMap<>();

    @Override
    public String getName() {
        return "Aerokinesis";
    }

    @Override
    public boolean canUse(Player player) {
        Long last = lastUse.getOrDefault(player.getUniqueId(), 0L);
        return System.currentTimeMillis() - last >= 15 * 60 * 1000;
    }

    @Override
    public long getCooldown(Player player) {
        Long last = lastUse.getOrDefault(player.getUniqueId(), 0L);
        long remaining = 15 * 60 * 1000 - (System.currentTimeMillis() - last);
        return Math.max(0, remaining);
    }

    @Override
    public void use(Player player, Role role) {
        if (!canUse(player)) {
            player.sendMessage("§cAerokinesis en cooldown !");
            return;
        }

        KnockbackUtils.repulse(player, 15, 1.6, 0.6);

        lastUse.put(player.getUniqueId(), System.currentTimeMillis());
        player.sendMessage("§aAerokinesis utilisé !");
    }
}