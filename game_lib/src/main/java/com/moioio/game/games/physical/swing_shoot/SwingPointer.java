package com.moioio.game.games.physical.swing_shoot;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.Shape;
import com.moioio.game.games.physical.common.particle.SwingParticle;

import java.util.ArrayList;
import java.util.List;

public class SwingPointer extends Shape {

    SwingParticle swingParticle;
    float minAngle;
    float maxAngle;
    float angle;
    float size;
    List<PointBall> points;

    SwingPointer()
    {
        swingParticle = new SwingParticle();
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(color);
        Shape.drawShapes(points,g);
    }

    @Override
    public void logic() {
        if(points==null)
        {
            points = new ArrayList<>();
            for(int i=0;i<3;i++)
            {
                PointBall ball = new PointBall();
                ball.setColor(color);
                ball.setRadius(size/(3+i));
                points.add(ball);
            }
            points.add(new PointBall());
            points.add(new PointBall());
        }
        swingParticle.runPeriod();
        angle = minAngle+swingParticle.getPeriod();

        int index = 0;
        for(PointBall ball:points)
        {
            float radius = size*2+20*index;
            double radians = Math.toRadians(angle);
            float x = ox+(float)(radius*Math.cos(radians));
            float y = oy+(float)(radius*Math.sin(radians));
            ball.setPosition(x,y);
            index++;
        }




    }

    public void setSize(float v) {
        size = v;
    }

    public void setAngleDomain(float minAngle, float maxAngle) {

        this.maxAngle = maxAngle;
        this.minAngle = minAngle;

        float max = maxAngle-minAngle;
        float step = max/30;

        swingParticle.setPeriodMax(max);
        swingParticle.setPeriodRate(step);
    }
}
