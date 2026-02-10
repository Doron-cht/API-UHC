package fr.doron.dcuhc.roles;

import fr.doron.dcuhc.roles.Pouvoir.Power;
import fr.doron.uhc.api.Role;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class JokerRole implements Role {

    @Override
    public String getName() {
        return "Joker";
    }

    @Override
    public String getAnnonce() {
        return "Vous devez gagnez avec les villains !";    }

    @Override
    public String getDescription() {
        return "Le prince du crime. Commence avec un effet aléatoire.";
    }

    @Override
    public void onAssign(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*10, 0));

    }
    @Override
    public RoleType getType() {
        return RoleType.JOKER;
    }
    private final Map<String, Power> powers = new HashMap<>();

    @Override
    public Map<String, Power> getPowers() {
        return powers;
    }

    @Override
    public void onGameStart(Player player) {
        player.sendMessage("T  LE JOKER MON FREROT");
        player.sendMessage(getAnnonce());
        player.sendMessage(getDescription());

    }

}