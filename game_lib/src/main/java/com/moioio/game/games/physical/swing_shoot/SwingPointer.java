package com.moioio.game.games.physical.swing_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Shape;
import com.moioio.game.games.physical.common.shape.SwingBall;

import java.util.ArrayList;
import java.util.List;

class SwingPointer extends SwingBall {

    List<PointBall> points;
    float startAngle;


    @Override
    public void draw(Graphics g) {

        g.setColor(color);
        Shape.drawShapes(points,g);
    }

    @Override
    public void logic() {
        swingParticle.runPeriod();
        angle = startAngle+swingParticle.getPeriod();

        int index = 0;
        for(PointBall ball:points)
        {
            float size = radius*2+20*index;
            double radians = Math.toRadians(angle);
            float px = x+(float)(size*Math.cos(radians));
            float py = y+(float)(size*Math.sin(radians));
            ball.setPosition(px,py);
            index++;
        }
    }


    public void build()
    {
        points = new ArrayList<>();
        for(int i=0;i<3;i++)
        {
            PointBall ball = new PointBall();
            ball.setColor(color);
            ball.setRadius(radius/(3+i));
            points.add(ball);
        }

    }
}
