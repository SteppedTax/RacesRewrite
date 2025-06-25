package net.steppedtax.racesrewrite.races.undead;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getLogger;

public class ToxicPlantFood implements Listener {

    private ItemStack item;

    @EventHandler
    public void onItemConsumption(PlayerItemConsumeEvent event) {
        item = event.getItem();
        getLogger().info(String.valueOf(item));
    }
}
