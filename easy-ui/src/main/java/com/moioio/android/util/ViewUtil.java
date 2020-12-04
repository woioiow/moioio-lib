package com.moioio.android.util;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
public class ViewUtil {

    private static int id_index = 4660;
    public static int getId()
    {
        id_index = 1 + id_index;
        return id_index;
    }



    public static void setViewID(View view)
    {
        view.setId(getId());
    }


    public static Drawable makeRoundDrawable(float radius, int color)
    {
        GradientDrawable pd = new GradientDrawable();
        pd.setCornerRadius(radius);
        pd.setColor(color);
        return pd;
    }

    public static Drawable makeRoundBord(float radius, int size, int color)
    {
        GradientDrawable pd = new GradientDrawable();
        pd.setCornerRadius(radius);
        pd.setStroke(size,color);
//        pd.setColor(color);
        return pd;
    }

    public static Drawable makeRoundBord(float[] radius, int size, int color)
    {
        GradientDrawable pd = new GradientDrawable();
        pd.setCornerRadii(radius);
        pd.setStroke(size,color);
//        pd.setColor(color);
        return pd;
    }


    public static Drawable makeRoundDrawableBord(float radius, int size, int bordcolor, int color)
    {
        GradientDrawable pd = new GradientDrawable();

        pd.setCornerRadius(radius);
        pd.setStroke(size,bordcolor);
        pd.setColor(color);
        return pd;
    }


    public static Drawable makeRoundDrawable(GradientDrawable.Orientation dir, float radius, int[] colors)
    {
        GradientDrawable pd = new GradientDrawable(dir,colors);
        pd.setCornerRadius(radius);
        return pd;
    }

    public static void setViewShadow(View view, final int radius, int size, int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), radius);
//                    outline.setOval(0, 0, view.getWidth(), view.getHeight());
                }
            };

            view.setElevation(size);
            view.setClipToOutline(true);
            view.setOutlineProvider(viewOutlineProvider);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                view.setOutlineSpotShadowColor(color);
                view.setOutlineAmbientShadowColor(color);
            }

        }
    }


    public static StateListDrawable getImageStateListDrawable(Bitmap pressBitmap, Bitmap relessBitmap) {

        int pressed = android.R.attr.state_pressed;
        int window_focused = android.R.attr.state_window_focused;
        int focused = android.R.attr.state_focused;
        int selected = android.R.attr.state_selected;

        BitmapDrawable pd = new BitmapDrawable(pressBitmap);
        BitmapDrawable rd = new BitmapDrawable(relessBitmap);
        StateListDrawable stalistDrawable = new StateListDrawable();
        stalistDrawable.addState(new int []{pressed , window_focused}, pd);
        stalistDrawable.addState(new int []{pressed , -focused}, pd);
        stalistDrawable.addState(new int []{selected }, pd);
        stalistDrawable.addState(new int []{focused }, pd);
        stalistDrawable.addState(new int []{}, rd);

        return stalistDrawable;
    }


    public static StateListDrawable getClickDrawable(Drawable pressDrawable, Drawable drawable) {

        int pressed = android.R.attr.state_pressed;
        int window_focused = android.R.attr.state_window_focused;
        int focused = android.R.attr.state_focused;
        int selected = android.R.attr.state_selected;


        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int []{pressed , window_focused}, pressDrawable);
        stateListDrawable.addState(new int []{pressed , -focused}, pressDrawable);
        stateListDrawable.addState(new int []{selected }, pressDrawable);
        stateListDrawable.addState(new int []{focused }, pressDrawable);
        stateListDrawable.addState(new int []{}, drawable);

        return stateListDrawable;
    }


    public static ColorStateList getClickColor(int pressColor, int color) {

        int pressed = android.R.attr.state_pressed;
        int window_focused = android.R.attr.state_window_focused;
        int focused = android.R.attr.state_focused;
        int selected = android.R.attr.state_selected;


        int[][] states = new int[][] {
                new int []{pressed , window_focused}, // enabled
                new int []{pressed , -focused}, // disabled
                new int []{selected }, // unchecked
                new int []{focused },  // pressed
                new int []{}
        };

        int[] colors = new int[] {
                pressColor,
                pressColor,
                pressColor,
                pressColor,
                color
        };

        ColorStateList colorStateList = new ColorStateList(states,colors);
        return colorStateList;
    }



    public static void hideView(View view) {
        view.setVisibility(View.INVISIBLE);
    }
    public static void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }


    public static int getAntiColor(int color)
    {
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;

        return Color.argb(alpha,red,green,blue);
    }

}
