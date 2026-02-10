package fr.doron.dcuhc.roles.BatMan;

import fr.doron.uhc.api.Power;
import fr.doron.uhc.api.Role;
import fr.doron.uhc.plugin.UHCAPI;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.metadata.FixedMetadataValue;

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