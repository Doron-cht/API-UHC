package fr.doron.uhc.plugin.scoreboard;

import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.game.GameManager;
import fr.doron.uhc.plugin.tools.DirectionUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class GameBoard extends AbstractBoard {

    public GameBoard(Player p) {
        super(p);
    }

    @Override
    protected String getTitle() {
        return(UHCAPI.getInstance().getGameManager().getCurrentModule() == null ? "§cUHC" : "§b" + UHCAPI.getInstance().getGameManager().getCurrentModule().getDisplayName());
    }

    private String formatTime(int seconds) {
        if(seconds < 0) seconds = 0;
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }

    @Override
    protected List<String> getLines() {
        Location target = new Location(player.getWorld(), 0, 80, 0);
        String arrow = DirectionUtil.getArrow(player, target);

        GameManager gm = UHCAPI.getInstance().getGameManager();
        Role role = gm.getRole(player);

        return Arrays.asList(
                " §1",
                "§fTemps: §b" + formatTime(gm.getGameTime()),
                "§fPvP: §b" + (gm.getPvPTimer() - gm.getElapsedSeconds() == 0 ? "§aActivé" : "§f" + formatTime(gm.getPvPTimer() - gm.getElapsedSeconds())),
                " §2",
                "§fRôle: §b" + (role == null ? "?" : role.getName()),
                " §3",
                "§fCentre: §b§l" + arrow ,
                " §4",
                "§bplay.Doron.fun"
        );
    }
}