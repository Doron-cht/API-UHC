package fr.doron.uhc.api;

import java.util.Collection;

public interface GameModeManager {

    void register(GameModeModule module);
    GameModeModule get(String name);
    Collection<GameModeModule> getAll();

}
