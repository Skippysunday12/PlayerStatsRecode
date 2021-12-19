package me.skippysunday.commands.commands;

import me.skippysunday.Colors;
import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.gui.where.WhereGui;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Where extends PSCommand {
    @Override
    public String getName() {
        return "where";
    }

    @Override
    public boolean hasCompleter() {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!label.equalsIgnoreCase("where")) return false;

        if(!checkUser(sender, "playerstats.where", args, "/where <player> <coordinates | surface | spawn | see>"))
            return false;

        else if(args.length != 2) {
            sender.sendMessage(Colors.ERROR + "Usage: /where <player> <coordinates | surface | spawn | see>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if(args[1].equalsIgnoreCase("coordinates")) {
            Location location = target.getLocation();
            sender.sendMessage(Colors.PLAYER + args[0] + Colors.BASE + " is at: " + ChatColor.GREEN + "X: " + (int) location.getX() +
                    ChatColor.BLUE + " Y: " + (int) location.getY() + ChatColor.DARK_PURPLE + " Z: " + (int) location.getZ());
            return true;
        }

        else if(args[1].equalsIgnoreCase("surface")) {
            String pos = "" + Colors.STAT;

            if(target.isFlying()) pos += "FLYING";
            else if(target.getFallDistance() == 0) {
                if(target.isSprinting()) pos += "SPRINTING";
                pos += "GROUNDED";
            }
            else if(target.getFallDistance() > 0) pos += "FALLING";
            else if(target.isSwimming()) pos += "SWIMMING";
            else {
                sender.sendMessage(Colors.PLAYER + args[0] + Colors.BASE + " cannot be located!");
                return true;
            }

            sender.sendMessage(Colors.PLAYER + args[0] + Colors.BASE + " is currently " + pos);
            return false;
        }

        else if(args[1].equalsIgnoreCase("spawn")) {
            Location loc = target.getBedSpawnLocation() == null ? target.getWorld().getSpawnLocation() : target.getBedSpawnLocation();
            sender.sendMessage(Colors.PLAYER + target.getName() + Colors.BASE + " spawns at: " + ChatColor.GREEN + "X: "
            + loc.getX() + ChatColor.BLUE + " Y: " + loc.getY() + ChatColor.DARK_PURPLE + " Z: " + loc.getZ());
            return false;
        }

        else if (args[1].equalsIgnoreCase("see")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Colors.ERROR + "You must be a player to run this command!");
                return false;
            }

            LiveUpdateRegister.registerUpdater((Player) sender, new WhereGui(target, sender.getName()));
        }

        else {
            sender.sendMessage(Colors.ERROR + "Usage: /where <player> <coordinates | surface | spawn | see>");
            return true;
        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 2) {
            List<String> argus = new ArrayList<>();
            argus.add("coordinates");
            argus.add("surface");
            argus.add("see");
            argus.add("spawn");
            List<String> results = new ArrayList<>();

            for(String s : argus) {
                if(s.toLowerCase().startsWith(args[1].toLowerCase())) results.add(s);
            }

            return results;
        }
        else return null;

    }
}
