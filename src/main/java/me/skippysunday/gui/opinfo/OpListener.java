package me.skippysunday.gui.opinfo;

import me.skippysunday.gui.GuiUtils;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class OpListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();

        if(item == null || !item.hasItemMeta() || !item.getItemMeta().getPersistentDataContainer()
                .has(GuiUtils.key, PersistentDataType.STRING)) return;

        e.setCancelled(true);

        String[] parts = item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).split("-");

        if(parts[0].startsWith("perms")) {
            Player player = Bukkit.getPlayerExact(parts[1]);
            LiveUpdateRegister.cancelTask((Player) e.getWhoClicked());
            LiveUpdateRegister.registerUpdater((Player) e.getWhoClicked(), new PermissionViewer(player, 0));
        }

        else if(parts[0].startsWith("permpage")) {
            LiveUpdateRegister.cancelTask((Player) e.getWhoClicked());
            LiveUpdateRegister.registerUpdater((Player) e.getWhoClicked(), new PermissionViewer(Bukkit.getPlayerExact(parts[2]),
                    Integer.parseInt(parts[1])));
        }
    }

}
