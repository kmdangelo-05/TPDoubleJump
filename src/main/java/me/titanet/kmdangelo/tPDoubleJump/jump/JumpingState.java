package me.titanet.kmdangelo.tPDoubleJump.jump;

import java.util.UUID;

public class JumpingState {
    UUID player;
    boolean stopinAir;

    public JumpingState(UUID player) {
        this.player = player;
        stopinAir = false;
    }

    public boolean isStopInAir() {
        return stopinAir;
    }

    public void setStopInAir(boolean inAir) {
        this.stopinAir = inAir;
    }

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }
}
