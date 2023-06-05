package me.skippysunday.playerstats;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.commands.commands.*;
import me.skippysunday.gui.inventory.BaseInvListener;
import me.skippysunday.gui.liveupdate.LiveUpdateListener;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import me.skippysunday.gui.opinfo.OpListener;
import me.skippysunday.gui.where.WhereGuiListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStats extends JavaPlugin {

    private PSInventoryListener listener;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new LiveUpdateListener(), this);
        listener = new PSInventoryListener(this);
        listener.registerListener("where", WhereGuiListener::onCLick);
        listener.registerListener("perms,permspage", OpListener::onClick);
        listener.registerListener("fullinv,viewinv,echest", BaseInvListener::onClick);
        PSCommand.registerCommands(this, new Where(), new Health(), new InventorySee(), new PotionsCommand(), new OPInfo());
        saveDefaultConfig();
        LiveUpdateRegister.setup(getConfig());
    }

    @Override
    public void onDisable() {

    }
}
