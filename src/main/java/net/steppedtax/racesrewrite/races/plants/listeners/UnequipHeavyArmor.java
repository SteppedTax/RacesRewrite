package net.steppedtax.racesrewrite.races.plants.listeners;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Set;

public class UnequipHeavyArmor implements Listener {

    // Wow, if only there was an event that's called on armor equip!
    // Then I wouldn't need to do all this crap.
    // Disgusting.

    @EventHandler
    public void onArmorClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack cursor = Objects.requireNonNull(event.getCursor());
        if (playerIsPlant(player)) {
            if (event.getSlotType() == InventoryType.SlotType.ARMOR) { // If left click
                if (EnchantmentTarget.ARMOR.includes(cursor)) {
                    if (!LIGHT_ARMOR.contains(cursor.getType())) {
//                        Bukkit.broadcastMessage(ChatColor.GREEN + "Yeah!");
                        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, SoundCategory.PLAYERS, 1, 1);
                        event.setCancelled(true);
                        player.updateInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArmorShiftClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = Objects.requireNonNull(event.getCurrentItem());
        if (playerIsPlant(player) && event.getClick().isShiftClick()) {
//            Bukkit.broadcastMessage(ChatColor.RED + "Shift click!");
            if (event.getSlotType() == InventoryType.SlotType.CONTAINER || event.getSlotType() == InventoryType.SlotType.QUICKBAR) {
                if (!LIGHT_ARMOR.contains(item.getType()) && EnchantmentTarget.ARMOR.includes(item)) {
                    player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, SoundCategory.PLAYERS, 1, 1);
                    event.setResult(Event.Result.DENY);
                    event.setCancelled(true);
                    player.updateInventory();
                }
            }
        }
    }

    @EventHandler
    public void onArmorEquip(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (playerIsPlant(player)) {
            if (EnchantmentTarget.ARMOR.includes(Objects.requireNonNull(event.getItem()))) {
                if (!LIGHT_ARMOR.contains(event.getItem().getType())) {
                    Action action = event.getAction();

                    if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, SoundCategory.PLAYERS, 1, 1);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    // And we still don't account for dispensers.
    // Fuck spigot.

    private boolean playerIsPlant(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.plantPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }

    public Set<Material> LIGHT_ARMOR = Set.of(
            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS
    );
}
