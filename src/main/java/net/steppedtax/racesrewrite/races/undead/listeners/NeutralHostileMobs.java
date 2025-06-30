package net.steppedtax.racesrewrite.races.undead.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class NeutralHostileMobs implements Listener {

    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        if (event.getEntity() instanceof Monster) {
            if (event.getTarget() instanceof Player player) {
                if (RacesRewrite.undeadPlayers.contains(player.getUniqueId().toString())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();
        if (damager instanceof Player player) {
            if (RacesRewrite.undeadPlayers.contains(player.getUniqueId().toString())) {
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    ((Monster)target).setTarget(player); // this is fucking unreadable but it works
                }
            }
        }
    }
}
