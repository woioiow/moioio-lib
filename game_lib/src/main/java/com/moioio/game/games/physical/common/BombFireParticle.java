package com.moioio.game.games.physical.common;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.util.RandomUtil;

public class BombFireParticle extends Particle {

    private float total;

    private float x;
    private float y;
    private float ox;
    private float oy;


    private float angle;

    private float radius;
    private float alpha;
    private int color;

    private float oradius;
    private float oalpha;

    private int step;
    private float v;
    private float a;



    public BombFireParticle(float x,float y,float angle,float radius,int color)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;

        this.ox = x;
        this.oy = y;
        this.oradius = radius;

        this.angle = angle;
        this.color = color;

        this.oalpha = (0.5f+RandomUtil.getRandom(6)*0.1f);
        this.alpha = oalpha;
    }


    public void draw(Graphics g)
    {
        runPeriod();
        if(!isDead())
        {
            int newColor = Color.argb((int)(alpha*255), Color.red(color), Color.green(color), Color.blue(color));
            g.setColor(newColor);
            g.fillCircle(x, y, radius);
            g.setColor(Color.BLACK);
        }
    }


    @Override
    public void runPeriod() {
        if(this.period>0)
        {
            this.step++;
            float s = v*step;//v*step+((a*step)*(a*step))/2;

            this.period = total-s;//(v-a*step)*step;


//            float xorPeriod = total-period;
//            x = ox+(vx+ax*time)*time;
//            y = oy+(vy+ay*time)*time;
//
            radius = oradius*(period/total);
            alpha = oalpha*(period/total);


            double radians = Math.toRadians(angle);
            x = ox+(float)(s*Math.cos(radians));
            y = oy+(float)(s*Math.sin(radians));
        }



    }


    public void setPeriod(float period) {
        this.period = period;
        this.total = period;
        this.v = 10+RandomUtil.getRandom(20);
//        this.a = (RandomUtil.getRandom(10)+1);
        this.step = 0;
    }



    @Override
    public boolean isDead() {
        return period<=0?true:false;
    }




}
