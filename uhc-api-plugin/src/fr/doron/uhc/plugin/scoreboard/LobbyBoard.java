package fr.doron.uhc.plugin.scoreboard;

import fr.doron.uhc.api.GameModeModule;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Arrays;
import java.util.List;

public class LobbyBoard extends AbstractBoard {

    public LobbyBoard(Player p) {
        super(p);
    }

    @Override
    protected String getTitle() {
        GameModeModule module = UHCAPI.getInstance().getGameManager().getCurrentModule();
        return (module == null ? "§eUHC" : "§6" + module.getDisplayName());
    }

    @Override
    protected List<String> getLines() {

        GameManager gm = UHCAPI.getInstance().getGameManager();

        return Arrays.asList(
                "§7 ",
                "§f» §eInformations",
                "  §fHost: §b" + (gm.getHost() == null ? "?" : gm.getHost().getName()),
                "  §fJoueurs: §b" + UHCAPI.getInstance().getPlayersForTheGame().size() + "§f/§b" + gm.getMaxSlot(),
                "  §fMode: §b" +(UHCAPI.getInstance().getGameManager().getCurrentModule() != null ? UHCAPI.getInstance().getGameManager().getCurrentModule().getDisplayName() : "..."),
                " §8",
                "§bplay.doron.fr"
        );
    }
}
