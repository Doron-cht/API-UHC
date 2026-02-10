package fr.doron.dcuhc.roles.BatMan;

import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class BatManListener implements Listener {

    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent event) {

        // Vérifie que la victime est un joueur
        if (!(event.getEntity() instanceof Player)) return;

        // Vérifie que le projectile est une snowball
        if (!(event.getDamager() instanceof Snowball)) return;

        Snowball snowball = (Snowball) event.getDamager();

        // Vérifie que le lanceur est un joueur
        if (!(snowball.getShooter() instanceof Player)) return;

        Player shooter = (Player) snowball.getShooter();
        Player target = (Player) event.getEntity();

        // Vérifie le tag custom
        if (snowball.getCustomName() == null) return;
        if (!snowball.getCustomName().equals("§eBatarang")) return;
        if (!snowball.hasMetadata("batarang")) return;

        // ===== ACTION =====
        shooter.sendMessage("§aTu as touché " + target.getName());
        target.sendMessage("§cTouché par " + shooter.getName());

        // Facultatif : annule les dégâts
        event.setCancelled(true);
    }

   @EventHandler
    public void onThrow(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null) return;
        if (item.getType() != Material.SNOW_BALL) return;
        if (!item.hasItemMeta()) return;
        if (!"§eBatarang".equals(item.getItemMeta().getDisplayName())) return;

        Snowball snowball = player.launchProjectile(Snowball.class);

        // 🔑 TAG IMPORTANT
        snowball.setCustomName("§eBatarang");
        snowball.setCustomNameVisible(false);
        snowball.setMetadata("batarang", new FixedMetadataValue(UHCAPI.getInstance(), true));



   }
}


