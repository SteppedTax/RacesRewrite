package net.steppedtax.racesrewrite;

import net.steppedtax.racesrewrite.commands.RaceCommand;
import net.steppedtax.racesrewrite.races.plants.tasks.restoreHungerOnPhotosynthesisTask;
import net.steppedtax.racesrewrite.races.undead.HostileGolems;
import net.steppedtax.racesrewrite.races.undead.NeutralHostileMobs;
import net.steppedtax.racesrewrite.races.undead.ToxicPlantFood;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class RacesRewrite extends JavaPlugin {
    // public static Set<String> plantPlayers = new HashSet<>();
    // public static Set<String> undeadPlayers = new HashSet<>();
    // public static Set<String> blazePlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Event handlers
        getServer().getPluginManager().registerEvents(new NeutralHostileMobs(),this);
        getServer().getPluginManager().registerEvents(new HostileGolems(),this);
        getServer().getPluginManager().registerEvents(new ToxicPlantFood(),this);
        // Bukkit tasks
        BukkitTask restoreHungerOnPhotosynthesisTask = new restoreHungerOnPhotosynthesisTask(this).runTaskLater(this, 100L);
        // Other stuff
        Objects.requireNonNull(getCommand("race")).setExecutor(new RaceCommand());
        getLogger().info("The plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
