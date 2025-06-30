package net.steppedtax.racesrewrite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        var playerID = player.getUniqueId().toString();
        var playerName = player.getName();
        if (player.hasPlayedBefore())
        {
            event.setJoinMessage(ChatColor.YELLOW + "What's poppin', " + playerID + "?");
        }
        else {
            Random rand = new Random();
            int randomNum = rand.nextInt(0,3);
            event.setJoinMessage(ChatColor.YELLOW + "You new, homie?");
            switch (randomNum) {
                case 1:
                    Bukkit.getLogger().info(playerName + "was assigned the Undead race.");
                    RacesRewrite.undeadPlayers.add(playerID);
                    break;
                case 2:
                    Bukkit.getLogger().info(playerName + "was assigned the Plant race.");
                    RacesRewrite.plantPlayers.add(playerID);
                    break;
                case 3:
                    Bukkit.getLogger().info(playerName + "was assigned the Blaze race.");
                    RacesRewrite.blazePlayers.add(playerID);
                    break;
                default:
                    Bukkit.getLogger().info(playerName + "was assigned the Human race.");
                    RacesRewrite.removePlayerRace(playerID);  // same as case 0
                    break;
            }
        }
    }
}
