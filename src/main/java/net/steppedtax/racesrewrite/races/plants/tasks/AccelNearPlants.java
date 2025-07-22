package net.steppedtax.racesrewrite.races.plants.tasks;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AccelNearPlants extends BukkitRunnable {
    @Override
    public void run() {
        var onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (playerIsPlant(player)) {
                List<Entity> nearbyEntities = player.getNearbyEntities(8, 8, 8);
                List<Entity> nearbyPlayers = new ArrayList<>();

                for (Entity entity : nearbyEntities) {
                    if (entity.getType() == EntityType.PLAYER) {
                        nearbyPlayers.add(entity);
                        int entityCount = nearbyPlayers.size();

                        if (entityIsPlant(entity)) {
                            PotionEffect speedEffect = new PotionEffect(PotionEffectType.SPEED, 60, entityCount, false, true);
                            player.addPotionEffect(speedEffect);
                        }
                    }
                }
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

    private boolean entityIsPlant(Entity entity) {
        String entityID = entity.getUniqueId().toString();
        return RacesRewrite.plantPlayers.contains(entityID);
    }
}
