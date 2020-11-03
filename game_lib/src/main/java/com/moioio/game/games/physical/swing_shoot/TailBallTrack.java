package com.moioio.game.games.physical.swing_shoot;

import com.moioio.game.games.physical.common.Track;

public class TailBallTrack extends Track {




    void addTail(ShootBall shootBall)
    {
        float x = shootBall.x;
        float y = shootBall.y;
        TailBall data = new TailBall();
        data.setAlpha(1);
        data.setColor(shootBall.color);
        data.setRadius(shootBall.radius/3);
        data.angle = (180+shootBall.angle)%360;
        data.setPeriod(10);
        data.setPeriodRate(1);


        double radians = Math.toRadians(data.angle);
        float xx = x+(float)(shootBall.radius*Math.cos(radians));
        float yy = y+(float)(shootBall.radius*Math.sin(radians));

        data.setPosition(xx,yy);


        add(data);
    }

}
