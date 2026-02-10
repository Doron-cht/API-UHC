package fr.doron.dcuhc.roles.BatMan;

import fr.doron.dcuhc.ressources.EffectType;
import fr.doron.dcuhc.roles.Camp;
import fr.doron.dcuhc.roles.DCRoles;
import fr.doron.uhc.api.Power;
import fr.doron.dcuhc.roles.RoleDescriptionBuilder;
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
    public void sendDescription(Player player) {
        DCRoles roleEnum = DCRoles.BATMAN;
        Camp batmancamp = roleEnum.getCamp();
        new RoleDescriptionBuilder("Batman", batmancamp)
                .addEffect(EffectType.NOFALL, "II", "Effet permanent")
                .addEffect(EffectType.FORCE, "+10%", "Bonus permanent de dégâts")
                .addEffect(EffectType.REGEN, "III", "3 secondes après une pomme en or")
                .addPassive("Instinct de survie", "Réduit les dégâts subis de 5%")
                .addPower("Batarang x3",
                        "Aveugle vos énnemis pendant 1 min.")
                .addInfo("Ne fonctionne pas dans les zones protégées")
                .addInfo("Les cooldowns ne se cumulent pas")
                .send(player);
    }


    @Override
    public void onAssign(Player player) {
        sendDescription(player);
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
    public DCRoles getType() {
        return DCRoles.BATMAN;
    }

    private final Map<String, Power> powers = new HashMap<>();

    public Map<String, Power> getPowers() {
        return powers;
    }

    @Override
    public void onGameStart(Player player) {

    }

}