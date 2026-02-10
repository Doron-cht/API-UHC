package fr.doron.dcuhc.BatMan;

import fr.doron.dcuhc.roles.Pouvoir.Power;
import fr.doron.dcuhc.roles.RoleType;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.tools.HeartUtils;
import fr.doron.uhc.plugin.tools.PotionUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class BatmanRole implements Role {

    @Override
    public String getName() {
        return "Batman";
    }

    @Override
    public String getAnnonce() {
        return "Vous devez gagnez avec les justicier";
    }

    @Override
    public String getDescription() {
        return "Un justicier masqué. Commence avec une vitesse accrue.";
    }

    @Override
    public void onAssign(Player player) {
        onGameStart(player);
        PotionUtils.effetGiveNofall(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*60, 1));
        HeartUtils.setHealth(player, 22);
        player.getInventory().addItem(Batarang());




    }

    public ItemStack Batarang(){
        ItemStack batarang = new ItemStack(Material.SNOW_BALL);
        ItemMeta meta = batarang.getItemMeta();
        meta.setDisplayName("§eBatarang");
        batarang.setAmount(3);
        batarang.setItemMeta(meta);
        return batarang;
    }
    @Override
    public RoleType getType() {
        return RoleType.BATMAN;
    }
    private final Map<String, Power> powers = new HashMap<>();

    @Override
    public Map<String, Power> getPowers() {
        return powers;
    }

    @Override
    public void onGameStart(Player player) {
        player.sendMessage(getAnnonce());
        player.sendMessage(getDescription());
        player.sendMessage("T  BATMAN MON FREROT");


    }

}