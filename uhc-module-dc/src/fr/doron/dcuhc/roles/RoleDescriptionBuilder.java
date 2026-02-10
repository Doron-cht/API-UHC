package fr.doron.dcuhc.roles;

import fr.doron.dcuhc.ressources.EffectType;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class RoleDescriptionBuilder {

    private final String name;
    private final Camp camp;
    private final List<Effect> effects = new ArrayList<>();
    private final List<Passive> passives = new ArrayList<>();
    private final Map<String, PowerInfo> powers = new LinkedHashMap<>();
    private final List<String> informations = new ArrayList<>();

    public RoleDescriptionBuilder(String name, Camp camp) {
        this.name = name;
        this.camp = camp;
    }

    public RoleDescriptionBuilder addEffect(EffectType type, String value, String description) {
        effects.add(new Effect(type, value, description));
        return this;
    }

    public RoleDescriptionBuilder addPassive(String name, String description) {
        passives.add(new Passive(name, description));
        return this;
    }

    public RoleDescriptionBuilder addPower(String name, String description) {
        String[] lines = description.split("\n", 2);
        String firstLine = lines[0] + " [=]";
        String hover = lines.length > 1 ? lines[1] : "";
        powers.put(name, new PowerInfo(firstLine, hover));
        return this;
    }

    public RoleDescriptionBuilder addInfo(String info) {
        informations.add(info);
        return this;
    }

    public void send(Player player) {
        player.sendMessage(ChatColor.GRAY + "===============================================");
        player.sendMessage(ChatColor.GOLD + "Role: " + name + " | Camp: " + camp.name());

        if (!effects.isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "==================== EFFETS ====================");
            for (Effect e : effects) {
                player.sendMessage(ChatColor.AQUA + "[" + e.type.name() + "] " + e.value + " : " + e.description);
            }
        }

        if (!passives.isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "==================== PASSIFS ====================");
            for (Passive p : passives) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + p.name + ": " + p.description);
            }
        }

        if (!powers.isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "==================== ACTIFS ====================");
            for (PowerInfo p : powers.values()) {
                TextComponent tc = new TextComponent(p.firstLine);
                if (!p.hoverText.isEmpty()) {
                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new ComponentBuilder(p.hoverText).color(ChatColor.YELLOW).create()));
                }
                player.spigot().sendMessage(tc);
            }
        }

        if (!informations.isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "=================== INFORMATION =================");
            for (String info : informations) {
                player.sendMessage(ChatColor.GREEN + info);
            }
        }

        player.sendMessage(ChatColor.GRAY + "===============================================");
    }

    // Classes internes
    private static class Effect {
        private final EffectType type;
        private final String value;
        private final String description;

        public Effect(EffectType type, String value, String description) {
            this.type = type;
            this.value = value;
            this.description = description;
        }
    }

    private static class Passive {
        private final String name;
        private final String description;

        public Passive(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    private static class PowerInfo {
        private final String firstLine;
        private final String hoverText;

        public PowerInfo(String firstLine, String hoverText) {
            this.firstLine = firstLine;
            this.hoverText = hoverText;
        }
    }
}