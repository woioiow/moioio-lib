package com.moioio.game.games.physical.rotate_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

class AimRing extends Ball {

    final static int STATUS_WAIT = 0;
    final static int STATUS_MOVE = 1;
    final static int STATUS_STOP = 2;
    List<AimBall> balls = new ArrayList<>();
    Bomb bomb = new Bomb();

    @Override
    public void draw(Graphics g) {
        Shape.drawShapes(balls,g);
        bomb.draw(g);
    }

    @Override
    public void logic() {
        makeRandomMove();
    }



    int status;
    int angleStep;
    float angleEach;
    //this is aimball ai to escape to shootball
    void makeRandomMove()
    {
        if(status==STATUS_WAIT)
        {
            boolean thinking = (RandomUtil.getRandom(20)==5);
            if(thinking)
            {

                float aimAngle = angle+RandomUtil.getRandom(90);

                float xa = RandomUtil.getRandomNoSign(90);//(aimAngle-angle);

                angleStep = 20+RandomUtil.getRandom(20);
                angleEach = xa/(float) angleStep;

                status = STATUS_MOVE;
            }
        }
        else if(status==STATUS_MOVE)
        {
            if(angleStep >0)
            {
                angle += angleEach;
                angleStep--;
                rotate();
            }
            else
            {
                status = STATUS_WAIT;
            }
        }

    }


    void stopRotate()
    {
        angleStep = 0;
        status = STATUS_STOP;
    }

    void rotate()
    {
        float eachAngle = 360/balls.size();
        int index = 0;
        for(AimBall ball:balls)
        {
            double radians = Math.toRadians(angle+eachAngle*index);
            ball.x = x+(float)(radius*Math.cos(radians));
            ball.y = y+(float)(radius*Math.sin(radians));
            index++;
        }
    }


    public void build() {

        int size = 8;
        float eachAngle = 360/size;
        for(int i=0;i<size;i++)
        {
            AimBall ball = new AimBall();
            ball.setColor(color);
            ball.setRadius(radius*0.09f);
            double radians = Math.toRadians(eachAngle*i);
            ball.x = x+(float)(radius*Math.cos(radians));
            ball.y = y+(float)(radius*Math.sin(radians));
            balls.add(ball);
        }
        AimBall ball = balls.get(RandomUtil.getRandom(balls.size()));
        ball.isAim = true;
        ball.setColor(0xFFF7D503);
    }

    AimBall getAim()
    {
        AimBall ret = null;
        for(AimBall ball:balls)
        {

            if(ball.isAim)
            {
                ret = ball;
                break;
            }
        }
        return ret;
    }

    public boolean collision(Ball hit)
    {
        boolean ok = false;
        AimBall ball = getAim();
        if(ball.collision(hit)&&ball.isAim)
        {
            ok = true;
        }
        return ok;
    }



    public void dead() {
        AimBall ball = getAim();
        bomb.setColor(ball.color);
        bomb.makeBomb(ball.x,ball.y);
        for(AimBall ball_:balls)
        {
            ball_.setColor(color);
            ball_.isAim = false;
        }
        ball = balls.get(RandomUtil.getRandom(balls.size()));
        ball.isAim = true;
        ball.setColor(0xFFF7D503);
        status = STATUS_WAIT;
    }
}
