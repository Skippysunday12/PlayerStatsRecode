package me.skippysunday.gui.liveupdate;

import me.skippysunday.playerstats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class LiveUpdateRegister implements Listener {

    private LiveUpdateRegister(){}

    protected static HashMap<Player, BukkitTask> updates = new HashMap<>();
    private static final BukkitScheduler scheduler;

    static {
        scheduler = Bukkit.getScheduler();
    }

    public static void registerUpdater(Player player, InventoryCreator creator) {
        updates.put(player, scheduler.runTaskTimer(PlayerStats.getPlugin(PlayerStats.class),
                new LiveUpdater(player, creator), 20, 20));
    }

    public static void cancelTask(Player player) {
        updates.get(player).cancel();
    }

}

class LiveUpdater implements Runnable {

    private final Player player;
    private final InventoryCreator inv;

    public LiveUpdater(Player player, InventoryCreator inv) {
        this.player = player;
        this.inv = inv;
    }

    @Override
    public void run() {
        player.openInventory(inv.createInv());
    }
}
