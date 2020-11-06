package com.moioio.game.games.physical.common.shape;

import com.moioio.util.PhysicalUtil;

public class TailBallTrack extends Track {


    public void addTail(float x,float y,int color,float radius,float angle,float speed,float max,float rate)
    {

        DecayBall data = new DecayBall();
        data.setAlpha(1);
        data.setColor(color);
        data.setRadius(radius);
        data.setSpeed(speed);
        data.angle = angle;//PhysicalUtil.getAntiAngle(angle);
        data.setPeriodMax(max);
        data.setPeriodRate(rate);


        double radians = Math.toRadians(data.angle);
        float xx = x+(float)(radius*Math.cos(radians));
        float yy = y+(float)(radius*Math.sin(radians));

        data.setPosition(xx,yy);

        data.build();

        add(data);
    }


    public void addTail(Ball ball)
    {
        float x = ball.x;
        float y = ball.y;


        DecayBall data = new DecayBall();
        data.setAlpha(1);
        data.setColor(ball.color);
        data.setRadius(ball.radius/3);
        data.angle = PhysicalUtil.getAntiAngle(ball.angle);
        data.setPeriodMax(10);
        data.setPeriodRate(1);


        double radians = Math.toRadians(data.angle);
        float xx = x+(float)(ball.radius*Math.cos(radians));
        float yy = y+(float)(ball.radius*Math.sin(radians));

        data.setPosition(xx,yy);

        data.build();

        add(data);
    }

}
