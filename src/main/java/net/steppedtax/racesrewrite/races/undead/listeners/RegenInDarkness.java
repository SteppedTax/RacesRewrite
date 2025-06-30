package net.steppedtax.racesrewrite.races.undead.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RegenInDarkness implements Listener {
    // TODO: make this a task instead

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (playerIsValid(player) && playerLightLevel(player) == 0) {
//            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BREATH, 10, 1);
//            player.sendMessage("Something wicked this way comes.");
            PotionEffect regenEffect = new PotionEffect(PotionEffectType.REGENERATION, 3 * 20, 1, false, false);
            player.addPotionEffect(regenEffect); // will change later

        }
    }

    private boolean playerIsValid(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.undeadPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }

    private byte playerLightLevel(Player player) {
        Block block = player.getLocation().getBlock();
        return block.getLightLevel();
    }
}
