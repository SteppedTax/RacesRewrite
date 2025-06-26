package net.steppedtax.racesrewrite;

import net.steppedtax.racesrewrite.commands.RaceCommand;
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
    public static Set<String> undeadPlayers = new HashSet<>();
    public static Set<String> plantPlayers = new HashSet<>();
    public static Set<String> blazePlayers = new HashSet<>();

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
        // Bukkit tasks
        BukkitTask BurnUnderSunlight = new BurnUnderSunlight(this).runTaskLater(this, 60L);
        // Other stuff
        Objects.requireNonNull(this.getCommand("race")).setExecutor(new RaceCommand());
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
        this.getConfig().set("undead-players", undeadPlayers);
        this.getConfig().set("plant-players", plantPlayers);
        this.getConfig().set("blaze-players", blazePlayers);
        this.saveConfig();
    }

    public void loadPluginConfig() {
        // this.getConfig().getList()
    }
}