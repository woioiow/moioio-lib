package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.RelativeLayout;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.g2d.Graphics;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;


public abstract class MyView extends RelativeLayout {


    private MyLayout MyLayout;
    private boolean isInited;
    private int margin;
    private int line;


    public MyView(Context context) {
        super(context);
        margin = DisplayUtil.getDip(context,5);
        line = DisplayUtil.getDip(context,1);
        initPage(context);
        ViewUtil.setViewID(this);
    }

    public abstract void initPage(Context context);

    public void show()
    {
        this.setVisibility(VISIBLE);
    }

    public void hide()
    {
        this.setVisibility(INVISIBLE);
    }

    public void gone()
    {
        this.setVisibility(GONE);
    }


    public boolean isShow()
    {
        return this.getVisibility()==VISIBLE;
    }

    public boolean isHide()
    {
        return this.getVisibility()==INVISIBLE;
    }

    public boolean isGone()
    {
        return this.getVisibility()==GONE;
    }



    public Activity getActivity()
    {
        return (Activity)getContext();
    }

    protected void onVisibilityChanged(View changedView, int visibility) {
        if(visibility==VISIBLE)
        {
            onShow();
        }
        else
        {
            onHide();
        }
    }

    public void onShow()
    {
    }

    public void onHide()
    {

    }


    public MyLayout makeLayout(int width, int height)
    {
        MyLayout = new MyLayout(width,height);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }

    public MyLayout getMyLayout()
    {
        return MyLayout;
    }

    public void reMyLayout()
    {
        this.setLayoutParams(MyLayout.get());
    }


    public MyLayout makeLayout()
    {
        MyLayout = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT);
        this.setLayoutParams(MyLayout.get());
        return MyLayout;
    }


    public void onResume() {

    }
    public void onPause() {

    }
    public void onStop() {

    }
    public void onDestroy() {

    }


    public int defaultMargin() {
        return margin;
    }
    public int defaultLine() {
        return line;
    }




    float shadowBorder = 0;
    float clipAngle = 0;
    Path path ;
    BlurMaskFilter blurMaskFilter;
    Graphics g;
    int shadowColor;
    RectF rectFPath;


    boolean isClipRound;
    public void setRoundAngle(float angle)
    {
        clipAngle = angle;
        if(clipAngle>0)
        {
            isClipRound = true;
        }
    }

    public void setShadow(int color,int border)
    {
        setShadowColor(color);
        setShadowBorder(border);
    }


    public void setShadowColor(int color)
    {
        shadowColor = color;
        isClipRound = true;
    }

    public void setShadowBorder(int border)
    {
        shadowBorder = border;
        isClipRound = true;
        rectFPath = new RectF(shadowBorder, shadowBorder, getMeasuredWidth()-shadowBorder, getMeasuredHeight()-shadowBorder);
        if(shadowBorder!=0)
        {
            blurMaskFilter = new BlurMaskFilter(shadowBorder, BlurMaskFilter.Blur.OUTER);
        }
        else
        {
            blurMaskFilter = null;
        }
    }
    PaintFlagsDrawFilter pfd;

    @Override
    protected void dispatchDraw(Canvas canvas) {

        if(isClipRound)
        {
            if(g==null)
            {
                path = new Path();
                g = new Graphics();
                g.paint.setAntiAlias(true);
//                g.grap.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALILAS_FLAG | Paint.FLITER_BITMAP_FLAG))

                pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
                canvas.setDrawFilter(pfd);

                rectFPath = new RectF(shadowBorder, shadowBorder, getMeasuredWidth()-shadowBorder, getMeasuredHeight()-shadowBorder);
            }
            g.setCanvas(canvas);
            g.getCanvas().setDrawFilter(pfd);
            path.reset();
            path.addRoundRect(rectFPath, clipAngle,clipAngle, Path.Direction.CW);
            if(blurMaskFilter!=null)
            {
                g.setColor(shadowColor);
                g.setMaskFilter(blurMaskFilter);
                g.fillPath(path);
                g.setMaskFilter(null);
                g.setColor(Color.WHITE);
            }
            canvas.clipPath(path);
        }

        super.dispatchDraw(canvas);
    }


}
