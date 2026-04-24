package me.titanet.kmdangelo.tPDoubleJump.flyTask;

import me.titanet.kmdangelo.tPDoubleJump.TPDoubleJump;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.*;
import java.util.UUID;

public class TempFlyTask extends BukkitRunnable {
    private UUID uuid;
    private long timeInMillis;
    private Player player;
    private TPDoubleJump plugin;

    public TempFlyTask(long timeInSeconds, UUID uuid, TPDoubleJump plugin){
        timeInMillis = System.currentTimeMillis() + timeInSeconds * 1000L;
        this.uuid = uuid;
        player = Bukkit.getPlayer(uuid);
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
        }
        if (!player.isFlying()) {
            timeInMillis += 1000L;
        }
        if (timeInMillis <= System.currentTimeMillis()) {
            player.sendMessage("La tua fly è terminata!");
            player.setAllowFlight(false);
            plugin.getTempFlyMap().remove(uuid);
            this.cancel();
        }

        if (player.isFlying()) {
            long remainedTimeInSeconds = (timeInMillis - System.currentTimeMillis())/1000;
            int hours = Math.toIntExact(remainedTimeInSeconds / 3600);
            int minutes = Math.toIntExact(remainedTimeInSeconds % 3600 / 60);
            int seconds = Math.toIntExact(remainedTimeInSeconds % 3600 % 60);

            String rawTimeMessage = ChatColor.translateAlternateColorCodes('&',"&4&lVolo rimanente: &2 " + hours + "&2h " + minutes + "&2m " + seconds + "&2s");
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(rawTimeMessage));
        }

    }

    public UUID getUserUUID() {
        return uuid;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }
}
