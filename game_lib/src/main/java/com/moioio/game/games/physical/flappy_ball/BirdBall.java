package com.moioio.game.games.physical.flappy_ball;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.shape.Ball;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.TailBallTrack;
import com.moioio.util.PhysicalUtil;

class BirdBall extends Ball {

    final static int AWAIT = 0;
    final static int SHOOT = 1;
    final static int BACK = 2;
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

        if(status!=OVER)
        {
            y += speed;

            count++;
            if(count%3==0)
            {
                float na = 180f;
                tailBallTrack.addTail(x,y,color,radius*0.6f,na,Math.abs(speed),10,1);
            }
            tailBallTrack.logic();

            checkHit();
        }
    }



    GameEngine gameEngine;
    void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    void build() {
    }


    void changeDir() {

        speed = speed*-1;
//        angle += (2*speed);
    }


    private void checkHit()
    {
        if(!isAreaRect())
        {
            bomb.setColor(color);
            bomb.makeBomb(x,y);
            status = OVER;
            wall.stopMove();
            gameEngine.setResult(GameEngine.RESULT_COMMON);
        }
        int type = wall.collisionLogic(this);
        if(type>0)
        {
            if(type==2)
            {
                gameEngine.setScore(gameEngine.getScore()+1);
            }
            else
            {
                bomb.setColor(Color.WHITE);
                bomb.makeBomb(x,y);
                status = OVER;
                wall.stopMove();
                gameEngine.setResult(GameEngine.RESULT_COMMON);
            }
        }

    }


    Wall wall;
    void setWall(Wall wall) {
        this.wall = wall;
    }
}
