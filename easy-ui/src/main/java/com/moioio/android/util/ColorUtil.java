package com.moioio.android.util;

import android.graphics.Color;

public class ColorUtil {

    public static long RGB_to_ARGB( int rgb) {
        int r = (rgb >> 16 ) & 0xFF ;
        int g = (rgb >> 8 ) & 0xFF ;
        int b = (rgb >> 0 ) & 0xFF ;
        return 0xff000000 | (r << 16 ) | (g << 8 ) | b;
    }


    public static int change_RGB_Alpha(int baseColor,float alpha) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    public static int change_RGB_Brightness( int rgb,float brightness) {
        float hsv[] = new float[3];
        Color.colorToHSV(rgb,hsv);
        hsv[2] = brightness;
        return Color.HSVToColor(hsv);
    }
    public static int change_RGB_Saturation( int rgb,float saturation) {
        float hsv[] = new float[3];
        Color.colorToHSV(rgb,hsv);
        hsv[1] = saturation;
        return Color.HSVToColor(hsv);
    }

}
