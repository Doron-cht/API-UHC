package fr.doron.uhc.api;

import org.bukkit.entity.Player;

import java.util.Map;

public interface Role {

    String getName();
    void sendDescription(Player player);
    Map<String, Power> getPowers();
    void onAssign(Player player);
    void onGameStart(Player player);
}