package com.moioio.physical.particle;

/**
 * 循环粒子
 */
public class LoopParticle extends Particle {

    float periodMax;

   public void setPeriodMax(float period) {
        this.periodMax = period;
    }


    public void runPeriod() {
        this.period += periodRate;
        if(period> periodMax)
        {
            this.period = 0;
        }
    }

    public boolean isDead()
    {
        return false;
    }

    public float getPeriodMax() {
        return periodMax;
    }


}
