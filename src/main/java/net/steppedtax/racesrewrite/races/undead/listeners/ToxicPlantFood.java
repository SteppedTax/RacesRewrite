package net.steppedtax.racesrewrite.races.undead.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Set;

public class ToxicPlantFood implements Listener {

    @EventHandler
    public void onItemConsumption(PlayerItemConsumeEvent event) {
        String playerID = event.getPlayer().getUniqueId().toString();
        if (RacesRewrite.undeadPlayers.contains(playerID)) {
            Player player = event.getPlayer();
            ItemStack itemEaten = event.getItem();

            if (PLANT_FOOD.contains(itemEaten.getType())) {
                PotionEffect hungerEffect = new PotionEffect(PotionEffectType.HUNGER, 600, 16, false, true);
                player.addPotionEffect(hungerEffect);
            }
        }
    }

    private final Set<Material> PLANT_FOOD = Set.of(
            Material.APPLE,
            Material.BAKED_POTATO,
            Material.BEETROOT,
            Material.BEETROOT_SOUP,
            Material.BREAD,
            Material.CARROT,
            Material.COOKIE, // arguably
            Material.DRIED_KELP, // even more arguably
            Material.GLOW_BERRIES,
            Material.GOLDEN_APPLE,
            Material.GOLDEN_CARROT,
            Material.MELON_SLICE,
            Material.POISONOUS_POTATO,
            Material.POTATO,
            Material.PUMPKIN_PIE,
            Material.SWEET_BERRIES
    );
}