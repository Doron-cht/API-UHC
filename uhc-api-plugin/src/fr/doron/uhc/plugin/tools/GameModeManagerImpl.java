package fr.doron.uhc.plugin.tools;

import fr.doron.uhc.api.GameModeManager;
import fr.doron.uhc.api.GameModeModule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameModeManagerImpl implements GameModeManager {

    private final Map<String, GameModeModule> modules = new HashMap<>();

    @Override
    public void register(GameModeModule module) {
        modules.put(module.getId().toLowerCase(), module);
    }
    @Override
    public GameModeModule get(String id) {
        return modules.get(id.toLowerCase());
    }

    @Override
    public Collection<GameModeModule> getAll() {
        return modules.values();
    }
}