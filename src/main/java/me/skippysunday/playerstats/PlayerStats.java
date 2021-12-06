package me.skippysunday.playerstats;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.commands.commands.Where;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayerStats extends JavaPlugin {

    @Override
    public void onEnable() {
        PSCommand.registerCommands(this, new Where());
    }

    @Override
    public void onDisable() {

    }
}
