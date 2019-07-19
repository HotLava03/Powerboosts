package io.github.hotlava03.powerboosts.commands;

import com.massivecraft.factions.entity.MPlayer;
import io.github.hotlava03.powerboosts.Powerboosts;
import io.github.hotlava03.powerboosts.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.hotlava03.powerboosts.strings.Strings.*;

public class PowerboostsCmd implements CommandExecutor {

    private Powerboosts plugin;

    public PowerboostsCmd(Powerboosts instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // If user is console just send help
        if(!(sender instanceof Player)) {
            sender.sendMessage("[Powerboosts] Only in game users can run this.");
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("powerboosts.use"))) {
            player.sendMessage(errorPrefix + noPerms);
            return true;
        }

        if(args.length == 0) {
            player.sendMessage(successPrefix + powerboostAmt
                    + Util.getPowerboosts(MPlayer.get(player).getFaction()));
            return true;
        }

        if ("reload".equals(args[0])) {
            if(!player.hasPermission("powerboosts.admin")) {
                player.sendMessage(errorPrefix + noPerms);
            }
            plugin.reload();
            player.sendMessage(staffPrefix + reloadSuccess);
        } else {
            player.sendMessage(ChatColor.GOLD + "Powerboosts"
                    + ChatColor.DARK_GRAY + ChatColor.BOLD +" » "
                    + ChatColor.GRAY + "Running powerboosts "
                    + ChatColor.WHITE + "1.0-SNAPSHOT"
                    + ChatColor.GRAY + " by "
                    + ChatColor.WHITE +"HotLava03"
                    + ChatColor.GRAY +"."
            );
        }
        return true;
    }
}
