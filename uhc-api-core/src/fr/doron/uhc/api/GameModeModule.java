package fr.doron.uhc.api;

import org.bukkit.plugin.PluginDescriptionFile;

import java.util.List;

public interface GameModeModule {
    String getId();
    String getDisplayName();
    PluginDescriptionFile getDescription();

    void onGameStart();
    void onGameEnd();
    List<Role> getRoles();
}