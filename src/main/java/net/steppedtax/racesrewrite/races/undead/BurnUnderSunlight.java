package net.steppedtax.racesrewrite.races.undead;

import org.bukkit.World;
import org.bukkit.entity.Entity;

public abstract class BurnUnderSunlight implements World {

    @Override
    public long getTime() { // infinite recursion, rewrite this
        long gameTime = getTime();
        if ((gameTime >= 12542L) && (gameTime <= 23460L) || !(isClearWeather())) { // if night or not clear weather:
            return gameTime;
        }
        else if (isClearWeather()) { // if day and clear weather:
            // idfk ive been working on this for five hours straight and my dopamine receptors are fucking starving
            // ive went through all nine stages of grief five times in a row
            // i don't understand why i cant implement both the world and the entity WHY IS IT RED
            // i had to cut out a lot of functionality from this plugin because it was just not fucking working
            // i shouldve better been working on listeners than on this shit
            // fuck this shit
        }
        return gameTime;
    }
}