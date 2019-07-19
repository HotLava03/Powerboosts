package io.github.hotlava03.powerboosts.util;

import com.massivecraft.factions.entity.Faction;
import io.github.hotlava03.powerboosts.Powerboosts;

import java.io.IOException;

public class Util {

    public static boolean hasPowerboosts(Faction faction) {
        final String ID = faction.getId();
        return Powerboosts.powerboosts.get("powerboosts."
                + ID) != null;
    }

    public static int getPowerboosts(Faction faction) {
        final String ID = faction.getId();
        try {
            return Powerboosts.powerboosts.getInt("powerboosts."
                    + ID);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public static boolean savePowerboost(Faction faction) {
        final String ID = faction.getId();
        int amt = Powerboosts.powerboosts.getInt("powerboosts." + ID);
        Powerboosts.powerboosts.set("powerboosts." + ID, amt + 1);
        try {
            Powerboosts.powerboosts.save(Powerboosts.file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
