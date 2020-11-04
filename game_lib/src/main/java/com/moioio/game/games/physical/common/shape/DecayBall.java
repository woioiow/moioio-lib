package com.moioio.game.games.physical.common.shape;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.particle.DecayParticle;

public class DecayBall extends Ball {


    private DecayParticle decayParticle;

    public DecayBall()
    {
        decayParticle = new DecayParticle();
    }

    public void build()
    {
        makeSpeedXY();
        setAlphaRate(-alpha/decayParticle.getPeriodMax());
        setRadiusRate(-radius/decayParticle.getPeriodMax());
    }


    public void draw(Graphics g)
    {
        if(!isDead())
        {
            int newColor = Color.argb((int)(alpha*255), Color.red(color), Color.green(color), Color.blue(color));
            g.setColor(newColor);
            g.fillCircle(x, y, radius);
        }
    }

    @Override
    public void logic() {

        decayParticle.runPeriod();
        if(!isDead())
        {
            move();
            changeAlpha();
            changeRadius();
        }
    }




    @Override
    public boolean isDead() {
        return decayParticle.isDead();
    }


    public void setPeriodRate(float periodRate) {
        decayParticle.setPeriodRate(periodRate);
    }

    public void setPeriodMax(float periodMax) {
        decayParticle.setPeriodMax(periodMax);
    }

}
