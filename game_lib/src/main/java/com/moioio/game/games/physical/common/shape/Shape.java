package com.moioio.game.games.physical.common.shape;

import com.moioio.android.g2d.Graphics;
import com.moioio.util.PhysicalUtil;

import java.util.List;

public abstract class Shape {

    public float x;
    public float y;

    public float width;
    public float height;

    public int color;


    public float alpha;
    public float alphaRate;

    public float angle;
    public float angleRate;


    public float speed;
    public float speedX;
    public float speedY;

    public float orX;
    public float orW;
    public float orY;
    public float orH;




    public abstract void draw(Graphics g);
    public abstract void logic();


    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
//        this.ox = x;
//        this.oy = y;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }


    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public static void drawShapes(List<?> list, Graphics g)
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


    public void makeSpeedXY()
    {
        makeSpeedXY(speed);
    }

    public void makeSpeedXY(float speed)
    {
        double radians = Math.toRadians(angle);
        speedX = (float)(speed*Math.cos(radians));
        speedY = (float)(speed*Math.sin(radians));
    }


    public void move()
    {
        x += speedX;
        y += speedY;
    }

    public void changeAlpha()
    {
        alpha += alphaRate;
        if(alpha<=0)
        {
            alpha = 0;
        }
        else if(alpha>=1)
        {
            alpha = 1;
        }
    }

    public void changeAngle()
    {
        angle += angleRate;
        if(angle<0)
        {
            angle = 360;
        }
        else if(angle>360)
        {
            angle = 360;
        }
    }


    public void setAlphaRate(float alphaRate) {
        this.alphaRate = alphaRate;
    }

    public void setAngleRate(float angleRate) {
        this.angleRate = angleRate;
    }

    public void changeAngle(float angle) {
        this.angle = angle;
        makeSpeedXY();
    }
    public void changeSpeed(float speed) {
        this.speed = speed;
        makeSpeedXY();
    }



    public void setAreaRect(float x, float y, float w, float h) {
        orX = x;
        orW = w;
        orY = y;
        orH = h;
    }

    public void setAreaRectByPos(float lx, float ty, float rx, float by) {
        orX = lx;
        orW = rx-lx;
        orY = ty;
        orH = by-ty;
    }

    public boolean isAreaRect()
    {
        boolean ok = PhysicalUtil.pointInRect(orX,orY,orW,orH,x,y);
        return ok;
    }

    public void setRectSize(float w,float h)
    {
        this.width = w;
        this.height = h;

    }
}
