package com.moioio.game.games.physical.landing_ball;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.game.games.physical.common.shape.Track;
import com.moioio.util.PhysicalUtil;
import com.moioio.util.RandomUtil;

class Sky extends Shape {

    PlanetBall landBall;
    PlanetBall aimBall;
    float shopRadius ;
    float ballRadius ;

    Track track = new Track();


    @Override
    public void draw(Graphics g) {
        track.draw(g);
    }

    @Override
    public void logic() {
        track.logic();
    }

    public void build() {
        landBall = new PlanetBall();
        aimBall = new PlanetBall();


        shopRadius = width/60+10;
        ballRadius = width/15;

        float ww = width/2-(shopRadius+ballRadius)*2;
        float hh = height-(shopRadius+ballRadius)*2;

        landBall.setColor(Color.WHITE);
        landBall.setRadius(ballRadius);
        landBall.setOutRadius(ballRadius+shopRadius);

        float xx = x+(width/2-ww)/2;
        float yy = y+(height-hh)/2;

        landBall.setPosition(xx+ RandomUtil.getRandom((int) ww),yy+RandomUtil.getRandom((int) hh));

        track.add(landBall);
        track.add(aimBall);
        makeAimBall();

    }


    private void makeAimBall()
    {


        aimBall.setColor(0xFF00D193);
        aimBall.setRadius(ballRadius);
        aimBall.setOutRadius(ballRadius+shopRadius);


        float ww = width/2-(shopRadius+ballRadius)*2;
        float hh = height-(shopRadius+ballRadius)*2;
        aimBall.setColor(0xFF00D193);
        if(landBall.x<width/2)
        {
            float xx = x+width/2+(width/2-ww)/2;
            float yy = y+(height-hh)/2;
            aimBall.setPosition(xx+ RandomUtil.getRandom((int) ww),yy+RandomUtil.getRandom((int) hh));
        }
        else
        {
            float xx = x+(width/2-ww)/2;
            float yy = y+(height-hh)/2;

            aimBall.setPosition(xx+ RandomUtil.getRandom((int) ww),yy+RandomUtil.getRandom((int) hh));
        }


        aimBall.revive();
    }

    public boolean collisionLogic(ShopBall ball) {
        boolean ok = false;
        float distance = PhysicalUtil.getDistance(aimBall.x,aimBall.y,ball.x,ball.y);
        if(distance<=(aimBall.outRadius+ball.radius))
        {
            ok = true;
        }
        return ok;

    }

    public void changeAim() {
        PlanetBall tmp = landBall;
        landBall = aimBall;
        aimBall = tmp;
        landBall.setColor(Color.WHITE);
        makeAimBall();
    }

    public void leaveLand() {
        landBall.dead();
    }
}
