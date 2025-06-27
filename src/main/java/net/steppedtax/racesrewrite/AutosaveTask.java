package net.steppedtax.racesrewrite;

import org.bukkit.scheduler.BukkitRunnable;

public class AutosaveTask extends BukkitRunnable {
    private final RacesRewrite plugin;
    public AutosaveTask(RacesRewrite plugin) {
        this.plugin = plugin;
    }
    @Override
    public void run() {
        plugin.savePluginConfig();
    }
}
