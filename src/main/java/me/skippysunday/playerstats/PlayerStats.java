package me.skippysunday.playerstats;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.commands.commands.Health;
import me.skippysunday.commands.commands.InventorySee;
import me.skippysunday.commands.commands.Where;
import me.skippysunday.gui.inventory.BaseInvListener;
import me.skippysunday.gui.inventory.InventoryBaseInv;
import me.skippysunday.gui.where.WhereGuiListener;
import me.skippysunday.gui.liveupdate.LiveUpdateListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStats extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new LiveUpdateListener(), this);
        this.getServer().getPluginManager().registerEvents(new WhereGuiListener(), this);
        this.getServer().getPluginManager().registerEvents(new BaseInvListener(), this);
        PSCommand.registerCommands(this, new Where(), new Health(), new InventorySee());
    }

    @Override
    public void onDisable() {

    }
}
