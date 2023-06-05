package me.skippysunday.gui.where;

import me.skippysunday.Colors;
import me.skippysunday.gui.GuiUtils;
import me.skippysunday.playerstats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class WhereGuiListener {

    public static void onCLick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();

        if(item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).contains("where-map")) {
            String[] parts = item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).split("-");
            Bukkit.getPlayerExact(parts[3]).teleport(Bukkit.getPlayerExact(parts[2]));
            Bukkit.getPlayerExact(parts[3]).sendMessage(Colors.STAT + "You have been teleported!");
        }

        else if(item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).contains("where-bed")) {
            String[] parts = item.getItemMeta().getPersistentDataContainer().get(GuiUtils.key, PersistentDataType.STRING).split("-");
            Player target = Bukkit.getPlayerExact(parts[2]);
            Location loc = target.getBedSpawnLocation() == null ? target.getWorld().getSpawnLocation() : target.getBedSpawnLocation();
            Bukkit.getPlayerExact(parts[3]).teleport(loc);
            Bukkit.getPlayerExact(parts[3]).sendMessage(Colors.STAT + "You have been teleported!");
        }
    }
}
