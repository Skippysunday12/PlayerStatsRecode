package me.skippysunday.playerstats;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.commands.commands.LiveTest;
import me.skippysunday.commands.commands.Where;
import me.skippysunday.gui.LiveUpdateListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStats extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new LiveUpdateListener(), this);
        PSCommand.registerCommands(this, new Where(), new LiveTest());
    }

    @Override
    public void onDisable() {

    }
}
