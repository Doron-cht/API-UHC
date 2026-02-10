package fr.doron.dcuhc.Flash;

import fr.doron.dcuhc.roles.Pouvoir.Power;
import fr.doron.dcuhc.roles.RoleType;
import fr.doron.dcuhc.roles.commands.RoleCommandHandler;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.KnockbackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static fr.doron.dcuhc.roles.Pouvoir.PowerBase.updatePowerActionBar;

public class FlashRole implements Role, RoleCommandHandler {

    private final Map<String, Power> powers = new HashMap<>();
    private Map<UUID, Deque<Location>> history = new HashMap<>();


    public Deque<Location> getHistory(Player player) {
        return history.get(player.getUniqueId());
    }

    public FlashRole(){
        powers.put("aerokinesis", new PowerAerokinesis());
        powers.put("dimensional", new PowerDimensional());
        powers.put("time", new PowerTime(this));
    }


    @Override
    public Map<String, Power> getPowers() {
        return powers;
    }

    @Override
    public String getName() {
        return "Flash";
    }

    @Override
    public String getAnnonce() {
        return "§bVous etes Flash vous gagnez avec les justiciers";
    }

    @Override
    public String getDescription() {
        return "";
    }
    @Override
    public RoleType getType() {
        return RoleType.FLASH;
    }

    @Override
    public void onAssign(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0, false, false)); // +10%
        player.getInventory().addItem(FlashPower());
        PowerTime time = (PowerTime) powers.get("time");
        player.sendMessage(getAnnonce());

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Role role = UHCAPI.getInstance().getGameManager().getRole(player);
                    if (role != null) {
                        updatePowerActionBar(player, role);
                    }
                }
            }
        }.runTaskTimer(UHCAPI.getInstance(), 0L, 20L);


    }
    public void usePower(Player p, String powerName) {
        Power power = powers.get(powerName.toLowerCase());
        if (power == null) {
            p.sendMessage("§cPouvoir inconnu !");
            return;
        }
        power.use(p, this); // passe le rôle si besoin
    }



    private ItemStack FlashPower(){
        ItemStack flashpower = new ItemStack(Material.NETHER_STAR);
        ItemMeta metaflash = flashpower.getItemMeta();
        metaflash.setDisplayName("§eFlash Power");
        metaflash.addEnchant(Enchantment.ARROW_FIRE, 1, false);
        metaflash.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        flashpower.setItemMeta(metaflash);
        return flashpower;
    }

    public void sharingForce(Player p, Player target) {

        p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

        target.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2 * 60 * 20 + 10 * 20, 0));
        target.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2 * 60 * 20 + 10 * 20, 0));


    }
    @Override
    public void onGameStart(Player player) {


    }

    @Override
    public boolean handleCommand(Player player, String[] args) {

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("sharing")) {

            if (args.length != 2) {
                player.sendMessage("§c/dc sharing <pseudo>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage("§cJoueur introuvable.");
                return true;
            }

            sharingForce(player, target);
            return true;
        }

        return false;
    }
}
