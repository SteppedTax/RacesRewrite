package net.steppedtax.racesrewrite.races.plants.tasks;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class PlantsRegenHunger extends BukkitRunnable {

    @Override
    public void run() {
        var onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (playerIsPlant(player) && plantBlockBelow(player) && player.getLocation().getBlock().getLightLevel() == 15) {
                if (player.getFoodLevel() < 20) {
                    // TODO: We need more feedback for hunger regeneration
                    player.setFoodLevel(player.getFoodLevel() + 1);
                }
            }
        }
    }

    private boolean plantBlockBelow(Player player) {
        Material block = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
        return PLANT_BLOCK.contains(block) || block.toString().contains("LEAVES");
    }

    private boolean playerIsPlant(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.plantPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }

    private final Set<Material> PLANT_BLOCK = Set.of(
            Material.GRASS_BLOCK,
            Material.MOSS_BLOCK
            // add more later
    );
}
