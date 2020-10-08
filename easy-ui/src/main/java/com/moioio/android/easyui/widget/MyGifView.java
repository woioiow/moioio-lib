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


public class MyGifView extends MyView implements Runnable {

    private ImageView imageView;
    GifDecoder gifDecoder;
    Bitmap gifBufferImg;

    TimerHandler timer;


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

        timer = new TimerHandler(this,10, ViewUtil.getId());
    }


    private String path;
    private boolean isLoad;
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
            gifDecoder.loop();
            gifDecoder.freshBufferedImage();
            imageView.setImageBitmap(gifBufferImg);
        }
    }


    public void onVisibilityChanged(View changedView, int visibility)
    {
        super.onVisibilityChanged(changedView,visibility);

        if(timer!=null)
        {
            if(visibility== View.VISIBLE)
            {
                timer.start();
            }
            else
            {
                timer.stop();
            }
        }
    }
}
