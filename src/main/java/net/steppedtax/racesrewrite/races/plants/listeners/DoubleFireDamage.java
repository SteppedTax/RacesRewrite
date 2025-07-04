package net.steppedtax.racesrewrite.races.plants.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DoubleFireDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player && playerIsPlant(player)) {
            if (event.getCause().name().contains("FIRE")) {
                event.setDamage(event.getDamage() * 2.5);
            } else if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                event.setDamage(event.getDamage() * 3.0);
            }
        }
    }

    private boolean playerIsPlant(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.plantPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }
}
