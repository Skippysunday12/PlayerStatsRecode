package me.skippysunday.gui;

import me.skippysunday.playerstats.PlayerStats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static me.skippysunday.gui.LiveUpdateRegister.updates;

public class LiveUpdateListener implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!updates.containsKey(e.getPlayer())) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (e.getPlayer().getOpenInventory().getTitle().equalsIgnoreCase("crafting")) {
                    updates.remove(e.getPlayer()).cancel();
                }}}.runTaskLater(PlayerStats.getPlugin(PlayerStats.class), 2);
    }
}
