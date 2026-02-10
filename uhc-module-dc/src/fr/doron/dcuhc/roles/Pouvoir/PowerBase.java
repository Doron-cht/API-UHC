package fr.doron.dcuhc.roles.Pouvoir;

import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.tools.Title;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public abstract class PowerBase implements Power {

    private final int maxUses;
    private final long cooldown;
    private int uses = 0;
    private long lastUse = 0;

    protected PowerBase(int maxUses, long cooldownSeconds) {
        this.maxUses = maxUses;
        this.cooldown = cooldownSeconds * 1000;
    }

    public boolean canUse(Player p) {

        if (uses >= maxUses) {
            p.sendMessage("§cPouvoir épuisé.");
            return false;
        }

        long now = System.currentTimeMillis();
        long remaining = (lastUse + cooldown - now) / 1000;

        if (remaining > 0) {
            p.sendMessage("§cCooldown: " + remaining + "s");
            return false;
        }

        return true;
    }

    protected void consume() {
        uses++;
        lastUse = System.currentTimeMillis();
    }
    public static void updatePowerActionBar(Player player, Role role) {
        StringBuilder sb = new StringBuilder("§ePouvoirs: ");

        for (Power power : role.getPowers().values()) {
            if (power.canUse(player)) {
                sb.append("§a").append(power.getName()).append(" §7| ");
            } else {
                long cd = power.getCooldown(player) / 1000;
                sb.append("§c").append(power.getName())
                        .append("(").append(cd).append("s) §7| ");
            }
        }

        String message = sb.toString();
        if (message.endsWith(" §7| ")) {
            message = message.substring(0, message.length() - 4);
        }

        Title.sendActionBar(player, message);
    }


}
