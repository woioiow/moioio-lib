package com.moioio.game.games.physical.common;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.GameEngine;
import com.moioio.game.games.physical.swing_shoot.ShootBall;

import java.util.ArrayList;
import java.util.List;

public abstract class PhysicalGamePanel extends GameEngine {

    public List<Shape> shapes;
    public PhysicalGamePanel()
    {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }



    public void drawShapes(Graphics g)
    {
        for(Shape shape:shapes)
        {
            shape.draw(g);
        }
    }

    public void logicShapes()
    {
        for(Shape shape:shapes)
        {
            shape.logic();
        }
    }

}
