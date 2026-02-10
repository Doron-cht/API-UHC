package fr.doron.dcuhc.roles.Pouvoir;

import fr.doron.dcuhc.Flash.FlashRole;
import fr.doron.uhc.api.Role;
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
