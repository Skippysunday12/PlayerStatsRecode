package me.skippysunday.commands.commands;

import me.skippysunday.Colors;
import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.gui.inventory.InventoryBaseInv;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class InventorySee extends PSCommand {
    @Override
    public String getName() {
        return "inv";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!label.equalsIgnoreCase("inv")) return false;
        if(!(sender instanceof Player)) {
            sender.sendMessage(Colors.ERROR + "You must be a player to run this command!");
            return false;
        }

        else if(!checkUser(sender, "playerstats.inv", args, "/inv <player>"))
            return false;

        else if(args.length != 1) {
            sender.sendMessage(Colors.ERROR + "Usage: /inv <player>");
            return false;
        }

        else if(Bukkit.getPlayerExact(args[0]) == null) {
            sender.sendMessage(Colors.ERROR + args[0] + " is not online!");
            return false;
        }

        LiveUpdateRegister.registerUpdater((Player) sender, new InventoryBaseInv(Bukkit.getPlayerExact(args[0]), (Player) sender));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
