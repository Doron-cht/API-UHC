package fr.doron.uhc.plugin.scoreboard;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UHCScoreboardManager {

    private final Map<UUID, AbstractBoard> boards = new HashMap<>();

    public void setLobbyBoard(Player p) {
        boards.put(p.getUniqueId(), new LobbyBoard(p));
    }

    public void setGameBoard(Player p) {
        boards.put(p.getUniqueId(), new GameBoard(p));
    }

    public void remove(Player p) {
        AbstractBoard board = boards.remove(p.getUniqueId());
        if (board != null) board.destroy();
    }

    public void updateAll() {
        for (AbstractBoard board : boards.values()) {
            board.update();
        }
    }
}
