package me.skippysunday.playerstats;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.commands.commands.Where;
import me.skippysunday.gui.WhereGuiListener;
import me.skippysunday.gui.liveupdate.LiveUpdateListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStats extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new LiveUpdateListener(), this);
        this.getServer().getPluginManager().registerEvents(new WhereGuiListener(), this);
        PSCommand.registerCommands(this, new Where());
    }

    @Override
    public void onDisable() {

    }
}
