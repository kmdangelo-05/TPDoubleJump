package me.titanet.kmdangelo.tPDoubleJump.command;

import me.titanet.kmdangelo.tPDoubleJump.TPDoubleJump;
import me.titanet.kmdangelo.tPDoubleJump.flyTask.TempFlyTask;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class TempFlyCommand implements TabExecutor {

    private TPDoubleJump plugin;

    public TempFlyCommand(TPDoubleJump plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Il comando è eseguibile solo da un player");
            return true;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission("TPDouble.command.tempfly")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&4&lNon hai il permesso di eseguire quel comando!"));
        }

        if (args.length>=1) {

            switch (args[0]) {
                case "time":

                    if (plugin.getTempFlyMap().get(player.getUniqueId()) == null) {
                        sender.sendMessage("Non hai tempo di fly a disposizione!");
                        return true;
                    }

                    long time = plugin.getTempFlyMap().get(player.getUniqueId()).getTimeInMillis();
                    long remainedTimeInSeconds = (time-System.currentTimeMillis())/1000;
                    int hours = Math.toIntExact(remainedTimeInSeconds / 3600);
                    int minutes = Math.toIntExact(remainedTimeInSeconds % 3600 / 60);
                    int seconds = Math.toIntExact(remainedTimeInSeconds % 3600 % 60);
                    sender.sendMessage("Ti rimangono " + hours + "h, "+ minutes +"m, " + seconds + "s di fly");
                    return true;

                case "set":
                    if (plugin.getTempFlyMap().get(player.getUniqueId()) != null) {
                        plugin.getTempFlyMap().get(player.getUniqueId()).cancel();
                        plugin.getTempFlyMap().remove(player.getUniqueId());
                    }
                    if (args.length==2) {
                        long writtenTime;
                        try{
                            writtenTime = Long.parseLong(args[1]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage("Valore inserito non valido, inserire un numero");
                            return true;
                        }

                        TempFlyTask tft = new TempFlyTask(writtenTime,player.getUniqueId(), plugin);
                        plugin.getTempFlyMap().putIfAbsent(player.getUniqueId(),tft);
                        tft.runTaskTimer(plugin,0L,20L);
                        sender.sendMessage("Hai impostato con successo " + args[1] + " secondi di fly");
                        return true;
                    } else {
                        sender.sendMessage("Inserire un valore numerico di secondi di fly");
                        return true;
                    }

                default: sender.sendMessage("Comando non valido");
                    return true;
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        switch (args.length) {
            case 0: return List.of("set", "time");
            case 1:
                if (args[0].equals("set ")) {
                    return List.of("<flyTime>");
                }
            default: return List.of();
        }
    }
}
