package com.moioio.game.games.physical.common.shape;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.util.MyLog;
import com.moioio.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class Bomb {


    private List<DecayBall> particles;
    private List<DecayBall> rlist;
    private List<Integer> colors;

    public Bomb()
    {
        particles = new ArrayList<>();
        rlist = new ArrayList<>();
        colors = new ArrayList<>();
        colors.add(Color.WHITE);
    }


    public void draw(Graphics g)
    {
        run();
        for(DecayBall particle:particles)
        {
            particle.logic();
            particle.draw(g);
        }
    }


    private void run()
    {
        for(DecayBall particle:particles)
        {
            if(particle.isDead())
            {
                rlist.add(particle);
            }
        }

        for(DecayBall particle:rlist)
        {
            particles.remove(particle);
        }

        rlist.clear();
    }

    public void addBomb(float x,float y)
    {
        int size = 15+ RandomUtil.getRandom(20);
        int num = (int) (360f/15f);
        for(int i=0;i<size;i++)
        {

            int index = RandomUtil.getRandom(colors.size());

            int color = colors.get(index);

            float angle = RandomUtil.getRandom(num)*15f;
            float radius = RandomUtil.getRandom(5)+1;

            DecayBall data = new DecayBall();

            data.setPosition(x,y);
            data.setAngle(angle);
            data.setRadius(radius);
            data.setColor(color);
            data.setSpeed(10+RandomUtil.getRandom(20));
            data.setAlpha((0.5f+RandomUtil.getRandom(6)*0.1f));
            data.setPeriodRate(1);
            data.setPeriodMax(RandomUtil.getRandom(20)+20);
            data.build();

            particles.add(data);
        }
    }


    public void makeBomb(float x,float y)
    {
        particles.clear();
        rlist.clear();
        addBomb(x,y);
    }

    public boolean isDead()
    {
        return particles.size()==0?true:false;
    }

    public void setColor(int color) {
        colors.clear();
        colors.add(color);
    }

    public void setColors(int... cs) {
        for(int color:cs)
        {
            colors.add(color);
        }
    }
}
