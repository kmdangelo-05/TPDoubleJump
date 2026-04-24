package me.titanet.kmdangelo.tPDoubleJump.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlyCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo i player possono usare questo comando");
            return true;
        }
        if (!sender.hasPermission("TPDouble.command.fly")){
            sender.sendMessage("Non hai il permesso di eseguire questo comando");
            return true;
        }

        if (player.getAllowFlight() == false) {
            player.setAllowFlight(true);
            sender.sendMessage("Volo attivato!");
        }else{
            player.setAllowFlight(false);
            sender.sendMessage("Volo disabilitato!");
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
