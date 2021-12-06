package me.skippysunday.commands.commands;

import me.skippysunday.commands.base.PSCommand;
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

        if(!checkUser(sender, "playerstats.where", args, "/where <player> <coordinates | surface>"))
            return false;

        else if(args.length != 2) {
            sender.sendMessage(error + "Usage: /where <player> <coordinates | surface>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if(args[1].equalsIgnoreCase("coordinates")) {
            Location location = target.getLocation();
            sender.sendMessage(playerName + args[0] + base + " is at: " + ChatColor.GREEN + "X: " + (int) location.getX() +
                    ChatColor.BLUE + " Y: " + (int) location.getY() + ChatColor.DARK_PURPLE + " Z: " + (int) location.getZ());
            return true;
        }

        else if(args[1].equalsIgnoreCase("surface")) {
            String pos = "" + stat;

            if(target.isFlying()) pos += "FLYING";
            else if(target.getFallDistance() == 0) {
                if(target.isSprinting()) pos += "SPRINTING";
                pos += "GROUNDED";
            }
            else if(target.getFallDistance() > 0) pos += "FALLING";
            else if(target.isSwimming()) pos += "SWIMMING";
            else {
                sender.sendMessage(playerName + args[0] + base + " cannot be located!");
                return true;
            }

            sender.sendMessage(playerName + args[0] + base + " is currently " + pos);
            return false;
        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 2) {
            List<String> argus = new ArrayList<>();
            argus.add("coordinates");
            argus.add("surface");
            List<String> results = new ArrayList<>();

            for(String s : argus) {
                if(s.toLowerCase().startsWith(args[1].toLowerCase())) results.add(s);
            }

            return results;
        }
        else return null;

    }
}
