package com.moioio.game.games.physical.landing_ball;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;

public class PlanetBall extends Ball {


    final static int AWAIT = 0;
    final static int FLYING = 1;
    final static int OVER = 3;
    int status;
    int step = 20;

    float outRadius;

    float stepRadius;
    float stepOutRadius;

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillCircle(x,y,radius);
        int alpha = Color.alpha(color);
        int newColor = Color.argb(alpha*3/10,Color.red(color),Color.green(color),Color.blue(color));
        g.setColor(newColor);
        g.drawCircle(x,y,outRadius);
    }

    @Override
    public void logic() {
        if(step>0)
        {
            outRadius += stepOutRadius;
            radius += stepRadius;
            step--;
        }
    }

    public void setOutRadius(float r) {
        this.outRadius = r;
    }

    public void dead() {

        step = 20;
        stepRadius = -radius/step;
        stepOutRadius = -outRadius/step;
    }

    public void revive() {
        step = 20;
        stepRadius = radius/step;
        stepOutRadius = outRadius/step;

        radius = 0;
        outRadius = 0;
    }


}
