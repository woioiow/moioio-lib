package com.moioio.game.games.physical.common.particle;

/**
 * 循环粒子
 */
public class LoopParticle extends Particle {



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



}
