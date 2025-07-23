package net.steppedtax.racesrewrite.races.undead.tasks;

import net.steppedtax.racesrewrite.RacesRewrite;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.Set;

public class BurnUnderSunlight extends BukkitRunnable {
    private final RacesRewrite plugin;
    public BurnUnderSunlight(RacesRewrite plugin) {
        this.plugin = plugin;
    }
    Random rand = new Random();

    @Override
    public void run() {
        var onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player player : onlinePlayers) {
            if (playerIsValid(player) && isClearDay(player) && isUnderSky(player)) {
                ItemStack headSlot = player.getInventory().getHelmet();

                if (isHeadSlotEmpty(headSlot)) {
                    player.setFireTicks(60);
                }
                if (hasHelmetEquipped(headSlot)) {
                    short durability = getDurability(headSlot);
                    short finalDurability = (short) (durability + durabilityAddend(headSlot));
                    short maxDurability = (headSlot.getType().getMaxDurability());

                    if (finalDurability >= maxDurability) {
                        breakHelmet(player);
                        return;
                    }

                    setDurability(headSlot, finalDurability);
                }
                else if (!hasHelmetEquipped(headSlot)) {
                    if (rand.nextInt(128) < 8) {
                        breakHelmet(player);
                    }
                }
            }
        }
    }

    private boolean isClearDay(Player player) {
        long dayTime = player.getWorld().getTime();
        boolean clearWeather = player.getWorld().isClearWeather();
        // Bukkit.getLogger().info("Current time: " + dayTime);
        return (dayTime <= 12542L) || (dayTime >= 23460L) && clearWeather;
    }

    private boolean isUnderSky(Player player) {
        return player.getLocation().getBlock().getLightFromSky() == 15;
    }

    private boolean isHeadSlotEmpty(ItemStack headSlot) {
        return headSlot != null && headSlot.getType() == Material.AIR;
    }

    private boolean hasHelmetEquipped(ItemStack headSlot) {
        return headSlot != null && HELMET.contains(headSlot.getType());
    }

    private boolean playerIsValid(Player player) {
        String playerID = player.getUniqueId().toString();
        if (RacesRewrite.undeadPlayers.contains(playerID)) {
            return player.getGameMode() == GameMode.SURVIVAL; // return true
        }
        return false;
    }

    private void breakHelmet(Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 10, 1);
        player.getInventory().setHelmet(null);
    }

    public short getDurability(ItemStack headSlot) {
        ItemMeta meta = headSlot.getItemMeta();
        return (meta == null) ? 0 : (short) ((Damageable) meta).getDamage();
        // this is where I learned about ternary operators...
    }

    public void setDurability(ItemStack headSlot, short durability) {
        ItemMeta meta = headSlot.getItemMeta();
        if (meta != null) {
            ((Damageable) meta).setDamage(durability);
            headSlot.setItemMeta(meta);
        }
        // shoutout to craftbukkit source code
    }

    private short durabilityAddend(ItemStack headSlot) {
        int chance = 10;
        if (headSlot.containsEnchantment(Enchantment.UNBREAKING)) {
            int level = headSlot.getEnchantmentLevel(Enchantment.UNBREAKING);
            switch (level) {
                case 1:
                    chance = 25;
                    break;
                case 2:
                    chance = 36;
                    break;
                case 3:
                    chance = 43;
                    break;
            }
        }
        if (rand.nextInt(100) >= chance) {
            return 1;
        }
        return 0;
    }

    private final Set<Material> HELMET = Set.of(
            Material.LEATHER_HELMET,
            Material.GOLDEN_HELMET,
            Material.CHAINMAIL_HELMET,
            // Material.COPPER_HELMET,
            Material.IRON_HELMET,
            Material.DIAMOND_HELMET,
            Material.NETHERITE_HELMET,
            Material.TURTLE_HELMET // special
    );
}
// (gameTime >= 12542L) && (gameTime <= 23460L)