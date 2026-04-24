package me.titanet.kmdangelo.tPDoubleJump.command;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeeCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Solo i giocatori possono inviare questo comando");
            return true;
        }

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("reset")) {

                if (!(sender.hasPermission("TPDouble.command.reset"))) {
                    sender.sendMessage("Non hai il permesso per eseguire il comando");
                    return true;
                }

                if (args.length== 2) {
                    if (args[1].equalsIgnoreCase("confirm")) {
                        sender.sendMessage("Config resettata");
                        return true;
                    }
                }

                sender.sendMessage("Sei sicuro di resettare la config? fare /seeconfig reset confirm per confermare");
                return true;
            }
            if (args[0].equalsIgnoreCase("see")) {

            }
        } else {
            sender.sendMessage("Comando non valido! Uso corretto /seeconfig reset|see");
        }
        //seeConfig reset e see

        //reset -> scrivi /seeconfig reset confirm
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        switch (args.length) {
            case 1:
                return List.of("reset", "add");
            case 2:
                if (args[0].equalsIgnoreCase("reset")){
                    return List.of("confirm");
                }
        }
        return List.of();
    }
}
