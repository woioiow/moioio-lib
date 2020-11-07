package com.moioio.game.games.physical.flappy_ball;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.game.games.physical.common.shape.Track;
import com.moioio.util.RandomUtil;

class Wall extends Shape {

    final static int AWAIT = 0;
    final static int SHOOT = 1;
    final static int BACK = 2;
    final static int OVER = 3;
    int status;

    Track track = new Track();
    float eachWidth;
    Bomb bomb = new Bomb();

    @Override
    public void draw(Graphics g) {
        track.draw(g);
        bomb.draw(g);
    }

    @Override
    public void logic() {

        if(status!=OVER)
        {
            int size = track.dataList.size();
            track.logic();
            int size2 = track.dataList.size();

            if(size!=size2)
            {
                Block lastBlock = (Block) track.dataList.get(size2-1);
                Block block = makeBlock(lastBlock.x+lastBlock.width+eachWidth);
                track.dataList.add(block);
            }
        }
    }

    public void build() {

        int size = 5;
        eachWidth = width/3.2f;
        for(int i=0;i<size;i++)
        {
            Block block = makeBlock(x+width+eachWidth*i);
            track.dataList.add(block);
        }
    }

    private Block makeBlock(float xx)
    {
        Block block = new Block();
        block.setColor(color);
        block.setSpeed(speed);
        float h = height*(0.3f+(float)RandomUtil.getRandom(10)/100f);
        block.setRectSize(width/30,height);
        block.setHoleSize(h);

        block.setPosition(xx,y);
        block.build();
        return block;
    }

    public int collisionLogic(BirdBall ball) {

        int type = 0;
        for(Shape shape:track.dataList)
        {
            Block block =  ((Block)shape);
            type = block.collisionLogic(ball);
            if(type>0)
            {
                if(type==2)
                {
                    bomb.setColor(block.ballBlock.color);
                    bomb.makeBomb(block.ballBlock.x,block.ballBlock.y);

                }
                break;
            }
        }

        return type;
    }

    public void stopMove() {
        status = OVER;
    }
}
