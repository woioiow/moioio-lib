
package com.moioio.game.games.physical.rotate_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.TailBallTrack;
import com.moioio.util.PhysicalUtil;


class ShootBall extends Ball {

    final static int AWAIT = 0;
    final static int SHOOT = 1;
    final static int BACK = 2;
    final static int OVER = 3;
    int status;

    TailBallTrack tailBallTrack;
    Bomb bomb;




    public ShootBall()
    {
        bomb = new Bomb();
        tailBallTrack = new TailBallTrack();
    }

    @Override
    public void draw(Graphics g) {
        if(status!=OVER)
        {
            tailBallTrack.draw(g);
            drawBall(g);
        }
        bomb.draw(g);
    }

    @Override
    public void logic() {
        if(status==AWAIT)
        {
            rotate();
        }
        else if(status==SHOOT)
        {
            ballLogic();
            checkHit();
        }
        else if(status==BACK)
        {
            ballLogic();
            checkBack();
        }
    }

    private int count;
    private void ballLogic()
    {
        count++;
        move();
        if(count%3==0)
        {
            tailBallTrack.addTail(this);
        }
        tailBallTrack.logic();
    }


    private void drawBall(Graphics g)
    {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }


    void shot() {
        if(this.status==AWAIT)
        {
            this.status = SHOOT;
            aimRing.stopRotate();
            makeSpeedXY();
        }
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

        if(aimRing.collision(this))
        {
            gameEngine.setScore(gameEngine.getScore()+1);
            aimRing.dead();
            status = BACK;
            changeAngle(PhysicalUtil.getAntiAngle(angle));
            changeSpeed(1.5f*speedBak);
        }
    }


    private void checkBack()
    {

        float distance = PhysicalUtil.getDistance(x,y,centerX,centerY);
        if(distance<=rotateRadius)
        {
            changeAngle(PhysicalUtil.getAntiAngle(angle));
            changeSpeed(speedBak);
            build();
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

    private float speedBak;
    public void build() {

        status = AWAIT;
        speedBak = speed;
    }

    void rotate()
    {
        angle+=6;
        if(angle>360)
        {
            angle = 0;
        }
        double radians = Math.toRadians(angle);
        x = centerX+(float)(rotateRadius*Math.cos(radians));
        y = centerY+(float)(rotateRadius*Math.sin(radians));


        count++;
        if(count%5==0)
        {
            float na = (angle+270)%360;
            tailBallTrack.addTail(x,y,color,radius*0.6f,na,15,1);
        }
        tailBallTrack.logic();
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
