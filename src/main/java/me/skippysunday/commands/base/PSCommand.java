package me.skippysunday.commands.base;

import me.skippysunday.playerstats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public abstract class PSCommand implements CommandExecutor, TabCompleter {

    public static void registerCommands(PlayerStats ps, PSCommand... commands) {
        for(PSCommand command : commands) {
            ps.getCommand(command.getName()).setExecutor(command);
            if(command.hasCompleter()) ps.getCommand(command.getName()).setTabCompleter(command);
        }
    }

    public abstract String getName();

    public abstract boolean hasCompleter();

    public boolean checkUser(CommandSender sender, String perm, String[] args, String usage) {

        if(!sender.hasPermission(perm)) {
            sender.sendMessage(error + "Sorry, but you dont have permission!");
            return false;
        }

        else if(args.length == 0) {
            sender.sendMessage(error + "Usage: " + usage);
            return false;
        }

        else if(Bukkit.getPlayerExact(args[0]) == null) {
            sender.sendMessage(playerName + args[0] + error + " is currently not online!");
            return false;
        }

        return true;
    }

    protected ChatColor base = ChatColor.BLUE;
    protected ChatColor playerName = ChatColor.DARK_AQUA;
    protected ChatColor stat = ChatColor.GOLD;
    protected ChatColor error = ChatColor.RED;
}
