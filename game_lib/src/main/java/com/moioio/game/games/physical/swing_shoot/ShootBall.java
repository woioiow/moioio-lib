package com.moioio.game.games.physical.swing_shoot;

import android.graphics.Color;
import android.graphics.Paint;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.Ball;
import com.moioio.game.games.physical.common.Bomb;
import com.moioio.game.games.physical.common.particle.SwingParticle;
import com.moioio.util.PhysicalUtil;


public class ShootBall extends Ball {

    final static int AWAIT = 0;
    final static int SHOOT = 1;
    final static int OVER = 2;


    int status;
    float angle;
    float moveTotal;
    float speed;

    SwingParticle awaitBg;
    TailBallTrack tailBallTrack;
    Bomb bomb;

    public ShootBall()
    {
        bomb = new Bomb();
        tailBallTrack = new TailBallTrack();
        awaitBg = new SwingParticle();
        awaitBg.setPeriodMax(20);
        awaitBg.setPeriodRate(1);
    }

    @Override
    protected void draw(Graphics g) {
        if(status==AWAIT)
        {
            drawAwait(g);
            drawBall(g);
        }
        else if(status==SHOOT)
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
    protected void logic() {
        if(status==AWAIT)
        {
            awaitBg.runPeriod();
        }
        else if(status==SHOOT)
        {
            ballLogic();
            checkHit();
        }
        else if(status==OVER)
        {
        }
    }

    private int count;
    private void ballLogic()
    {
        count++;
        moveTotal += speed;
        double radians = Math.toRadians(angle);
        x = ox+(float)(moveTotal*Math.cos(radians));
        y = oy+(float)(moveTotal*Math.sin(radians));

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

    private void drawAwait(Graphics g)
    {
        int color = 0xffffffff;
        int alpha = (int) (32f*(awaitBg.getPeriod()/awaitBg.getPeriodMax()));
        int nc = Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
        g.setColor(nc);
        g.fillCircle(x,y,radius+awaitBg.getPeriod());
    }

    public void setSpeed(float speed) {
        this.speed =  speed;
    }


    void shot(float a) {
        if(this.status==AWAIT)
        {
            this.angle = a;
            this.status = SHOOT;
        }
    }


    private void checkHit()
    {
        //I think the ball hit the edge of the screen will funny!!^.^
        if(x<minX)
        {
            angle += 45f;
            x = minX+radius;
            moveTotal = 0;
            setPosition(x,y);
        }
        else if(x>maxX)
        {
            angle -= 45f;
            x = maxX-radius;
            moveTotal = 0;
            setPosition(x,y);
        }
        else if(y<minY||y>maxY)
        {
            bomb.makeBomb(x,y);
            status = OVER;
            swingShootGame.setResult(GameEngine.RESULT_FAIL);
        }
        if(aimBall!=null)
        {
            float distance = PhysicalUtil.getDistance(x,y,aimBall.x,aimBall.y);
            if(distance<=(radius+aimBall.radius))
            {
                bomb.setColor(color,aimBall.color);
                bomb.makeBomb(aimBall.x,aimBall.y);
                status = OVER;
                swingShootGame.shapes.remove(aimBall);
                swingShootGame.setResult(GameEngine.RESULT_SUCCESS);
            }
        }
    }


    float minX;
    float maxX;
    void setXDomain(float min, float max) {
        minX = min;
        maxX = max;
    }

    float minY;
    float maxY;
    void setYDomain(float min, float max) {
        minY = min;
        maxY = max;
    }

    AimBall aimBall;
    void setAimBall(AimBall aimBall) {
        this.aimBall = aimBall;
    }

    SwingShootGame swingShootGame;
    public void setSwingShootGame(SwingShootGame swingShootGame) {
        this.swingShootGame = swingShootGame;
    }
}
