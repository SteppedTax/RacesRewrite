package net.steppedtax.racesrewrite.races.undead;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class BurnUnderSunlight extends BukkitRunnable {
    private final RacesRewrite plugin;
    public BurnUnderSunlight(RacesRewrite plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        long dayTime = Objects.requireNonNull(Bukkit.getServer().getWorld("world")).getTime();
        plugin.getLogger().info("Current time: " + dayTime);
    }
}
// (gameTime >= 12542L) && (gameTime <= 23460L)