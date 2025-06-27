package net.steppedtax.racesrewrite;

import net.steppedtax.racesrewrite.commands.RaceCommand;
import net.steppedtax.racesrewrite.commands.RaceCommandAutocomplete;
import net.steppedtax.racesrewrite.commands.StartupCommand;
import net.steppedtax.racesrewrite.races.undead.BurnUnderSunlight;
import net.steppedtax.racesrewrite.races.undead.HostileGolems;
import net.steppedtax.racesrewrite.races.undead.NeutralHostileMobs;
import net.steppedtax.racesrewrite.races.undead.ToxicPlantFood;
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
    // public static Set<UUID> humanPlayers = new HashSet<UUID>();
    public static Set<String> allRacePlayers = new HashSet<String>(); {
        allRacePlayers.retainAll(undeadPlayers);
        allRacePlayers.retainAll(plantPlayers);
        allRacePlayers.retainAll(blazePlayers);
        // allRacePlayers.retainAll(humanPlayers);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Load configs
        this.saveDefaultConfig();
        loadPluginConfig();
        // Event handlers
        getServer().getPluginManager().registerEvents(new NeutralHostileMobs(), this);
        getServer().getPluginManager().registerEvents(new HostileGolems(), this);
        getServer().getPluginManager().registerEvents(new ToxicPlantFood(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        // Bukkit tasks
        BukkitTask AutosaveTask = new AutosaveTask(this).runTaskTimer(this, 600L, 600L);
        BukkitTask BurnUnderSunlight = new BurnUnderSunlight(this).runTaskTimer(this, 60L, 100L);
        // Other stuff
        Objects.requireNonNull(this.getCommand("race")).setExecutor(new RaceCommand());
        Objects.requireNonNull(this.getCommand("race")).setTabCompleter(new RaceCommandAutocomplete());
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
        // config.set("human-players", humanPlayers.toArray(new UUID[0]));
        this.saveConfig();
    }

    public void loadPluginConfig() {
        undeadPlayers.addAll(config.getStringList("undead-players"));
        plantPlayers.addAll(config.getStringList("plant-players"));
        blazePlayers.addAll(config.getStringList("blaze-players"));
        // undeadPlayers.addAll(config.get("undead-players"));
        // this.getConfig().getList()
    }
}