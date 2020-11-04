package com.moioio.game.games.physical.swing_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.util.RandomUtil;

class AimBall extends Ball {

    final static int STATUS_WAIT = 0;
    final static int STATUS_MOVE = 1;
    final static int STATUS_STOP = 2;
    Bomb bomb;

    public AimBall()
    {
        bomb = new Bomb();
    }

    @Override
    public void draw(Graphics g) {
        if(isDead)
        {
            bomb.draw(g);
            return;
        }
        g.setColor(color);
        g.fillCircle(x,y,radius);
    }

    @Override
    public void logic() {
        if(isDead)
        {
            return;
        }
        makeRandomMove();
    }


    int status;
    int moveStep;
    float moveEach;
    //this is aimball ai to escape to shootball
    void makeRandomMove()
    {
        if(status==STATUS_WAIT)
        {
            boolean thinking = (RandomUtil.getRandom(20)==5);
            if(thinking)
            {

                float aimX = minX+(RandomUtil.getRandom((int) (maxX-minX)));

                float xd = (aimX-x);

                moveStep = 10+RandomUtil.getRandom(20);
                moveEach = xd/(float) moveStep;

                status = STATUS_MOVE;
            }
        }
        else if(status==STATUS_MOVE)
        {
            if(moveStep>0)
            {
                x += moveEach;
                moveStep--;
            }
            else
            {
                status = STATUS_WAIT;
            }
        }

    }


    void stopMove()
    {
        moveStep = 0;
        status = STATUS_STOP;
    }



    float minX;
    float maxX;
    void setXDomain(float min, float max) {
        minX = min;
        maxX = max;
    }

    private boolean isDead;
    public void dead() {
        isDead = true;
        bomb.setColor(color);
        bomb.makeBomb(x,y);
    }


    public void revive() {
        isDead = false;
        status = STATUS_WAIT;
    }
}
