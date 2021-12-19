package me.skippysunday.gui.potions;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
import me.skippysunday.gui.liveupdate.InventoryCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

public class Potions implements InventoryCreator {

    private final Player player;

    public Potions(Player player) {
        this.player = player;
    }

    @Override
    public Inventory createInv() {
        Inventory inv = Bukkit.createInventory(null, 45, Colors.PLAYER + player.getName() + "'s " +
                Colors.BASE + "potion effects");

        ItemStack item = GuiUtils.getHead(player);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colors.PLAYER + player.getName());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(4, item);


        if(player.getActivePotionEffects().isEmpty()) {
            item = new ItemStack(Material.GLASS);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.BASE + "No potion effects");
            meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
            item.setItemMeta(meta);
            inv.setItem(31, item);
            return inv;
        }

        int index = 9;

        for(PotionEffect e : player.getActivePotionEffects()) {
            item = new ItemStack(EffectMaterials.valueOf(e.getType().getName()).mat);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.STAT + EffectMaterials.valueOf(e.getType().getName()).name);
            List<String> lore = new ArrayList<>();
            lore.add(Colors.BASE + "Ticks left: " + e.getDuration());
            lore.add(Colors.BASE + "Strength: " + e.getAmplifier() + 1);
            lore.add(Colors.BASE + "Has particles: " + (e.hasParticles() ? ChatColor.GREEN : ChatColor.RED) + e.hasParticles());
            meta.setLore(lore);
            meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
            item.setItemMeta(meta);
            inv.setItem(index++, item);
        }

        return inv;
    }
}

enum EffectMaterials {
    SPEED(Material.SUGAR, "Swiftness"),
    SLOW(Material.GUNPOWDER, "Slowness"),
    FAST_DIGGING(Material.GOLDEN_PICKAXE, "Haste"),
    SLOW_DIGGING(Material.WOODEN_PICKAXE, "Mining Fatigue"),
    INCREASE_DAMAGE(Material.DIAMOND_SWORD, "Strength"),
    HEAL(Material.GLISTERING_MELON_SLICE, "Instant Health"),
    HARM(Material.REDSTONE, "Instant Harming"),
    JUMP(Material.RABBIT_FOOT, "Jumping"),
    CONFUSION(Material.OBSIDIAN, "Nausea"),
    REGENERATION(Material.APPLE, "Regeneration"),
    DAMAGE_RESISTANCE(Material.TURTLE_HELMET, "Resistance"),
    FIRE_RESISTANCE(Material.MAGMA_CREAM, "Fire Resistance"),
    WATER_BREATHING(Material.PUFFERFISH, "Water Breathing"),
    INVISIBILITY(Material.ARMOR_STAND, "Invisibility"),
    BLINDNESS(Material.CARROT, "Blindness"),
    NIGHT_VISION(Material.GOLDEN_CARROT, "Night vision"),
    HUNGER(Material.ROTTEN_FLESH, "Hunger"),
    WEAKNESS(Material.WOODEN_SWORD, "Weakness"),
    POISON(Material.POISONOUS_POTATO, "Poison"),
    WITHER(Material.WITHER_ROSE, "Withering"),
    HEALTH_BOOST(Material.RED_DYE, "Health Boost"),
    ABSORPTION(Material.GOLDEN_APPLE, "Absorption"),
    SATURATION(Material.COOKED_BEEF, "Saturation"),
    GLOWING(Material.SPECTRAL_ARROW, "Glowing"),
    LEVITATION(Material.FEATHER, "Levitation"),
    LUCK(Material.GREEN_WOOL, "Luck"),
    UNLUCK(Material.BROWN_WOOL, "Unluckiness"),
    SLOW_FALLING(Material.PHANTOM_MEMBRANE, "Slow Falling"),
    CONDUIT_POWER(Material.CONDUIT, "Conduit Power"),
    DOLPHINS_GRACE(Material.KELP, "Dolphins Grace"),
    BAD_OMEN(Material.IRON_AXE, "Bad Omen"),
    HERO_OF_THE_VILLAGE(Material.EMERALD, "Hero of the Village");


    public final Material mat;
    public final String name;

    private EffectMaterials(Material mat, String name) {this.mat = mat; this.name = name;}
}