package me.skippysunday.gui.inventory;

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
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class FullInvViewer implements InventoryCreator {

    private final Player player;

    public FullInvViewer(Player player) {
        this.player = player;
    }

    @Override
    public Inventory createInv() {
        Inventory inv = Bukkit.createInventory(null, 45, Colors.PLAYER +
                player.getName() + "'s " + Colors.BASE + "inventory");

        for(int i = 9; i < 36; i++) {
           inv.setItem(i, evalNull(player.getInventory().getItem(i)));
        }

        int index = 27;
        for(int i = 0; i < 9; i++) {
            inv.setItem(index++, evalNull(player.getInventory().getItem(i)));
        }

        inv.setItem(36, evalNull(player.getInventory().getItemInOffHand()));
        inv.setItem(37, evalNull(player.getInventory().getItemInMainHand()));
        inv.setItem(41, evalNull(player.getInventory().getHelmet()));
        inv.setItem(42, evalNull(player.getInventory().getChestplate()));
        inv.setItem(43, evalNull(player.getInventory().getLeggings()));
        inv.setItem(44, evalNull(player.getInventory().getBoots()));

        return inv;
    }

    private ItemStack evalNull(ItemStack item) {
        ItemMeta meta;

        if(item == null || item.getType() == Material.AIR) return new ItemStack(Material.AIR);

        item = item.clone();
        meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        return item;
    }
}
