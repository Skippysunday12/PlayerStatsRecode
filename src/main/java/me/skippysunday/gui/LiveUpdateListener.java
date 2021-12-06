package me.skippysunday.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static me.skippysunday.gui.LiveUpdateRegister.updates;

public class LiveUpdateListener implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(updates.containsKey(e.getPlayer())) updates.get(e.getPlayer()).cancel();
        updates.remove(e.getPlayer());
    }
}
