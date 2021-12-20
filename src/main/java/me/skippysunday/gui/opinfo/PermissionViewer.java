package me.skippysunday.gui.opinfo;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
import me.skippysunday.gui.liveupdate.InventoryCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PermissionViewer implements InventoryCreator {

    private final Player player;
    private final int page;

    public PermissionViewer(Player player, int page) {
        this.player = player;
        this.page = page;
    }

    @Override
    public Inventory createInv() {
        Inventory inv = Bukkit.createInventory(null, 54, Colors.PLAYER + player.getName() + "'s " +
                Colors.BASE + "permissions");

        ItemStack item;
        ItemMeta meta;

        List<PermissionAttachmentInfo> perms = new ArrayList<>(player.getEffectivePermissions());

        if(page != 0) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.BASE + "Back");
            meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "permpage-" + player.getName() + "-" + (page - 1));
            item.setItemMeta(meta);
            inv.setItem(45, item);
        }

        if(page != (perms.size() % 45 != 0 ? perms.size() / 45 > 0 ? perms.size() / 45 + 1 : 0 : perms.size())) {
            item = new ItemStack(Material.ARROW);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.BASE + "Next page");
            meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "permpage-" + player.getName() + "-" + (page + 1));
            item.setItemMeta(meta);
            inv.setItem(53, item);
        }

        for(int i = page * 45; i < (page + 1) * 45 && i < perms.size(); i++) {
            item = new ItemStack(Material.PAPER);
            meta = item.getItemMeta();
            meta.setDisplayName(Colors.STAT + perms.get(i).getPermission());
            meta.getPersistentDataContainer().set(GuiUtils.key, PersistentDataType.STRING, "ps-identifier");
            item.setItemMeta(meta);
            inv.setItem(i, item);
        }

        return inv;
    }
}
