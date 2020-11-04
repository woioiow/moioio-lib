package com.moioio.game.games.physical.common.shape;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.particle.SwingParticle;

public class SwingBall extends Ball {


    public SwingParticle swingParticle;

    public SwingBall()
    {
        swingParticle = new SwingParticle();
    }



    public void draw(Graphics g)
    {
        int newColor = Color.argb((int)(alpha*255), Color.red(color), Color.green(color), Color.blue(color));
        g.setColor(newColor);
        g.fillCircle(x, y, radius);
    }

    @Override
    public void logic() {

        swingParticle.runPeriod();
        if(swingParticle.isOnChange())
        {
            if(swingParticle.isSwingBack())
            {
                makeSpeedXY(-ospeed);
                setAlphaRate(-oalpha/swingParticle.getPeriodMax());
                setRadiusRate(-oradius/swingParticle.getPeriodMax());
            }
            else
            {
                makeSpeedXY(ospeed);
                setAlphaRate(oalpha/swingParticle.getPeriodMax());
                setRadiusRate(oradius/swingParticle.getPeriodMax());
            }
        }
        move();
        changeAlpha();
        changeRadius();
    }




    @Override
    public boolean isDead() {
        return false;
    }


    public void setPeriodRate(float periodRate) {
        swingParticle.setPeriodRate(periodRate);
    }

    public void setPeriodMax(float periodMax) {
        swingParticle.setPeriodMax(periodMax);
    }

    private float ospeed;
    private float oalpha;
    private float oradius;
    public void build() {

        this.ospeed = speed;
        this.oalpha = alpha;
        this.oradius = radius;

        makeSpeedXY(ospeed);
        setAlphaRate(oalpha/swingParticle.getPeriodMax());
        setRadiusRate(oradius/swingParticle.getPeriodMax());
    }
}
