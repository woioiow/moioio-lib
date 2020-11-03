package com.moioio.game.games.physical.common.particle;

/**
 * 摇摆粒子
 */
public class SwingParticle extends LoopParticle{


    private boolean isBack;

    @Override
    public void runPeriod() {
        if(isBack)
        {
            this.period -= periodRate;
            if(this.period<=0)
            {
                this.period = 0;
                isBack = false;
            }
        }
        else
        {
            this.period += periodRate;
            if(period>= periodMax)
            {
                this.period = periodMax;
                isBack = true;
            }
        }
    }

    @Override
    public boolean isDead() {
        return false;
    }
}
