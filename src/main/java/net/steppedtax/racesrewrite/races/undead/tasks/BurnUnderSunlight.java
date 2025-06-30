package net.steppedtax.racesrewrite.races.undead.tasks;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BurnUnderSunlight extends BukkitRunnable {
    private final RacesRewrite plugin;
    public BurnUnderSunlight(RacesRewrite plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        var onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (playerIsValid(player) && isDay(player) && isUnderSky(player)) {
                player.setFireTicks(100);
            }
        }
    }

    private boolean isDay(Player player) {
        long dayTime = player.getWorld().getTime();
        // Bukkit.getLogger().info("Current time: " + dayTime);
        return (dayTime <= 12542L) || (dayTime >= 23460L);
    }

    private boolean isUnderSky(Player player) {
        return player.getLocation().getBlock().getLightFromSky() == 15;
    }

    private boolean playerIsValid(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.undeadPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }
}
// (gameTime >= 12542L) && (gameTime <= 23460L)