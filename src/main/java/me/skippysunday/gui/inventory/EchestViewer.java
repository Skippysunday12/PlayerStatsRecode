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

public class EchestViewer implements Supplier<Inventory> {

    private final Player player;

    public EchestViewer(Player player) {
        this.player = player;
    }

    @Override
    public Inventory get() {
        Inventory inv = Bukkit.createInventory(null, player.getEnderChest().getSize(), Colors.PLAYER +
                player.getName() + "'s " + Colors.BASE + "ender chest");

        for(int i = 0; i < player.getEnderChest().getSize(); i++) {
            inv.setItem(i, evalNull(player.getEnderChest().getItem(i)));
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
