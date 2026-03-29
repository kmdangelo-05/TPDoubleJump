package me.titanet.kmdangelo.tPDoubleJump.listener;

import me.titanet.kmdangelo.tPDoubleJump.TPDoubleJump;
import me.titanet.kmdangelo.tPDoubleJump.jump.JumpingState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.Tag;
import org.bukkit.block.BlockType;
import org.bukkit.block.data.type.Slab;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class PlayerJumpListener implements Listener {
    TPDoubleJump plugin;

    public PlayerJumpListener(TPDoubleJump plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerJumpEvent(PlayerMoveEvent e) {
        Location underBlock = e.getFrom().clone().subtract(0,0.1,0);
        Material underBlockMaterial = underBlock.getBlock().getType();

        if (!(Tag.AIR.isTagged(underBlockMaterial))) {
            plugin.getJumpingPlayers().remove(e.getPlayer().getUniqueId());
        }

        if (!(e.getFrom().getBlock().getY() <= e.getTo().getBlock().getY())) return;

        if (Tag.AIR.isTagged(underBlockMaterial)) {
            plugin.getJumpingPlayers().put(e.getPlayer().getUniqueId(), new JumpingState(e.getPlayer().getUniqueId()));
        }

        if (Tag.AIR.isTagged(underBlockMaterial) && e.getPlayer().isSneaking()) {
            plugin.getJumpingPlayers().put(e.getPlayer().getUniqueId(), new JumpingState(e.getPlayer().getUniqueId()));

        }

    }

    @EventHandler
    public void ShiftClickEvent(PlayerToggleSneakEvent e) {
        Vector velocity = e.getPlayer().getLocation().getDirection().normalize().multiply(.2);
        if (plugin.getJumpingPlayers().get(e.getPlayer().getUniqueId()) != null) {
            if (e.isSneaking()) {
                plugin.getJumpingPlayers().get(e.getPlayer().getUniqueId()).setStopInAir(true);
            } else {
                plugin.getJumpingPlayers().get(e.getPlayer().getUniqueId()).setStopInAir(false);
            }
            if (!plugin.getJumpingPlayers().get(e.getPlayer().getUniqueId()).isStopInAir()) {
                velocity.setY(.5);
                e.getPlayer().setVelocity(velocity);
            }
        }
    }

    @EventHandler
    public void TryFlyEvent(PlayerToggleFlightEvent e) {
        e.getPlayer().sendMessage("ciao");
    }
}
