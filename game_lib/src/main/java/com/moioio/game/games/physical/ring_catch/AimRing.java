package com.moioio.game.games.physical.ring_catch;

import android.graphics.Paint;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.game.games.physical.common.shape.Track;
import com.moioio.util.RandomUtil;

class AimRing extends Ball {

    final static int OVER = 3;
    int status;


    Track track = new Track();
    Bomb bomb = new Bomb();

    @Override
    public void draw(Graphics g) {
        if(status!=OVER)
        {
            track.draw(g);
        }
        drawRing(g);
        bomb.draw(g);
    }


    private void drawRing(Graphics g)
    {
        float w = radius*0.05f;
        g.setColor(color);
        Paint paint = g.getPaint();
        float ow = paint.getStrokeWidth();
        paint.setStrokeWidth(w);
        g.drawCircle(x,y,radius-w/2);
        paint.setStrokeWidth(ow);
    }

    @Override
    public void logic() {
        if(status!=OVER)
        {
            makeRandomAimBall();
        }
        track.logic();
    }



    void makeRandomAimBall()
    {
        boolean thinking = (RandomUtil.getRandom(15)==5);
        if(thinking)
        {

            float aimAngle = RandomUtil.getRandom(360);

            float angleStep = 20+RandomUtil.getRandom(20);
            float angleEach = (radius-radius*0.05f)/angleStep;


            AimBall ball = new AimBall();
            ball.setColor(color);
            ball.setAngle(aimAngle);
            ball.setPosition(x,y);
            ball.setMaxSize(radius*0.05f);
            ball.setMaxStep(angleStep);
            ball.setSpeed(angleEach);


            if(RandomUtil.getRandom(10)==5)
            {
                ball.isAim = true;
                ball.setColor(0xFF00D193);
            }

            ball.build();
            track.add(ball);

        }

    }





    public void build() {



    }


    public int collisionLogic(Ball hit1,Ball hit2)
    {
        int type = 0;
        for(Shape shape:track.dataList)
        {
            AimBall ball = (AimBall) shape;
            if(ball.collision(hit1))
            {
                type = 1;
                if(ball.isAim)
                {
                    type = 2;
                }
                bomb.setColor(ball.color);
                bomb.makeBomb(ball.x, ball.y);
                ball.dead();
            }
            else if(ball.collision(hit2))
            {
                type = 1;
                if(ball.isAim)
                {
                    type = 2;
                }
                bomb.setColor(ball.color);
                bomb.makeBomb(ball.x, ball.y);
                ball.dead();
            }

        }

        return type;
    }


    public void dead() {
        status = OVER;
    }
}
