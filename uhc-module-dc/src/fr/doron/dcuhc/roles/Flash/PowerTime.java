package fr.doron.dcuhc.roles.Flash;

import fr.doron.uhc.api.Power;
import fr.doron.uhc.api.Role;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PowerTime implements Power {
    private final FlashRole flash;
    private final Map<UUID, Boolean> used = new HashMap<>();

    public PowerTime(FlashRole flash) {
        this.flash = flash;
    }

    @Override
    public void use(Player player, Role role) {
        if (!canUse(player)) {
            player.sendMessage("§cTime Travel déjà utilisé !");
            return;
        }

        Deque<Location> h = flash.getHistory(player);

        if (h == null || h.isEmpty()) {
            player.sendMessage("§cAucune position enregistrée !");
            return;
        }
        player.teleport(h.getFirst());

        player.sendMessage("§aTime Travel utilisé !");
        used.put(player.getUniqueId(), true);
    }



    @Override
    public String getName() {
        return "Time Travel";
    }

    @Override
    public boolean canUse(Player player) {
        return !used.getOrDefault(player.getUniqueId(), false);
    }

    @Override
    public long getCooldown(Player player) {
        return canUse(player) ? 0 : Long.MAX_VALUE; // 1x par épisode
    }


}