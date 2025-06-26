package net.steppedtax.racesrewrite.races.undead;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class ToxicPlantFood implements Listener {

    private ItemStack item;

    @EventHandler
    public void onItemConsumption(PlayerItemConsumeEvent event) {
        item = event.getItem();
        Bukkit.getLogger().info(String.valueOf(item));
    }
}
