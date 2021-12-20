package me.skippysunday.commands.commands;

import me.skippysunday.Colors;
import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import me.skippysunday.gui.opinfo.OpInfoInv;
import me.skippysunday.gui.opinfo.PermissionViewer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OPInfo extends PSCommand {
    @Override
    public String getName() {
        return "permissions";
    }

    @Override
    public boolean hasCompleter() {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!label.equalsIgnoreCase("permissions")) return false;
        else if(!checkUser(sender, "playerstats.permissions", args, "/permissions <player> <isop | perms | see>")) return false;
        else if(args.length != 2) {
            sender.sendMessage(Colors.ERROR + "Usage: /permissions <player> <isop | perms | see>");
            return false;
        }

        Player player = Bukkit.getPlayerExact(args[0]);

        if(args[1].equalsIgnoreCase("isop")) {
            sender.sendMessage(Colors.PLAYER + player.getName() +
                    (player.isOp() ? ChatColor.GREEN + " is" : ChatColor.RED + " is not") + Colors.BASE + " op");
            return false;
        }

        else if(args[1].equalsIgnoreCase("perms")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Colors.ERROR + "Must be a player to run this command!");
                return false;
            }

            LiveUpdateRegister.registerUpdater((Player) sender, new PermissionViewer(player, 0));
            return false;
        }

        else if(args[1].equalsIgnoreCase("see")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Colors.ERROR + "Must be a player to run this command!");
                return false;
            }

            LiveUpdateRegister.registerUpdater((Player) sender, new OpInfoInv(player));
            return false;
        }

        else sender.sendMessage(Colors.ERROR + "Usage: /permissions <player> <isop | perms | see>");

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 2) {
            List<String> argus = new ArrayList<>();
            argus.add("isop");
            argus.add("perms");
            argus.add("see");
            List<String> results = new ArrayList<>();

            for(String s : argus) {
                if(s.toLowerCase().startsWith(args[1].toLowerCase())) results.add(s);
            }

            return results;
        }
        else return null;
    }
}
