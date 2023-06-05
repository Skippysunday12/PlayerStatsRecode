package me.skippysunday.gui.inventory;

import me.skippysunday.gui.GuiUtils;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BaseInvListener {

    public static void onClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        String[] keys = item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).split("-");

        if(keys[0].startsWith("fullinv")) {
            LiveUpdateRegister.cancelTask((Player) event.getWhoClicked());
            LiveUpdateRegister.registerUpdater((Player) event.getWhoClicked(), new FullInvViewer(Bukkit.getPlayerExact(keys[1])));
        }

        else if(keys[0].startsWith("viewinv")) {
            LiveUpdateRegister.cancelTask((Player) event.getWhoClicked());
            LiveUpdateRegister.registerUpdater((Player) event.getWhoClicked(), new CurrentInvViewer(Bukkit.getPlayerExact(keys[1])));
        }

        if(keys[0].startsWith("echest")) {
            LiveUpdateRegister.cancelTask((Player) event.getWhoClicked());
            LiveUpdateRegister.registerUpdater((Player) event.getWhoClicked(), new EchestViewer(Bukkit.getPlayerExact(keys[1])));
        }
    }

}
