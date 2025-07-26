package net.steppedtax.racesrewrite.races.blazes.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class FireballThrowListener implements Listener {

    @EventHandler
    public void onFireballThrow(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand(); // TODO: make the fireball throwable in offhand

        if (playerIsBlaze(player)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (item.getType() == Material.FIRE_CHARGE && !player.hasCooldown(Material.FIRE_CHARGE)) {
                    item.setAmount(item.getAmount() - 1);
                    player.setCooldown(Material.FIRE_CHARGE, 16);
                    player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 1, 1);
                    player.launchProjectile(SmallFireball.class);
                }
            }
        }
    }

    private boolean playerIsBlaze(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.blazePlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }
}
