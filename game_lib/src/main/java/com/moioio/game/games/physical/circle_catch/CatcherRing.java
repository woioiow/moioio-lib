
package com.moioio.game.games.physical.circle_catch;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.TailBallTrack;
import com.moioio.util.PhysicalUtil;
import com.moioio.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;


class CatcherRing extends Ball {

    final static int AWAIT = 0;
    final static int OVER = 3;



    int status;

    Bomb bomb;




    public CatcherRing()
    {
        bomb = new Bomb();
    }

    @Override
    public void draw(Graphics g) {
        if(status!=OVER)
        {
            drawBall(g);
        }
        bomb.draw(g);
    }

    @Override
    public void logic() {
        if(status!=OVER)
        {
            rotate();
            checkHit();
        }
    }



    private void drawBall(Graphics g)
    {
        if(!topBall.isDead())
        {
            topBall.draw(g);
        }
        if(!bottomBall.isDead())
        {
            bottomBall.draw(g);
        }
    }


    void changeDir() {

        speed = speed*-1;
//        angle += (2*speed);
    }


    private void checkHit()
    {

        int type = aimRing.collisionLogic(topBall,bottomBall);
        if(type>0)
        {
            if(type==2)
            {
                gameEngine.setScore(gameEngine.getScore()+1);
            }
            else
            {
                bomb.setColor(Color.WHITE);
                bomb.makeBomb(topBall.x,topBall.y);
                bomb.makeBomb(bottomBall.x,bottomBall.y);
                aimRing.dead();
                status = OVER;
                gameEngine.setResult(GameEngine.RESULT_COMMON);
            }
        }
    }


    AimRing aimRing;
    void setAimRing(AimRing aimRing) {
        this.aimRing = aimRing;
    }

    GameEngine gameEngine;
    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    CatcherBall topBall;
    CatcherBall bottomBall;

    public void build() {

        status = AWAIT;

        topBall = new CatcherBall();
        topBall.setRadius(radius);
        topBall.setColor(color);

        bottomBall = new CatcherBall();
        bottomBall.setRadius(radius);
        bottomBall.setColor(color);

    }


    void rotate()
    {
        angle += speed;
        if(angle>360)
        {
            angle = 0;
        }
        else if(angle<0)
        {
            angle = 360;
        }

        double radians = Math.toRadians(angle);
        float x1 = centerX+(float)(rotateRadius*Math.cos(radians));
        float y1 = centerY+(float)(rotateRadius*Math.sin(radians));

        radians = Math.toRadians(PhysicalUtil.getAntiAngle(angle));
        float x2 = centerX+(float)(rotateRadius*Math.cos(radians));
        float y2 = centerY+(float)(rotateRadius*Math.sin(radians));


        topBall.setPosition(x1,y1);
        bottomBall.setPosition(x2,y2);
    }


    float centerX;
    float centerY;
    public void setRotateCenter(float x, float y) {
        this.centerX = x;
        this.centerY = y;
    }

    float rotateRadius;
    public void setRotateRadius(float r) {
        this.rotateRadius = r;
    }
}
