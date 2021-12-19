package me.skippysunday.commands.commands;

import me.skippysunday.Colors;
import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import me.skippysunday.gui.potions.Potions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PotionsCommand extends PSCommand {
    @Override
    public String getName() {
        return "potions";
    }

    @Override
    public boolean hasCompleter() {
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!label.equalsIgnoreCase("potions")) return false;
        else if(!checkUser(sender, "playerstats.potions", args, "/potions <player>") || args.length != 1) return false;

        else if(!(sender instanceof Player)) {
            sender.sendMessage(Colors.ERROR + "Must be a player to run this command!");
            return false;
        }


        LiveUpdateRegister.registerUpdater((Player) sender, new Potions(Bukkit.getPlayerExact(args[0])));


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
