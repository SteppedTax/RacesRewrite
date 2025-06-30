package net.steppedtax.racesrewrite.commands;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RaceCommandAutocomplete implements TabCompleter {
    private final RacesRewrite plugin;
    public RaceCommandAutocomplete(RacesRewrite plugin) {
        this.plugin = plugin;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> allRaces = new ArrayList<>();
        allRaces.add("human");
        allRaces.add("undead");
        allRaces.add("plant");
        allRaces.add("blaze");
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player player : players) {
                playerNames.add(player.getName());
            }
            return playerNames;
        } else if (args.length == 2) {
            return allRaces;
        }
        return List.of();
    }
}
