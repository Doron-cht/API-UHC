package fr.doron.uhc.api;

import fr.doron.dcuhc.roles.Pouvoir.Power;
import fr.doron.dcuhc.roles.RoleType;
import org.bukkit.entity.Player;

import java.util.Map;

public interface Role {

    /**
     * Nom du rôle
     */
    String getName();
    String getAnnonce();
    /**
     * Description du rôle
     */
    String getDescription();
    RoleType getType();
    Map<String, Power> getPowers();

    /**
     * Méthode appelée quand le joueur reçoit ce rôle
     */
    void onAssign(Player player);

    /**
     * Méthode appelée au début de la partie si besoin
     */
    void onGameStart(Player player);
}