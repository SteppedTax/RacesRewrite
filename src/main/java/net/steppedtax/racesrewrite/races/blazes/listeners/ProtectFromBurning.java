package net.steppedtax.racesrewrite.races.blazes.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ProtectFromBurning implements Listener {

    @EventHandler
    public void onFireDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player && playerIsBlaze(player)) {
            switch (event.getCause()) {
                case FIRE, FIRE_TICK, HOT_FLOOR:
                    event.setCancelled(true);
                    break;
                case LAVA: // CHICKEN
                    // TODO: fix for Paper servers because Paper sucks
                    if (player.getServer().getName().equalsIgnoreCase("paper")) {
                        player.stopAllSounds(); // band-aid fix
                    }
                    event.setDamage(1);
                    player.setFireTicks(player.getFireTicks() / 2);
                    break;
            }
        }
    }

    private boolean playerIsBlaze(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.blazePlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }
}
