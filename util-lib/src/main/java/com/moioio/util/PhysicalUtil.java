package com.moioio.util;

public class PhysicalUtil {


    public static float getDistance(float x1,float y1,float x2, float y2){

        float _x = Math.abs(x2 - x1);
        float _y = Math.abs(y2 - y1);
        float distance = (float) Math.sqrt(_x*_x+_y*_y);
        return distance;
    }


    public static float getAngle(float cx,float cy,float x, float y){
        float angle = (float)Math.toDegrees(Math.atan2(y -cy,x-cx));
        if(angle< 0){
            angle += 360;
        }
        return angle;
    }

    public static float getAntiAngle(float angle){
        float antiAngel = (180+angle)%360;
        return antiAngel;
    }

    public static boolean pointInRect(float rx,float ry,float rw,float rh,float x,float y)
    {
        return x >= rx && x < (rx+rw) && y >= ry && y < (ry+rh);
    }

}
