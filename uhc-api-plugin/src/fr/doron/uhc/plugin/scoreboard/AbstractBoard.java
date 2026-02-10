package fr.doron.uhc.plugin.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public abstract class AbstractBoard {

    protected final Player player;
    protected final Scoreboard scoreboard;
    protected final Objective obj;

    public AbstractBoard(Player p) {
        this.player = p;

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        scoreboard = sm.getNewScoreboard();

        obj = scoreboard.registerNewObjective("uhc", "dummy");
        obj.setDisplayName(getTitle());
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        player.setScoreboard(scoreboard);
        update();
    }

    protected abstract String getTitle();
    protected abstract List<String> getLines();

    public void update() {
        scoreboard.getEntries().forEach(scoreboard::resetScores);

        obj.setDisplayName(getTitle()); // <-- IMPORTANT

        int i = getLines().size();
        for (String line : getLines()) {
            obj.getScore(line).setScore(i--);
        }
    }
    public void destroy() {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }
}
