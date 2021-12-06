package me.skippysunday.commands.commands;

import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.gui.LiveUpdateRegister;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class LiveTest extends PSCommand {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!label.equalsIgnoreCase("test")) return false;

        LiveUpdateRegister.registerUpdater((Player) sender, new LiveTestInterface((Player) sender));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
