package net.steppedtax.racesrewrite.races.blazes.tasks;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class WaterDamagesPlayer extends BukkitRunnable {

    @Override
    public void run() {
        var onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (playerIsBlaze(player)) {
                if (!isWearingHelmet(player) & isPlayerUnderRain(player) || isPlayerInWater(player)) {
                    damagePlayer(player);
                }
            }
        }
    }

    private boolean isPlayerInWater(Player player) {
        Material block = player.getLocation().getBlock().getType();
        return block == Material.WATER;
    }

    private boolean isPlayerUnderRain(Player player) {
        return player.getLocation().getBlock().getLightFromSky() == 15 && player.getWorld().hasStorm();
    }

    private boolean isWearingHelmet(Player player) {
        Material helmet = player.getInventory().getHelmet().getType(); // warning the ide is having an aneurysm
        return helmet == Material.LEATHER_HELMET;
    }

    private void damagePlayer(Player player) {
        if (!player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
            player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.5F, 1);
            player.damage(1);
        }
    }

    private boolean playerIsBlaze(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.blazePlayers.contains(playerID) && !player.isDead()) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }
}
