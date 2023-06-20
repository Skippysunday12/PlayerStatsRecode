package me.skippysunday.gui.inventory;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
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
import java.util.function.Supplier;

public class InventoryBaseInv implements Supplier<Inventory> {

    private final Player target;
    private final Player sender;

    public InventoryBaseInv(Player target, Player sender) {
        this.target = target;
        this.sender = sender;
    }

    @Override
    public Inventory get() {
        Inventory inv = Bukkit.createInventory(null, 45, Colors.PLAYER + target.getName() + "'s "
                + Colors.BASE + "inventory stats");

        ItemStack item = GuiUtils.getHead(target);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colors.PLAYER + target.getName());
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        inv.setItem(10, evalNull(target.getInventory().getHelmet()));
        inv.setItem(19, evalNull(target.getInventory().getChestplate()));
        inv.setItem(28, evalNull(target.getInventory().getLeggings()));
        inv.setItem(37, evalNull(target.getInventory().getBoots()));

        item = new ItemStack(Material.IRON_HELMET);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Helmet");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(9, item);

        item = new ItemStack(Material.IRON_CHESTPLATE);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Chestplate");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(18, item);

        item = new ItemStack(Material.IRON_LEGGINGS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Leggings");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(27, item);

        item = new ItemStack(Material.IRON_BOOTS);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Boots");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(36, item);

        item = new ItemStack(Material.ITEM_FRAME);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Offhand");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(21, item);

        item = new ItemStack(Material.ITEM_FRAME);
        meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + "Main hand");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        inv.setItem(23, item);

        inv.setItem(30, evalNull(target.getInventory().getItemInOffHand()));
        inv.setItem(32, evalNull(target.getInventory().getItemInMainHand()));

        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.BASE + "View full inventory");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "fullinv-" + target.getName());
        item.setItemMeta(meta);
        inv.setItem(8, item);

        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.BASE + "View Currently viewing inventory");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "viewinv-" + target.getName());
        item.setItemMeta(meta);
        inv.setItem(44, item);

        item = new ItemStack(Material.ENDER_CHEST);
        meta = item.getItemMeta();
        meta.setDisplayName(Colors.BASE + "View Ender Chest");
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "echest-" + target.getName());
        item.setItemMeta(meta);
        inv.setItem(26, item);

        return inv;
    }

    private ItemStack evalNull(ItemStack item) {
        ItemMeta meta;

        if(item == null || item.getType() == Material.AIR) {
            item = new ItemStack(Material.GLASS);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.BASE + "No item");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "No item in this slot");
            meta.setLore(lore);
        }

        else {
            item = item.clone();
            meta = item.getItemMeta();
        }
        meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
        item.setItemMeta(meta);
        return item;
    }
}
