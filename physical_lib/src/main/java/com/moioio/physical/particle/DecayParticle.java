package com.moioio.physical.particle;

/**
 * 衰减粒子
 */
public class DecayParticle extends Particle {



    public DecayParticle() {

    }

    public void setPeriod(int period) {
        this.period = period;
    }


    public void runPeriod() {
        if(this.period>0)
        {
            this.period--;
        }
    }

    public  boolean isDead()
    {
        return period<=0?true:false;
    }

}
