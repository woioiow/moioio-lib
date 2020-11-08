package com.moioio.game.games.physical.remember_touch;

import android.graphics.Color;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.games.physical.common.shape.Shape;

 class Block extends Shape {


     int bgColor;


    @Override
    public void draw(Graphics g) {

        float ww = width*0.8f;
        float hh = height*0.8f;
        float xx = x+(width-ww)/2;
        float yy = y+(height-hh)/2;

        g.setColor(bgColor);
        g.fillRect(xx,yy,ww,hh);

        if(isShow)
        {
            int alpha = 0;//Color.alpha(color);
            if(step<stepMax/2)
            {
                alpha = 255*step/(stepMax/2);
            }
            else
            {
                alpha = 255-255*(step-stepMax/2)/(stepMax/2);
            }
            int newColor = Color.argb(alpha,Color.red(color),Color.green(color),Color.blue(color));
            g.setColor(newColor);
        }
        g.fillRect(xx,yy,ww,hh);

        if(isPress)
        {
            g.setColor(color);
            g.fillRect(xx,yy,ww,hh);
        }


    }

    @Override
    public void logic() {

        if(step<stepMax)
        {
            step++;
        }
        else
        {
            isShow = false;
        }
    }


    boolean isShow;
    int step;
    int stepMax;
    void showPrompt()
    {
        if(!isShow)
        {
            isShow = true;
            stepMax = 30;
            step = 0;
        }
    }

    boolean isPress;
     void press()
     {
         isPress = true;
     }


}
