package com.moioio.util;

public class DataUtil
{
    public static int getRateValue(int ov,int r)
    {
        return  ov*r/100;
    }


    public static int getInt(String attr) {
        try {
            return Integer.parseInt(attr);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return 0;
    }

    public static boolean getBoolean(String string) {
        try {
            return Boolean.parseBoolean(string);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return false;
    }

    public static long getLong(String attr) {
        try {
            return Long.parseLong(attr);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return 0;
    }

    public static float getFloat(String string) {
        try {
            return Float.parseFloat(string);
        } catch (Exception e) {
        }
        // TODO Auto-generated method stub
        return 0;
    }

}
