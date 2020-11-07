
package com.moioio.game.games.physical.swing_shoot;

import android.graphics.Paint;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.SwingBall;
import com.moioio.game.games.physical.common.shape.TailBallTrack;
import com.moioio.util.PhysicalUtil;


class ShootBall extends Ball {

    final static int AWAIT = 0;
    final static int SHOOT = 1;
    final static int BACK = 2;
    final static int OVER = 3;
    int status;

    SwingBall awaitBg;
    TailBallTrack tailBallTrack;
    Bomb bomb;

    SwingPointer swingPointer;

    float minAngle;
    float maxAngle;


    public ShootBall()
    {
        bomb = new Bomb();
        tailBallTrack = new TailBallTrack();
        awaitBg = new SwingBall();
        swingPointer = new SwingPointer();
    }

    @Override
    public void draw(Graphics g) {
        if(status==AWAIT)
        {
            awaitBg.draw(g);
            drawBall(g);
            swingPointer.draw(g);
        }
        else if(status==SHOOT)
        {
            tailBallTrack.draw(g);
            drawBall(g);
        }
        else if(status==BACK)
        {
            tailBallTrack.draw(g);
            drawBall(g);
        }
        else if(status==OVER)
        {
            bomb.draw(g);
        }
    }

    @Override
    public void logic() {
        if(status==AWAIT)
        {
            awaitBg.logic();
            swingPointer.logic();
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
        float w = radius*0.5f;
        g.setColor(color);
        Paint paint = g.getPaint();
        float ow = paint.getStrokeWidth();
        paint.setStrokeWidth(w);
        g.drawCircle(x,y,radius-w/2);
        paint.setStrokeWidth(ow);
    }


    void shot() {
        if(this.status==AWAIT)
        {
            this.angle = swingPointer.angle;
            this.status = SHOOT;
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

        if(collision(aimBall))
        {
            gameEngine.setScore(gameEngine.getScore()+1);
            aimBall.dead();
            status = BACK;
            changeAngle(PhysicalUtil.getAntiAngle(angle));
            changeSpeed(3*speedBak);
        }
    }


    private void checkBack()
    {
        if(y>=startY)
        {
            y = startY;
            aimBall.revive();
            changeAngle(PhysicalUtil.getAntiAngle(angle));
            changeSpeed(speedBak);
            build();
        }

    }



    AimBall aimBall;
    void setAimBall(AimBall aimBall) {
        this.aimBall = aimBall;
    }


    private float speedBak;
    public void build() {

        makeSpeedXY();

        status = AWAIT;

        awaitBg.setPosition(x,y);
        awaitBg.setColor(color);
        awaitBg.setAlpha(0.1f);
        awaitBg.setRadius(radius);
        awaitBg.setPeriodMax(20);
        awaitBg.setPeriodRate(1);
        awaitBg.build();

        float max = maxAngle-minAngle;
        float step = max/30;
        swingPointer.setPosition(x,y);
        swingPointer.setColor(color);
        swingPointer.setRadius(radius);
        swingPointer.setPeriodMax(max);
        swingPointer.setPeriodRate(step);
        swingPointer.startAngle = minAngle;
        swingPointer.build();

        speedBak = speed;
    }


    public void setAngleDomain(float minAngle, float maxAngle) {

        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
    }


    private float startY;
    public void setStartY(float y) {
        this.startY = y;
    }
}
