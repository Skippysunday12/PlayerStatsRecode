package me.skippysunday.gui.liveupdate;

import me.skippysunday.playerstats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.function.Supplier;

public class LiveUpdateRegister implements Listener {

    private LiveUpdateRegister(){}

    protected static HashMap<Player, BukkitTask> updates = new HashMap<>();
    private static BukkitScheduler scheduler;
    private static int updateTime;

    public static void setup(FileConfiguration file) {
        scheduler = Bukkit.getScheduler();
        updateTime = file.getInt("liveupdatetime");
    }

    public static void registerUpdater(Player player, Supplier<Inventory> creator) {
        updates.put(player, scheduler.runTaskTimer(PlayerStats.getPlugin(PlayerStats.class),
                new LiveUpdater(player, creator), updateTime, updateTime));
    }

    public static void cancelTask(Player player) {
        updates.get(player).cancel();
    }

}

class LiveUpdater implements Runnable {

    private final Player player;
    private final Supplier<Inventory> inv;

    public LiveUpdater(Player player, Supplier<Inventory> inv) {
        this.player = player;
        this.inv = inv;
    }

    @Override
    public void run() {
        player.openInventory(inv.get());
    }
}
