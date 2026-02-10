package fr.doron.dcuhc.ressources;

import net.md_5.bungee.api.ChatColor;

public enum EffectType {
    SPEED(ChatColor.AQUA, "Vitesse"),
    FORCE(ChatColor.RED, "Force"),
    REGEN(ChatColor.GREEN, "Régénération"),
    INVISIBILITY(ChatColor.GRAY, "Invisibilité"),
    RESISTANCE(ChatColor.DARK_GRAY, "Résistance"),
    JUMP(ChatColor.GOLD, "Saut"),
    ABSORPTION(ChatColor.LIGHT_PURPLE, "Absorption"),
    NOFALL(ChatColor.LIGHT_PURPLE, "Nofall");

    private final ChatColor color;
    private final String displayName;

    EffectType(ChatColor color, String displayName) {
        this.color = color;
        this.displayName = displayName;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }
}
