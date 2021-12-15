package me.skippysunday.commands.commands;

import me.skippysunday.Colors;
import me.skippysunday.commands.base.PSCommand;
import me.skippysunday.gui.health.HealthGui;
import me.skippysunday.gui.liveupdate.LiveUpdateRegister;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.ArrayList;
import java.util.List;

public class Health extends PSCommand {
    @Override
    public String getName() {
        return "health";
    }

    @Override
    public boolean hasCompleter() {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!label.equalsIgnoreCase("health")) return false;

        else if(!checkUser(sender, "playerstats.health", args, "/health <player> <health | saturation | lastdamage | see>"))
            return false;

        else if(args.length != 2) {
            sender.sendMessage(Colors.ERROR + "/health <player> <health | saturation | lastdamage | see>");
            return false;
        }

        Player player = Bukkit.getPlayerExact(args[0]);
        if(player == null) {
            sender.sendMessage(Colors.ERROR + "That player is not online!");
            return false;
        }

        if(args[1].equalsIgnoreCase("health")) {
            sender.sendMessage(Colors.PLAYER + player.getName() + Colors.BASE + " is at " + Colors.STAT + player.getHealth()
            + Colors.BASE + " hp");
        }

        else if(args[1].equalsIgnoreCase("saturation")) {
            sender.sendMessage(Colors.PLAYER + player.getName() + Colors.BASE + " has " + Colors.STAT + ((double) player.getFoodLevel() / 2)
            + Colors.BASE + " food bars, and " + Colors.STAT + player.getSaturation() + Colors.BASE + " saturation");
        }

        else if(args[1].equalsIgnoreCase("lastdamage")) {
            sender.sendMessage(Colors.PLAYER + player.getName() + Colors.BASE + " was last damaged "
                    + Colors.STAT + player.getLastDamage() + Colors.BASE + " hitpoints, by cause " + Colors.STAT +
                    player.getLastDamageCause().getCause());
        }

        else if(args[1].equalsIgnoreCase("see")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Colors.ERROR + "You must be a player to run this command!");
                return false;
            }

            LiveUpdateRegister.registerUpdater((Player) sender, new HealthGui(player));
        }

        else {
            sender.sendMessage(Colors.ERROR + "Usage: /health <player> <health | saturation | lastdamage | see>");
            return false;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 2) {
            List<String> argus = new ArrayList<>();
            argus.add("health");
            argus.add("saturation");
            argus.add("lastdamage");
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
