package me.skippysunday.gui;

import me.skippysunday.playerstats.PlayerStats;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GuiUtils {

    public static ItemStack getHead(Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if(!meta.setOwningPlayer(p)) return null;

        item.setItemMeta(meta);
        return item;
    }

    public static NamespacedKey key = new NamespacedKey(PlayerStats.getPlugin(PlayerStats.class), "Identifier");
}
