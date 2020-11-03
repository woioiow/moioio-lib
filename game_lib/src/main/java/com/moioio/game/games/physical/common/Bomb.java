package com.moioio.game.games.physical.common;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class Bomb {


    private List<BombFireParticle> particles;
    private List<BombFireParticle> rlist;

    public Bomb()
    {
        particles = new ArrayList<>();
        rlist = new ArrayList<>();
    }


    public void draw(Graphics g)
    {
        run();
        for(BombFireParticle particle:particles)
        {
            particle.draw(g);
        }
    }


    private void run()
    {
        for(BombFireParticle particle:particles)
        {
            if(particle.isDead())
            {
                rlist.add(particle);
            }
        }

        for(BombFireParticle particle:rlist)
        {
            particles.remove(particle);
        }

        rlist.clear();
    }




    public void makeBomb(float x,float y)
    {
        particles.clear();
        rlist.clear();
        int size = 10+ RandomUtil.getRandom(10);
        int num = (int) (360f/15f);
        for(int i=0;i<size;i++)
        {

            int color = Color.WHITE;


            if(colors!=null)
            {
                color = colors[RandomUtil.getRandom(colors.length)];
            }

            float angle = RandomUtil.getRandom(num)*15f;
            float radius = RandomUtil.getRandom(5)+1;
            int period = RandomUtil.getRandom(200)+100;
            BombFireParticle data = new BombFireParticle(x,y,angle,radius, color);
            data.setPeriod(period);
            particles.add(data);
        }
    }

    public boolean isFinish()
    {
        return particles.size()==0?true:false;

    }


    private int[] colors;
    public void setColor(int... colors) {
        this.colors = colors;
    }
}
