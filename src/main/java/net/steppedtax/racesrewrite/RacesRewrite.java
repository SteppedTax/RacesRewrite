package net.steppedtax.racesrewrite;

import net.steppedtax.racesrewrite.commands.CreditsCommand;
import net.steppedtax.racesrewrite.races.plants.tasks.restoreHungerOnPhotosynthesisTask;
import net.steppedtax.racesrewrite.races.undead.NeutralHostileMobs;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

public final class RacesRewrite extends JavaPlugin {
    public static Set<String> plantPlayers = new HashSet<>();
    public static Set<String> undeadPlayers = new HashSet<>();
    public static Set<String> blazePlayers = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        // saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new NeutralHostileMobs(),this);
        getCommand("rrcredits").setExecutor(new CreditsCommand());
        BukkitTask restoreHungerOnPhotosynthesisTask = new restoreHungerOnPhotosynthesisTask(this).runTaskLater(this, 100L);
        /* would you get races randomly or by choice?
        right now I'll plug in a temporary solution */
        getLogger().info("The plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadRaces() {
        /* plantPlayers.toArray() = getConfig().getList("plant-players");
        getLogger().info("Races loaded!");
        getLogger().info(plantPlayers.toString());
        getLogger().info(undeadPlayers.toString());
        getLogger().info(blazePlayers.toString()); */
    }

    public void saveRaces() {
        /* getConfig().set("plant-players", plantPlayers);
        getConfig().set("undead-players", undeadPlayers);
        getConfig().set("blaze-players", blazePlayers);
        saveConfig(); */
    }
}
