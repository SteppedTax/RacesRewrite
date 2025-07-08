package net.steppedtax.racesrewrite;

import net.steppedtax.racesrewrite.commands.RaceCommand;
import net.steppedtax.racesrewrite.commands.RaceCommandAutocomplete;
import net.steppedtax.racesrewrite.commands.StartupCommand;
import net.steppedtax.racesrewrite.races.plants.listeners.DoubleFireDamage;
import net.steppedtax.racesrewrite.races.undead.listeners.HostileGolems;
import net.steppedtax.racesrewrite.races.undead.listeners.NeutralHostileMobs;
import net.steppedtax.racesrewrite.races.undead.listeners.ToxicPlantFood;
import net.steppedtax.racesrewrite.races.undead.tasks.BurnUnderSunlight;
import net.steppedtax.racesrewrite.races.undead.tasks.RegenInDarkness;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class RacesRewrite extends JavaPlugin {
    FileConfiguration config = this.getConfig();
    public static Set<String> undeadPlayers = new HashSet<String>();
    public static Set<String> plantPlayers = new HashSet<String>();
    public static Set<String> blazePlayers = new HashSet<String>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Load configs
        this.saveDefaultConfig();
        loadPluginConfig();
        // Event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new NeutralHostileMobs(), this);
        getServer().getPluginManager().registerEvents(new HostileGolems(), this);
        getServer().getPluginManager().registerEvents(new ToxicPlantFood(), this);
        getServer().getPluginManager().registerEvents(new DoubleFireDamage(), this);
        // Bukkit tasks
        BukkitTask AutosaveTask = new AutosaveTask(this).runTaskTimer(this, 600L, 600L);
        BukkitTask BurnUnderSunlight = new BurnUnderSunlight(this).runTaskTimer(this, 60L, 60L);
        BukkitTask RegenInDarkness = new RegenInDarkness().runTaskTimer(this, 40L, 40L);
        // Other stuff
        Objects.requireNonNull(this.getCommand("race")).setExecutor(new RaceCommand(this));
        Objects.requireNonNull(this.getCommand("race")).setTabCompleter(new RaceCommandAutocomplete(this));
        Objects.requireNonNull(this.getCommand("startupmsg")).setExecutor(new StartupCommand(this));
        String startupMessage = this.getConfig().getString("startup-message");
        this.getLogger().info(startupMessage); // this.shit
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        savePluginConfig();
    }

    public void savePluginConfig() {
        config.set("undead-players", undeadPlayers.toArray(new String[0]));
        config.set("plant-players", plantPlayers.toArray(new String[0]));
        config.set("blaze-players", blazePlayers.toArray(new String[0]));
        this.saveConfig();
    }

    public void loadPluginConfig() {
        undeadPlayers.addAll(config.getStringList("undead-players"));
        plantPlayers.addAll(config.getStringList("plant-players"));
        blazePlayers.addAll(config.getStringList("blaze-players"));
        // this.getConfig().getList()
    }

    public static void removePlayerRace(String targetID) {
        // I've been told not to use static methods.
        // But I did it anyway.
        RacesRewrite.undeadPlayers.remove(targetID);
        RacesRewrite.plantPlayers.remove(targetID);
        RacesRewrite.blazePlayers.remove(targetID);
    }
}