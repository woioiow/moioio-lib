package com.moioio.game.games.physical.common.shape;

import com.moioio.android.g2d.Graphics;

import java.util.ArrayList;
import java.util.List;

public class Track {

    List<Shape> clearList;
    List<Shape> dataList;

    public Track()
    {
        clearList = new ArrayList<>();
        dataList = new ArrayList<>();
    }

    public void draw(Graphics g)
    {
        for(Shape shape:dataList)
        {
            shape.draw(g);
        }
    }


    public void logic()
    {
        for(Shape shape:dataList)
        {
            shape.logic();
            if(shape.isDead())
            {
                clearList.add(shape);
            }
        }

        for(Shape shape:clearList)
        {
            dataList.remove(shape);
        }

        clearList.clear();
    }

    public void add(Shape shape)
    {
        dataList.add(shape);
    }

}
