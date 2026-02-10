package fr.doron.uhc.api;

import org.bukkit.entity.Player;

public interface Power {

    String getName();

    boolean canUse(Player player);

    void use(Player player, Role role);

    default int getMaxUses() {
        return -1; // illimité par défaut
    }

    long getCooldown(Player player);
}
