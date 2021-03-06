package com.moioio.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.moioio.android.MyAndroidLib;

public class DisplayUtil
{
    public static int topH( ){
        return getStateBarHeight(MyAndroidLib.context);
    }

    public static int bottomH( ){
        return getNavigationBarHeight(MyAndroidLib.context);
    }

    public static int dip( int num)
    {
        return getDip(MyAndroidLib.context,num);
    }

    public static int screenW()
    {
        return getScreenWidth(MyAndroidLib.context);
    }

    public static int screenH()
    {
        return getScreenHeight(MyAndroidLib.context);
    }

    public static int screenW(float rate)
    {
        return (int) (getScreenWidth(MyAndroidLib.context)*rate);
    }

    public static int screenH(float rate)
    {
        return (int) (getScreenHeight(MyAndroidLib.context)*rate);
    }


    public static int topH(Context context){
        return getStateBarHeight(context);
    }

    public static int bottomH(Context context){
        return getNavigationBarHeight(context);
    }

    public static int dip(Context context, int num)
    {
        return getDip(context,num);
    }

    public static int screenW(Context context)
    {
        return getScreenWidth(context);
    }

    public static int screenH(Context context)
    {
        return getScreenHeight(context);
    }



    public static int getStateBarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



    public static int getNavigationBarHeight(Context context) {
        return  getNavigationBarHeight(context, Configuration.ORIENTATION_PORTRAIT);
    }


    public static int getNavigationBarHeight(Context context, int orientation) {
        Resources resources = context.getResources();

        int id = resources.getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
        if (id > 0) {
            return resources.getDimensionPixelSize(id);
        }
        return 0;
    }



    public static int getDip(Context context, int num)
    {
        float value = num * getScreenDpi(  context);
        return (int) value;
    }

    public static float getScreenDpi(Context context)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.density;
    }


    public static int getScreenHeight(Context context)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context)
    {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }


    public static int getRateScreenHeight(Context context,float rate)
    {
        int height = (int) (DisplayUtil.getScreenHeight(context)*rate);
        return height;
    }

    public static int getRateScreenWidth(Context context,float rate)
    {
        int width = (int) (DisplayUtil.getScreenWidth(context)*rate);
        return width;
    }


}
