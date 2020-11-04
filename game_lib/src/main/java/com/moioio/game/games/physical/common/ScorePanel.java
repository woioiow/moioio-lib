package com.moioio.game.games.physical.common;

import com.moioio.android.g2d.Graphics;
import com.moioio.game.engine.BasePanel;

public class ScorePanel extends BasePanel {


    @Override
    public void draw(Graphics g) {

        g.setColor(0xFFFEFDFE);
        String str = String.valueOf(gameEngine.getScore());
        float fontSize = width/5;//g.getStringWidth(str);
        if(fontSize>height)
        {
            fontSize = height-20;
        }
        g.setFontSize(fontSize);
        g.drawString(str,x+(width-g.getStringWidth(str))/2,y+(height-g.getStringHeight(str))/2);
    }


}
