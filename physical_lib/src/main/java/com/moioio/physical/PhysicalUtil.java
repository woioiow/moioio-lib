package com.moioio.physical;

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
}
