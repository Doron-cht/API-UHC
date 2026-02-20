package fr.doron.dcuhc.roles.HERO.Superman;

import fr.doron.dcuhc.Camp.Camp;
import fr.doron.dcuhc.roles.Tools.ressources.EffectType;
import fr.doron.dcuhc.roles.DCRoles;
import fr.doron.dcuhc.roles.Tools.RoleDescriptionBuilder;
import fr.doron.dcuhc.roles.Tools.commands.RoleCommandHandler;
import fr.doron.uhc.api.Power;
import fr.doron.uhc.api.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SupermanRole implements Role, RoleCommandHandler {

    private final Map<String, Power> powers = new HashMap<>();




    @Override
    public String getName() {
        return DCRoles.SUPERMAN.getCamp().getColor() +  DCRoles.SUPERMAN.getDisplayName();
    }

    @Override
    public void sendDescription(Player player) {
        DCRoles roleEnum = DCRoles.SUPERMAN;
        Camp supermanCamp = roleEnum.getCamp();


        new RoleDescriptionBuilder("Superman", supermanCamp)
                .addEffect(EffectType.RESISTANCE, "I", "Effet permanent")
                .addPower("Journalisme",
                        "/dc annonce [message]\nVous permez de publier une annonce visible par tous\n1x/épisode")
                .addPower("Vol",
                        "Vous permez de voler pendant 15 secondes\n1x/20min\n2x/partie")
                .addPower("Vision Thermique",
                        "Vous permez de mettre en feu le joueur visez avec votre item en main.\nLe joueur sera mis en feu avec l'impossibilité de l'éteindre pendant 10 secondes.\nAttention ! des particules rouges sont visibles de vos yeux jusqu'au joueurs\n1x/10min\n2x/partie")
                .addInfo("Êtant membre de la Justice League vous obtenez le pseudo de l'un de vos coéquipier au hasard")
                .addInfo("Membre de la Justice League : ")
                .send(player);
    }

    @Override
    public Camp getCamp() {
        return Camp.HERO;
    }

    @Override
    public Map<String, Power> getPowers() {
        return powers;
    }


    @Override
    public void onAssign(Player player) {
        sendDescription(player);
        player.getInventory().addItem(Vol());
        // TODO Faire le Listener pour le vol
        // TODO Faire l'item pour le rayon laser
        // TODO Apprendre a générer des particules.......



    }

    public ItemStack Vol() {
        ItemStack volItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = volItem.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Vol");
        volItem.setItemMeta(meta);
        return volItem;
    }


    public void Journalisme(String message) {
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY+"["+ ChatColor.YELLOW+ "DailyBuggle "+ChatColor.DARK_GRAY+"]"+ChatColor.YELLOW+": "+message);

    }


    @Override
    public void onGameStart(Player player) {

    }
    @Override
    public boolean handleCommand(Player player, String[] args) {

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("annonce")) {

            if (args.length != 2) {
                player.sendMessage("§c/dc annonce <message>");
                return true;
            }

            String message = Arrays.toString(args);
            if (message.equals(" ")) {
                player.sendMessage("§cVous devez annoncer quelque chose.");
                return true;
            }

            Journalisme(message);
            return true;
        }

        return false;
    }
}
