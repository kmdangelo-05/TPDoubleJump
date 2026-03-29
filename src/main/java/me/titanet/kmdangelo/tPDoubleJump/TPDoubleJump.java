package me.titanet.kmdangelo.tPDoubleJump;

import me.titanet.kmdangelo.tPDoubleJump.jump.JumpingState;
import me.titanet.kmdangelo.tPDoubleJump.listener.PlayerJumpListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TPDoubleJump extends JavaPlugin {

    Map<UUID, JumpingState> jumpingPlayers = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new PlayerJumpListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Map<UUID, JumpingState> getJumpingPlayers() {
        return jumpingPlayers;
    }
}
