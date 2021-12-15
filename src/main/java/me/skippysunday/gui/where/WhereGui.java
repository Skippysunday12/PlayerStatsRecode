package me.skippysunday.gui.where;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
import me.skippysunday.gui.liveupdate.InventoryCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class WhereGui implements InventoryCreator {

    private final Player player;
    private final String ownerName;

    public WhereGui(Player target, String ownerName) {
        this.player = target;
        this.ownerName = ownerName;
    }

    @Override
    public Inventory createInv() {
        Inventory inv = Bukkit.createInventory(null, 18, Colors.PLAYER + player.getName());

        ItemStack item = GuiUtils.getHead(player);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(Colors.PLAYER + player.getName());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "where-head");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        item = new ItemStack(Material.MAP);
        meta = item.getItemMeta();

        meta.setDisplayName(Colors.STAT + "Location: ");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Click to teleport");
        lore.add(ChatColor.GREEN + "X: " + (int) player.getLocation().getX());
        lore.add(ChatColor.BLUE + "Y: " + (int) player.getLocation().getY());
        lore.add(ChatColor.DARK_PURPLE + "Z: " + (int) player.getLocation().getZ());
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING,
                "where-map-" + player.getName() + "-" + ownerName);
        item.setItemMeta(meta);
        inv.setItem(11, item);

        lore.clear();

        if(player.isFlying()) {
            item = new ItemStack(Material.FEATHER);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.STAT + "Flying");
            lore.add(Colors.PLAYER + player.getName() + ChatColor.GRAY + " is flying");
        }

        else if(player.getFallDistance() == 0) {
            if(player.isSprinting()) {
                item = new ItemStack(Material.SUGAR);
                meta = item.getItemMeta();
                meta.setDisplayName(Colors.STAT + "Sprinting");
                lore.add(Colors.PLAYER + player.getName() + ChatColor.GRAY + " is sprinting");
            } else {
                item = new ItemStack(Material.GRASS_BLOCK);
                meta = item.getItemMeta();
                meta.setDisplayName(Colors.STAT + "Grounded");
                lore.add(Colors.PLAYER + player.getName() + ChatColor.GRAY + " is on the ground");
            }
        }

        else if(player.getFallDistance() > 0) {
            item = new ItemStack(Material.ELYTRA);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.STAT + "Falling");
            lore.add(Colors.PLAYER + player.getName() + ChatColor.GRAY + " has been falling");
            lore.add("for " + Colors.STAT + (int) player.getFallDistance() + ChatColor.GRAY + " blocks");
        }

        else if(player.isSwimming()) {
            item = new ItemStack(Material.WATER_BUCKET);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.STAT + "Swimming");
            lore.add(Colors.PLAYER + player.getName() + ChatColor.GRAY + " is swimming");
        }

        else {
            item = new ItemStack(Material.BEDROCK);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.PLAYER + player.getName() + Colors.ERROR + " could not be located");
        }

        meta.setLore(lore);
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(15, item);

        return inv;
    }
}
