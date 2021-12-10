package me.skippysunday.gui;

import me.skippysunday.Colors;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class WhereGuiListener implements Listener {

    @EventHandler
    public void onCLick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();

        if(item == null || !item.hasItemMeta() || !item.getItemMeta().getPersistentDataContainer()
                .has(GuiUtils.key, PersistentDataType.STRING)) return;

        e.setCancelled(true);

        if(item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).contains("where-map")) {
            String[] parts = item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).split("-");
            Bukkit.getPlayerExact(parts[3]).teleport(Bukkit.getPlayerExact(parts[2]));
            Bukkit.getPlayerExact(parts[3]).sendMessage(Colors.STAT + "You have been teleported!");
        }
    }
}
