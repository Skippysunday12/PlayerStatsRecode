package me.skippysunday.playerstats;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.commands.commands.*;
import me.skippysunday.gui.inventory.BaseInvListener;
import me.skippysunday.gui.inventory.InventoryBaseInv;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import me.skippysunday.gui.opinfo.OpListener;
import me.skippysunday.gui.where.WhereGuiListener;
import me.skippysunday.gui.liveupdate.LiveUpdateListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class PlayerStats extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new LiveUpdateListener(), this);
        this.getServer().getPluginManager().registerEvents(new WhereGuiListener(), this);
        this.getServer().getPluginManager().registerEvents(new BaseInvListener(), this);
        this.getServer().getPluginManager().registerEvents(new OpListener(), this);
        PSCommand.registerCommands(this, new Where(), new Health(), new InventorySee(), new PotionsCommand(), new OPInfo());
        saveDefaultConfig();
        LiveUpdateRegister.setup(getConfig());
    }

    @Override
    public void onDisable() {

    }
}
