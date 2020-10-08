package com.moioio.android.easyui.widget.draw;

import android.content.Context;


import com.moioio.android.g2d.Graphics;

import java.util.ArrayList;
import java.util.List;

public class MyDrawPanel {

    private String id;
    float x;
    float y;
    float width;
    float height;
    List<MyDrawItem> items;

    public MyDrawPanel()
    {
        items = new ArrayList<>();
    }

    public void layout(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float width() {
        return width;
    }

    public float height() {
        return height;
    }


    public void paint(Graphics g)
    {

    }
    protected void onTouch(int action, float x, float y) {

    }

    protected void onMultiTouch(int id,int action, float x, float y) {
    }




    public boolean contains(float x,float y)
    {
        return x >= this.x && x < (this.x+this.width) && y >= this.y && y < (this.y+this.height);
    }

    public void init(Context context) {
    }

    public float getX() {
        return x;
    }


    public float getY() {
        return y;
    }


    public void addItem(MyDrawItem item)
    {
        if(!items.contains(item))
        {
            items.add(item);
        }
    }

    public void drawItems(Graphics g)
    {
        for(MyDrawItem item:items)
        {
            item.paint(g);
        }
    }

    public boolean clickItems(int action, float x, float y)
    {
        boolean isClick = false;
        for(MyDrawItem item:items)
        {
            if(item.click(action, x, y))
            {
                isClick = true;
            }
        }
        return isClick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
