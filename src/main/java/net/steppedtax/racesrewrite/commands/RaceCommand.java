package net.steppedtax.racesrewrite.commands;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                String targetName = target.getName();
                String targetID = target.getUniqueId().toString();
                var race = args[1];
                RacesRewrite.allRacePlayers.remove(targetID);
                switch (race) {
                    case "undead":
                        sender.sendMessage(targetName + "'s race was set to Undead.");
                        RacesRewrite.undeadPlayers.add(targetID);
                        break;
                    case "plant":
                        sender.sendMessage(targetName + "'s race was set to Plant.");
                        RacesRewrite.plantPlayers.add(targetID);
                        break;
                    case "blaze":
                        sender.sendMessage(targetName + "'s race was set to Blaze.");
                        RacesRewrite.blazePlayers.add(targetID);
                        break;
                    case "human":
                        sender.sendMessage(targetName + "'s race was set to Human.");
                        RacesRewrite.allRacePlayers.remove(targetID);
                        break;
                }
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Please specify an existing player.");
            }
        }
        return false;
    }
}
