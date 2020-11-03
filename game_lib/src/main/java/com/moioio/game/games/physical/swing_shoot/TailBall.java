package com.moioio.game.games.physical.swing_shoot;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.Ball;
import com.moioio.game.games.physical.common.particle.DecayParticle;
import com.moioio.util.MyLog;

public class TailBall extends Ball {

    float angle;


    DecayParticle decayParticle;

    public TailBall()
    {
        decayParticle = new DecayParticle();

    }


    @Override
    public void draw(Graphics g) {

        if(!decayParticle.isDead())
        {
            float rate = (decayParticle.getPeriod()/decayParticle.getPeriodMax());
            int alpha = (int) (255f*rate);
            int nc = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
            g.setColor(nc);
            float r = radius*rate;
            g.fillCircle(x,y,r);
        }
    }


    @Override
    public void logic() {
        decayParticle.runPeriod();
        if(!decayParticle.isDead())
        {
            float step = decayParticle.getPeriodMax()-decayParticle.getPeriod();

            double radians = Math.toRadians(angle);
            x = ox+(float)(step*Math.cos(radians));
            y = oy+(float)(step*Math.sin(radians));
        }
    }

    void setPeriod(float period)
    {
        decayParticle.setPeriod(period);
    }

    public void setPeriodRate(float rate) {
        decayParticle.setPeriodRate(rate);
    }


    public boolean isDead()
    {
        return decayParticle.isDead();
    }
}
