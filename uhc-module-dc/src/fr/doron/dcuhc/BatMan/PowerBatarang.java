package fr.doron.dcuhc.BatMan;

import fr.doron.dcuhc.Flash.FlashRole;
import fr.doron.dcuhc.roles.Pouvoir.Power;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import fr.doron.uhc.plugin.tools.KnockbackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.*;

public class PowerBatarang implements Power {


    @Override
    public String getName() {
        return "Aerokinesis";
    }

    @Override
    public boolean canUse(Player player) {
        return true;
    }



    @Override
    public long getCooldown(Player player) {
        return 0;
    }



    @Override
    public void use(Player player, Role role) {

        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setMetadata("BatmanBatarang", new FixedMetadataValue(UHCAPI.getInstance(), true));


        player.sendMessage("§aBatarang utilisé !");
    }
}