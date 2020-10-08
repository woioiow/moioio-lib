package com.moioio.android.easyui.widget.draw;

import com.moioio.android.g2d.Graphics;
import com.moioio.android.easyui.widget.MyDrawView;

public abstract class MyDrawClickItem extends MyDrawItem {

    private boolean isPress;
    private ClickListener clickListener;
    private boolean isSelected;


    public void paint(Graphics g)
    {

    }


    public boolean click(int action, float x, float y) {
        boolean isClick = false;
        if(action== MyDrawView.TOUCH_DOWN)
        {
            isPress = false;
            if(contains(x,y))
            {
                isPress = true;
                isClick = true;
            }
        }
        else if(action== MyDrawView.TOUCH_MOVE)
        {
            if(isPress)
            {
                if(!contains(x,y))
                {
                    isPress = false;
                }
            }
        }
        else if(action== MyDrawView.TOUCH_UP)
        {
            if(isPress)
            {
                if(contains(x,y))
                {
                    isClick = true;
                    if(clickListener!=null)
                    {
                        clickListener.onClick(this);
                    }
                }
            }
            isPress = false;
        }
        return isClick;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface ClickListener{
        void onClick(MyDrawClickItem item);
    }


    public boolean isPress() {
        return isPress;
    }

    public void setPress(boolean press) {
        isPress = press;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
