package com.moioio.game.games.physical.common;

public abstract class Particle {

    float periodRate = 1;

    float period;

    public abstract void runPeriod();

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    public abstract boolean isDead();


    public float getPeriodRate() {
        return periodRate;
    }

    public void setPeriodRate(float periodRate) {
        this.periodRate = periodRate;
    }
}
