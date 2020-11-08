package com.moioio.game.games.physical.remember_touch;

import android.graphics.Color;

import com.moioio.android.easyui.widget.MyDivView;
import com.moioio.android.easyui.widget.MyDrawView;
import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.common.shape.Bomb;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.game.games.physical.common.shape.Track;
import com.moioio.util.PhysicalUtil;
import com.moioio.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TouchBroad extends Shape {

    final static int THINKING = 0;
    final static int AI = 1;
    final static int YOU = 2;
    final static int OVER = 3;
    int status;
    int count;



    Track track = new Track();
    Bomb bomb = new Bomb();


    @Override
    public void draw(Graphics g) {
        if(status!=OVER)
        {
            track.draw(g);
        }
        bomb.draw(g);
    }

    @Override
    public void logic() {

        if(status==THINKING)
        {
            if(count==0)
            {
                makeRemember();
            }
            if(count==10)
            {
                setBlocksColor(0xFF666666);
                setBlocksPress(false);
                status = AI;
            }
            count++;
        }
        else if(status==AI)
        {
            Block block = (Block) track.dataList.get(keys[index]);
            block.showPrompt();
            block.logic();
            if(!block.isShow)
            {
                index++;
                if(index==keys.length)
                {
                    setBlocksColor(0xFFAAAAAA);
                    index = 0;
                    status = YOU;
                }
            }
        }

    }

    public void touchBlock(int action, float x, float y) {
        if(action== MyDrawView.TOUCH_DOWN&&status==YOU)
        {
            for(Shape shape:track.dataList)
            {
                Block block = (Block) shape;
                if(PhysicalUtil.pointInRect(block.x,block.y,block.width,block.height,x,y))
                {
                    block.press();
                    int tag = (int) block.getTag();
                    if(keys[index]!=tag)
                    {
                        gameEngine.setResult(GameEngine.RESULT_COMMON);
                        makeBlocksBomb();
                        status = OVER;
                        break;
                    }
                    index++;
                    if(index==keys.length)
                    {
                        gameEngine.setScore(gameEngine.getScore()+1);
                        setBlocksPress(true);
                        status = THINKING;
                        count = 0;
                    }
                    break;
                }
            }
        }
    }


    void makeBlocksBomb()
    {
        for(Shape shape:track.dataList)
        {
            Block block = (Block) shape;
            bomb.setColors(Color.WHITE,0xFF666666,0xFFAAAAAA,block.color);

            bomb.addBomb(block.x+block.width/2,block.y+block.height/2);
            bomb.addBomb(block.x,block.y);
            bomb.addBomb(block.x+block.width,block.y);
            bomb.addBomb(block.x,block.y+block.height);
            bomb.addBomb(block.x+block.width,block.y+block.height);
        }
    }


    int[] keys;
    int index = 0;
    void makeRemember()
    {
        index = 0;
        keys = getSequence(track.dataList.size());
    }

    void setBlocksColor(int color)
    {
        for(Shape shape:track.dataList)
        {
            Block block = (Block) shape;
            block.bgColor = color;
        }
    }

    void setBlocksPress(boolean press)
    {
        for(Shape shape:track.dataList)
        {
            Block block = (Block) shape;
            block.isPress = press;
        }
    }


    int[] getSequence(int no) {
        int[] sequence = new int[no];
        for (int i = 0; i < no; i++) {
            sequence[i] = i;
        }
        Random random = new Random();
        for (int i = 0; i < no; i++) {
            int p = random.nextInt(no);
            int tmp = sequence[i];
            sequence[i] = sequence[p];
            sequence[p] = tmp;
        }

        int[] ret = new int[3+RandomUtil.getRandom(2)];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = sequence[i];
        }

        return ret;

    }

    public void build()
    {
        for(int i=0;i<9;i++)
        {
            Block block = new Block();
            block.setColor(0xFF00D193);
            block.setTag(i);
            block.setRectSize(blockSize,blockSize);
            block.setPosition(x+(i%3)*blockSize,y+(i/3)*blockSize);
            track.add(block);
        }
        setBlocksColor(0xFF666666);
        setBlocksPress(false);
    }

    float blockSize;
    public void setBlockSize(float blockSize) {
        this.blockSize = blockSize;
    }


}
