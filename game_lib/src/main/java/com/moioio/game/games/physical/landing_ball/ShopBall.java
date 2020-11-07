package com.moioio.game.games.physical.landing_ball;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.TailBallTrack;
import com.moioio.util.PhysicalUtil;

class ShopBall extends Ball {

    final static int AWAIT = 0;
    final static int FLYING = 1;
    final static int OVER = 3;
    int status;
    int count;

    TailBallTrack tailBallTrack = new TailBallTrack();
    Bomb bomb = new Bomb();


    @Override
    public void draw(Graphics g) {
        if(status!=OVER)
        {
            tailBallTrack.draw(g);
            drawBall(g);
        }
        bomb.draw(g);
    }

    private void drawBall(Graphics g)
    {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }

    @Override
    public void logic() {
        if(status==AWAIT)
        {
            rotate();
            trackLogic(0);
        }
        else if(status==FLYING)
        {
            move();
            checkHit();
            trackLogic(1);
        }
    }

    void trackLogic(int type)
    {
        count++;
        if(count%5==0)
        {
            float na = (angle+270)%360;
            if(type==1)
            {
                na = PhysicalUtil.getAntiAngle(angle);
            }
            tailBallTrack.addTail(x,y,color,radius*0.6f,na,0,15,1);
        }

        tailBallTrack.logic();
    }

    void rotate()
    {
        angle += speed;
        if(angle>360)
        {
            angle = (angle%360);
        }
        else if(angle<0)
        {
            angle = 360+angle%360;
        }

        double radians = Math.toRadians(angle);
        x = centerX+(float)(rotateRadius*Math.cos(radians));
        y = centerY+(float)(rotateRadius*Math.sin(radians));
    }

    private void checkHit()
    {
        if(!isAreaRect())
        {
            bomb.setColor(color);
            bomb.makeBomb(x,y);
            status = OVER;
            gameEngine.setResult(GameEngine.RESULT_COMMON);
        }

        if(sky.collisionLogic(this))
        {
            sky.changeAim();
            speed = -1*speed/2;
            gameEngine.setScore(gameEngine.getScore()+1);
//            angle = PhysicalUtil.getAngle(centerX,centerY,sky.landBall.x,sky.landBall.y);
            angle = PhysicalUtil.getAngle(x,y,sky.landBall.x,sky.landBall.y);
            angle = PhysicalUtil.getAntiAngle(angle);
            setRotateCenter(sky.landBall.x,sky.landBall.y);
            status = AWAIT;
        }
    }


    void fly() {

        if(status==AWAIT)
        {
            status = FLYING;
            speed = Math.abs(speed*2);
            sky.leaveLand();
            makeSpeedXY();
        }
    }





    public void build() {
    }

    Sky sky;
    public void setSky(Sky sky) {
        this.sky = sky;
    }


}
