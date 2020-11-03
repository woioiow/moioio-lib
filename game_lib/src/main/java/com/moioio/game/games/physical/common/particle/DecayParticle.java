package com.moioio.game.games.physical.common.particle;

/**
 * 衰减粒子
 */
public class DecayParticle extends Particle {


    public void runPeriod() {
        if(this.period>0)
        {
            this.period -= this.periodRate;
        }
    }


    public  boolean isDead()
    {
        return period<=0?true:false;
    }

}
