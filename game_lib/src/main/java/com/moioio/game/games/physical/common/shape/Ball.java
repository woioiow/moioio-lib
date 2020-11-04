package com.moioio.game.games.physical.common.shape;

import com.moioio.util.PhysicalUtil;

public abstract class Ball extends Shape {

    public float radius;
    public float radiusRate;


    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setRadiusRate(float radiusRate) {
        this.radiusRate = radiusRate;
    }


    public void changeRadius()
    {
        radius += radiusRate;
        if(radius<=0)
        {
            radius = 0;
        }
    }

    public boolean isAreaRect()
    {
        boolean ok = PhysicalUtil.pointInRect(orX+radius,orY+radius,orW-2*radius,orH-2*radius,x,y);
        return ok;
    }

    public boolean collision(Ball ball)
    {
        boolean ok = false;
        float distance = PhysicalUtil.getDistance(x,y,ball.x,ball.y);
        if(distance<=(radius+ball.radius))
        {
            ok = true;
        }
        return ok;
    }



}
