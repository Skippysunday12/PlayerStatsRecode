package me.skippysunday.gui.health;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
import me.skippysunday.gui.liveupdate.InventoryCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class HealthGui implements InventoryCreator {

    private final Player player;

    public HealthGui(Player player) {
        this.player = player;
    }


    @Override
    public Inventory createInv() {
        Inventory inv = Bukkit.createInventory(null, 18, Colors.PLAYER + player.getName());

        ItemStack item = GuiUtils.getHead(player);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colors.PLAYER + player.getName());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        item = new ItemStack(Material.GLISTERING_MELON_SLICE);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.BASE + "Health: " + Colors.STAT + (int) player.getHealth());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(11, item);

        item = new ItemStack(Material.COOKED_BEEF);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.BASE + "Hunger bars: " + Colors.STAT + player.getFoodLevel());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(13, item);

        item = new ItemStack(Material.APPLE);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.BASE + "Saturation: " + Colors.STAT + player.getSaturation());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(15, item);

        return inv;
    }
}
