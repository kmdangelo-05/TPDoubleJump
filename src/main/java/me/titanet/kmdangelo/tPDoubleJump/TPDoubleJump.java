package me.titanet.kmdangelo.tPDoubleJump;

import me.titanet.kmdangelo.tPDoubleJump.command.FlyCommand;
import me.titanet.kmdangelo.tPDoubleJump.command.SeeCommand;
import me.titanet.kmdangelo.tPDoubleJump.command.TempFlyCommand;
import me.titanet.kmdangelo.tPDoubleJump.flyTask.TempFlyTask;
import me.titanet.kmdangelo.tPDoubleJump.jump.JumpingState;
import me.titanet.kmdangelo.tPDoubleJump.listener.PlayerJumpListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TPDoubleJump extends JavaPlugin {

    Map<UUID, JumpingState> jumpingPlayers = new HashMap<>();
    Map<UUID, TempFlyTask> tempFlyTaskMap = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic


        SeeCommand seeCommand = new SeeCommand();
        FlyCommand flyCommand = new FlyCommand();
        TempFlyCommand tempFlyCommand = new TempFlyCommand(this);

        getCommand("seeConfig").setExecutor(seeCommand);
        getCommand("seeConfig").setTabCompleter(seeCommand);

        getCommand("fly").setExecutor(flyCommand);
        getCommand("fly").setTabCompleter(flyCommand);

        getCommand("tempFly").setExecutor(tempFlyCommand);
        getCommand("tempFly").setTabCompleter(tempFlyCommand);

        getServer().getPluginManager().registerEvents(new PlayerJumpListener(this), this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Map<UUID, JumpingState> getJumpingPlayers() {
        return jumpingPlayers;
    }

    public Map<UUID, TempFlyTask> getTempFlyMap() {
        return tempFlyTaskMap;
    }

}
