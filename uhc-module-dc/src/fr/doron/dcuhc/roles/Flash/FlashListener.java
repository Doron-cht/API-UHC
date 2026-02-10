package fr.doron.dcuhc.roles.Flash;

import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlashListener implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        if (!(e.getItem().getType() == Material.GOLDEN_APPLE)) return;

        Player p = e.getPlayer();

        if (!(UHCAPI.getInstance().getGameManager().getRole(p) instanceof FlashRole)) return;

        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2)); // 3 sec, lvl 3
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack it = p.getItemInHand();

        if (it == null || it.getType() != Material.NETHER_STAR) return;

        Role r = UHCAPI.getInstance().getGameManager().getRole(p);
        if (!(r instanceof FlashRole)) return;

        e.setCancelled(true);
        UHCAPI.getInstance().getGUIManager().open(p, FlashGUI.class);
    }

}
