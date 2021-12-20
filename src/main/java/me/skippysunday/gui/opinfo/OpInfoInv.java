package me.skippysunday.gui.opinfo;

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

public class OpInfoInv implements InventoryCreator {

    private final Player player;

    public OpInfoInv(Player player) {
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

        item = new ItemStack(player.isOp() ? Material.GREEN_WOOL : Material.RED_WOOL);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.STAT + (player.isOp() ? "Is operator" : "Is not operator"));
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(11, item);

        item = new ItemStack(Material.PAPER);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.STAT + "Permissions");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Click to view permissions");
        meta.setLore(lore);
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "perms-" + player.getName());
        item.setItemMeta(meta);
        inv.setItem(15, item);

        return inv;
    }
}
