package com.moioio.game.games.physical.common.particle;

/**
 * 摇摆粒子
 */
public class SwingParticle extends LoopParticle{


    private boolean isBack;
    private boolean isOnChange = true;

    @Override
    public void runPeriod() {
        isOnChange = false;
        if(isBack)
        {
            this.period -= periodRate;
            if(this.period<=0)
            {
                this.period = 0;
                isBack = false;
                isOnChange = true;
            }
        }
        else
        {
            this.period += periodRate;
            if(period>= periodMax)
            {
                this.period = periodMax;
                isBack = true;
                isOnChange = true;
            }
        }
    }

    @Override
    public boolean isDead() {
        return false;
    }


    public boolean isSwingBack()
    {
        return isBack;
    }

    public boolean isOnChange()
    {
        return isOnChange;
    }
}
