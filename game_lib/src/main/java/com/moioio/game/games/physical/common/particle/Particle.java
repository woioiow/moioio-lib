package com.moioio.game.games.physical.common.particle;

public abstract class Particle {

    protected float periodRate = 1;

    protected float period;

    protected float periodMax;


    public abstract void runPeriod();

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
        this.periodMax = period;
    }



    public abstract boolean isDead();


    public float getPeriodRate() {
        return periodRate;
    }

    public void setPeriodRate(float periodRate) {
        this.periodRate = periodRate;
    }

    public void setPeriodMax(float period) {
        this.periodMax = period;
    }
    public float getPeriodMax() {
        return periodMax;
    }

}
