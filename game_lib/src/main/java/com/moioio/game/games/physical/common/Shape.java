package com.moioio.game.games.physical.common;

import com.moioio.android.g2d.Graphics;

import java.util.List;

public abstract class Shape {

    public float x;
    public float y;
    public float ox;
    public float oy;
    public int color;
    public float alpha;

    protected abstract void draw(Graphics g);
    protected abstract void logic();


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.ox = x;
        this.oy = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }


    public static void drawShapes(List<?> list,Graphics g)
    {
        if(list==null)
        {
            return;
        }
        for(Object o:list)
        {
            if(o instanceof Shape)
            {
                ((Shape) o).draw(g);
            }

        }
    }

    public static void logicShapes(List<?> list)
    {
        if(list==null)
        {
            return;
        }
        for(Object o:list)
        {
            if(o instanceof Shape)
            {
                ((Shape) o).logic();
            }

        }
    }

    public  boolean isDead() {
        return false;
    }
}
