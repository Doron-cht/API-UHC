package fr.doron.dcuhc.roles;

import fr.doron.dcuhc.BatMan.BatmanRole;
import fr.doron.dcuhc.Flash.FlashRole;

public enum DCRoles {

    BATMAN("Batman", BatmanRole.class),
    JOKER("Joker", JokerRole.class),
    FLASH("Flash",FlashRole.class);

    private String displayName;
    private Class<? extends fr.doron.uhc.api.Role> clazz;

    DCRoles(String displayName, Class<? extends fr.doron.uhc.api.Role> clazz) {
        this.displayName = displayName;
        this.clazz = clazz;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Class<? extends fr.doron.uhc.api.Role> getRoleClass() {
        return clazz;
    }
}