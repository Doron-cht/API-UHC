package fr.doron.dcuhc.roles;

import fr.doron.dcuhc.roles.BatMan.BatmanRole;
import fr.doron.dcuhc.roles.Flash.FlashRole;
import fr.doron.dcuhc.roles.Superman.SupermanRole;

public enum DCRoles {

    // Héros
    BATMAN("Batman", BatmanRole.class, Camp.HERO),
    FLASH("Flash", FlashRole.class, Camp.HERO),
    SUPERMAN("Superman", SupermanRole.class, Camp.HERO),
    CYBORG("Cyborg", CyborgRole.class, Camp.HERO),
    WONDERWOMAN("Wonder Woman", WonderWomanRole.class, Camp.HERO),
    AQUAMAN("Aquaman", AquamanRole.class, Camp.HERO),
    SHAZAM("Shazam", ShazamRole.class, Camp.HERO),
    ARROW("Arrow", ArrowRole.class, Camp.HERO),
    NIGHTWING("Night Wing", NightwingRole.class, Camp.HERO),
    CAITLINSNOW("Caitlin Snow", CaitlinSnowRole.class, Camp.HERO),
    GROGAN("Commissaire Grogan", GroganRole.class, Camp.HERO),
    GORDON("Capitaine Gordon", GordanRole.class, Camp.HERO),
    POLICIER("Inspecteur de Police", PolicierRole.class, Camp.HERO),

    // Méchants
    JOKER("Joker", JokerRole.class, Camp.SUICIDESQUAD),
    HARLEYQUINN("Harley Quinn", HarleyQuinnRole.class, Camp.SUICIDESQUAD),
    DEADSHOT("DeadShot", DeadshotRole.class, Camp.SUICIDESQUAD),
    KINGSHARK("King Shark", KingSharkRole.class, Camp.SUICIDESQUAD),
    BLACKSPIDER("Black Spider", BlackSpiderRole.class, Camp.SUICIDESQUAD),

    // Mafieux
    FALCONE("Carmin Falcone", FalconeRole.class, Camp.MAFIA),
    MARONI("Maroni", MaroniRole.class, Camp.MAFIA),
    PINGOUIN("Le Pingouin", PingouinRole.class, Camp.MAFIA),
    MOONEY("Fish Mooney", MooneyRole.class, Camp.MAFIA),
    BUTCH("Butch", ButchRole.class, Camp.MAFIA),
    PIG("Professeur Pig", PigRole.class, Camp.MAFIA),
    CRANE("Jonathan Crane", CranRole.class, Camp.MAFIA),
    MAFIEUXFALCONE("Mafieux (Falcone)", MafieuxFalconeRole.class, Camp.MAFIA),
    MAFIEUXMARONI("Mafieux (Maroni)", MafieuxMaroniRole.class, Camp.MAFIA),

    // Anti-héros / neutres
    POISONIVY("Poison Ivy", PoisonIvyRole.class, Camp.VILLAIN),
    LEXLUTHOR("Lex Luthor", LexLuthorRole.class, Camp.VILLAIN),
    REDHOOD("Red Hood", RedHoodRole.class, Camp.VILLAIN),
    DEATHSTROKE("Deathstroke", DeathStrokeRole.class, Camp.VILLAIN),
    BANE("Bane", BaneRole.class, Camp.VILLAIN),
    RAASALGUL("Ra's Al Gul", RaasalgulRole.class, Camp.VILLAIN),
    SPHINX("Le Sphinx", ShinxRole.class, Camp.VILLAIN),

    // Autres
    DARKSEID("DarkSeid", DarkseidRole.class, Camp.VILLAIN),
    BRITGHBURN("BrightBurn", BrightBurnRole.class, Camp.VILLAIN);

    private final String displayName;
    private final Class<? extends fr.doron.uhc.api.Role> clazz;
    private final Camp camp;

    DCRoles(String displayName, Class<? extends fr.doron.uhc.api.Role> clazz, Camp camp) {
        this.displayName = displayName;
        this.clazz = clazz;
        this.camp = camp;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Class<? extends fr.doron.uhc.api.Role> getRoleClass() {
        return clazz;
    }

    public Camp getCamp() {
        return camp;
    }
}