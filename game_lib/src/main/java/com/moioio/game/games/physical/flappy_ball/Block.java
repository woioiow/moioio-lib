package com.moioio.game.games.physical.flappy_ball;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.util.PhysicalUtil;
import com.moioio.util.RandomUtil;

public class Block extends Shape {

    LineBlock topBlock;
    LineBlock bottomBlock;
    BallBlock ballBlock;
    float holeSize;

    @Override
    public void draw(Graphics g) {
        topBlock.draw(g);
        bottomBlock.draw(g);
        if(!ballBlock.isDead())
        {
            ballBlock.draw(g);
        }
    }

    @Override
    public void logic() {
        x -= speed;
        topBlock.logic();
        bottomBlock.logic();
        ballBlock.logic();
    }

    public void setHoleSize(float size) {
        holeSize = size;
    }

    public void build() {

        float maxH = height-holeSize;
        float topH = maxH*0.1f+(RandomUtil.getRandom((int) (maxH*0.9f)));
        float bottomH = maxH-topH;
        float by = (y+topH+width)+RandomUtil.getRandom((int) (holeSize-width));

        topBlock = new LineBlock();
        topBlock.setColor(color);
        topBlock.setPosition(x,y);
        topBlock.setRectSize(width,topH);
        topBlock.setSpeed(speed);


        bottomBlock = new LineBlock();
        bottomBlock.setColor(color);
        bottomBlock.setPosition(x,y+height-bottomH);
        bottomBlock.setRectSize(width,bottomH);
        bottomBlock.setSpeed(speed);


        ballBlock = new BallBlock();
        ballBlock.setColor(0xFF00D193);
        ballBlock.setRadius(width/2);
        ballBlock.setSpeed(speed);
        ballBlock.setPosition(x+ballBlock.radius,by);

    }

    @Override
    public boolean isDead() {
        return (x+width)<0;
    }

    public int collisionLogic(BirdBall ball) {
        int type = 0;
        if(ball.collision(ballBlock)&&!ballBlock.isDead())
        {
            ballBlock.dead();
            type = 2;
        }
        if(type==0)
        {
            boolean cr1 = PhysicalUtil.circleWithRect(ball.x,ball.y,ball.radius, topBlock.x, topBlock.y,topBlock.width,topBlock.height);
            boolean cr2 = false;
            if(!cr1)
            {
                cr2 = PhysicalUtil.circleWithRect(ball.x,ball.y,ball.radius, bottomBlock.x, bottomBlock.y,bottomBlock.width,bottomBlock.height);
            }

            if(cr1||cr2)
            {
                type = 1;
            }
        }

        return type;
    }



}

class BallBlock extends Ball
{

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }

    @Override
    public void logic() {
        x -= speed;
    }

    boolean isDead;
    public void dead()
    {
        isDead = true;
    }
    public boolean isDead() {
        return  isDead;
    }

}


class LineBlock extends Shape
{

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    @Override
    public void logic() {
        x -= speed;
    }



}