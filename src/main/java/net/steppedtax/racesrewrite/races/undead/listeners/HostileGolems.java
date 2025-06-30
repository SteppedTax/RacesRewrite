package net.steppedtax.racesrewrite.races.undead.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class HostileGolems implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (playerIsValid(player)) {
            List<Entity> nearbyEntities = player.getNearbyEntities(8, 8, 8);

            for (Entity entity : nearbyEntities) {
                if (entity.getType() == EntityType.IRON_GOLEM) {
                    if ((((IronGolem) entity).getTarget() == null)) {
                        ((IronGolem) entity).setTarget(player); // :thumbsup:
                    }
                }
            }
        }
    }

    private boolean playerIsValid(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.undeadPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }
}
