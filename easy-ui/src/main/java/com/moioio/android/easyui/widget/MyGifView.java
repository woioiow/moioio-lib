package com.moioio.android.easyui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moioio.android.util.AppFileUtil;
import com.moioio.android.util.TimerHandler;
import com.moioio.android.util.ViewUtil;
import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.gif.GifDecoder;
import com.moioio.util.CodeUtil;

import java.util.ArrayList;
import java.util.List;


public class MyGifView extends MyView implements Runnable {

    private ImageView imageView;
    private GifDecoder gifDecoder;
    private Bitmap gifBufferImg;
    private boolean isShow;
    private String path;
    private boolean isLoad;
    private int delay;
    private int step;


    private class GifTimer implements Runnable{

        TimerHandler timer;
        List<MyGifView> views = new ArrayList<>();

        GifTimer()
        {
            timer = new TimerHandler(this,10, ViewUtil.getId());
        }

        @Override
        public void run() {
            for(MyGifView view:views)
            {
                if(view.isShow)
                {
                    view.run();
                }
            }

        }

        public void add(MyGifView myGifView) {
            if(!views.contains(myGifView)){
                views.add(myGifView);
            }
        }

        public void remove(MyGifView myGifView) {
            if(views.contains(myGifView)){
                views.remove(myGifView);
            }

            if(views.size()==0)
            {
                timer.stop();
            }
        }

        public void start() {
            timer.start();
        }
    }

    public static void setTimerDelay(long m)
    {
        gifTimer.timer.setDelay(m);
    }

    static GifTimer gifTimer;

    public MyGifView(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {

        imageView = new ImageView(context);

        RelativeLayout.LayoutParams imageView_params = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT)
            .addRule(RelativeLayout.CENTER_IN_PARENT).get();
        imageView.setLayoutParams(imageView_params);



        String cachePath = AppFileUtil.makeCachePath(getContext(),"gif");

        gifDecoder = new GifDecoder();
        gifDecoder.setCachePath(cachePath);

        this.addView(imageView);

        if(gifTimer==null)
        {
            gifTimer = new GifTimer();
        }

    }


    public void load(String path)
    {
        String md5 = CodeUtil.getStringMD5(path);
        this.path  = AppFileUtil.makeCachePath(getContext(),"gif")+"/"+md5;
        AppFileUtil.copyAssetsDataToFile(getContext(), path, this.path);
        this.isLoad = true;
    }

    @Override
    public void run() {

        if(isLoad)
        {
            gifDecoder.decode(path);
            gifBufferImg = gifDecoder.getBufferedImage();
            isLoad = false;
        }


        if(gifBufferImg!=null)
        {
            step++;
            if(step>delay)
            {
                step = 0;
                gifDecoder.loop();
                gifDecoder.freshBufferedImage();
                imageView.setImageBitmap(gifBufferImg);
            }
        }
    }


    public void onShow()
    {
        isShow = true;
        gifTimer.add(this);
        gifTimer.start();
    }
    public void onHide()
    {
        isShow = false;
        gifTimer.remove(this);
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
