package me.skippysunday.playerstats;

import me.skippysunday.gui.GuiUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.function.Consumer;

public class PSInventoryListener implements Listener {

    private HashMap<String, Consumer<InventoryClickEvent>> listeners;

    public PSInventoryListener(JavaPlugin ps) {
        ps.getServer().getPluginManager().registerEvents(this, ps);
        listeners = new HashMap<>();
    }

    public void registerListener(String listenerKey, Consumer<InventoryClickEvent> listenerFunction) {
        listeners.put(listenerKey, listenerFunction);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();

        if(item == null || !item.hasItemMeta() || item.getItemMeta().getPersistentDataContainer() == null ||
                !item.getItemMeta().getPersistentDataContainer().has(GuiUtils.key, PersistentDataType.STRING)) return;

        e.setCancelled(true);

        String listenerKey = item.getItemMeta().getPersistentDataContainer().
                get(GuiUtils.key, PersistentDataType.STRING).split("-")[0];
        listeners.keySet().stream().filter(s -> s.contains(listenerKey)).forEach(s -> listeners.get(s).accept(e));
    }

}
