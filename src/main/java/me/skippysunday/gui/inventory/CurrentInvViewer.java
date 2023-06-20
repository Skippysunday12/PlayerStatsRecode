package me.skippysunday.gui.inventory;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.function.Supplier;

public class CurrentInvViewer implements Supplier<Inventory> {

    private final Player player;
    private final Inventory empty;

    public CurrentInvViewer(Player player) {
        this.player = player;
        empty = Bukkit.createInventory(null, 9, "");
        ItemStack item = new ItemStack(Material.GLASS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colors.PLAYER + player.getName() + Colors.BASE + " is not viewing an inventory");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        empty.setItem(4, item);
    }


    @Override
    public Inventory get() {
        if (player.getOpenInventory().getTitle().equalsIgnoreCase("crafting")) return empty;

        Inventory current = player.getOpenInventory().getTopInventory();
        Inventory inv = Bukkit.createInventory(null, current.getType(),
                Colors.PLAYER + player.getName() + "'s " + Colors.BASE + "currently viewed inventory");

        for(int i = 0; i < current.getSize(); i++) {
            inv.setItem(i, evalNull(current.getItem(i)));
        }

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
