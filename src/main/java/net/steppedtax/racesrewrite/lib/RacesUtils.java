package net.steppedtax.racesrewrite.lib;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class RacesUtils {

    // TODO: implement this properly in for- statements
    public static boolean playerIsValid(Player player, Race race) {
        String playerID = player.getUniqueId().toString();

        if (player.getGameMode() == GameMode.SURVIVAL) {
            return switch (race) {
                case UNDEAD -> RacesRewrite.undeadPlayers.contains(playerID);
                case PLANT -> RacesRewrite.plantPlayers.contains(playerID);
                case BLAZE -> RacesRewrite.blazePlayers.contains(playerID);
            };
        }
        return false;
    }
}
