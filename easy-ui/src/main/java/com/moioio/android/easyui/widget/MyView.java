package com.moioio.android.easyui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.round.RoundHelper;
import com.moioio.android.g2d.Graphics;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;
import com.moioio.util.MyLog;


public abstract class MyView extends RelativeLayout {


    private MyLayout MyLayout;
    private boolean isInited;
    private int margin;
    private int line;

    RoundHelper mHelper;
    public MyView(Context context) {
        super(context);
        margin = DisplayUtil.getDip(context,5);
        line = DisplayUtil.getDip(context,1);
        setBackgroundColor(0);
        mHelper = new RoundHelper();
        mHelper.init();
        ViewUtil.setViewID(this);
        initPage(context);
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
        mHelper.setRadius(angle);
        if(clipAngle>0)
        {
            isClipRound = true;
        }
    }
    public void setRoundAngle(float[] angles)
    {
        if(angles==null||angles.length!=4)
        {
            return;
        }
        mHelper.setRadius(angles[0],angles[1],angles[2],angles[3]);

        if(clipAngle>0)
        {
            isClipRound = true;
        }
    }



    public void setShadow(int color,int border)
    {
        mHelper.setStrokeColor(color);
        mHelper.setStrokeWidth(border);

    }


    public void setShadowColor(int color)
    {
//        mHelper.setShadowColor(color);
        shadowColor = color;
        isClipRound = true;
    }

    public void setShadowBorder(int border)
    {
        shadowBorder = border;
//        mHelper.setShadowBorder(border);
//        isClipRound = true;
//        rectFPath = new RectF(shadowBorder, shadowBorder, getMeasuredWidth()-shadowBorder, getMeasuredHeight()-shadowBorder);
//        if(shadowBorder!=0)
//        {
//            blurMaskFilter = new BlurMaskFilter(shadowBorder, BlurMaskFilter.Blur.OUTER);
//        }
//        else
//        {
//            blurMaskFilter = null;
//        }
    }
//    PaintFlagsDrawFilter pfd;


    @Override
    public void draw(Canvas canvas) {


//        MyLog.debug(this+"mHelper.isUse():::"+mHelper.isUse());
        mHelper.preDraw(canvas);
        super.draw(canvas);
        mHelper.drawPath(canvas);

//        if(mHelper.isUse())
//        {
//        }
//        else
//        {
//            super.draw(canvas);
//        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHelper.onSizeChanged(w, h);
    }

//
//
//

//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        MyLog.debug("canvas::::"+canvas);
//    }
//
//
//    RectF mRectF = new RectF();
//    Xfermode mXfermode;
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mRectF.set(0, 0, w, h);
//    }
//    float[] mRadii;
//
//    @Override
//    public void draw(Canvas canvas) {
//
//
//        canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);
//
//
//
//
//
//
//
//
//        if(isClipRound)
//        {
//            if(g==null)
//            {
//
//                path = new Path();
//                g = new Graphics();
//                g.paint.setAntiAlias(true);
//                pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
//                canvas.setDrawFilter(pfd);
//                rectFPath = new RectF(shadowBorder, shadowBorder, getWidth()-shadowBorder, getHeight()-shadowBorder);
//                mXfermode = new PorterDuffXfermode(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PorterDuff.Mode.DST_OUT : PorterDuff.Mode.DST_IN);
//
//                mRadii = new float[8];
//                for(int i=0;i<mRadii.length;i++)
//                {
//                    mRadii[i] = clipAngle;
//                }
//            }
//            g.setCanvas(canvas);
//            g.getCanvas().setDrawFilter(pfd);
//
//            path.reset();
//
////            path.addRoundRect(rectFPath, clipAngle,clipAngle, Path.Direction.CW);
//            path.addRoundRect(rectFPath, mRadii, Path.Direction.CCW);
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                mTempPath.reset();
////                mTempPath.addRect(mRectF, Path.Direction.CCW);
////                mTempPath.op(mPath, Path.Op.DIFFERENCE);
////                canvas.drawPath(mTempPath, mPaint);
////            } else {
////                canvas.drawPath(mPath, mPaint);
////            }
//
//
////            if(blurMaskFilter!=null)
////            {
////                g.setColor(shadowColor);
////                g.setMaskFilter(blurMaskFilter);
////                g.setColor(Color.BLACK);
////                g.fillPath(path);
////                g.setMaskFilter(null);
////            }
//
//            g.setColor(Color.BLACK);
//            g.fillPath(path);
////            g.setClipArea(path);
//            super.draw(canvas);
//            g.setColor(Color.BLACK);
//            g.fillRect(0,0,getWidth(),getHeight());
//
//        }
//        else
//            {
//                super.draw(canvas);
//            }
//
//        MyLog.debug(this+"getWidth():::"+getWidth());
//        MyLog.debug(this+"getHeight():::"+getHeight());
//
//    }


//    private void clipRoundView() {
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
//                @Override
//                public void getOutline(View view, Outline outline) {
//                    //修改outline为特定形状
//                    outline.setRoundRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), clipAngle);
//                    outline.set
//                }
//            };
//        }
//    }


}
