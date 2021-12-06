package me.skippysunday.commands.commands;

import me.skippysunday.gui.InventoryCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LiveTestInterface implements InventoryCreator {

    private final Player player;

    public LiveTestInterface(Player player) {
        this.player = player;
    }

    @Override
    public Inventory createInv() {
        Inventory inv = Bukkit.createInventory(null, 54);
        ItemStack item = new ItemStack(Material.DIRT);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("WEEEEEE");
        List<String> lore = new ArrayList<>();
        lore.add("" + player.getLocation().getY());
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(39, item);

        return inv;
    }
}
