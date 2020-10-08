package com.moioio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class CodeUtil {



    public static String getFileMD5(File file){
        BigInteger bigInt = null;
        try
        {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            bigInt = new BigInteger(1, md.digest());
        }
        catch (Exception e)
        {
            MyLog.printStackTrace(e);
        }
        return bigInt.toString(16);
    }

    public static String getByteMD5(byte[] data){


        String md5 = "";
        if(data==null)
        {
            return md5;
        }
        try
        {
            BigInteger bigInt = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = data;
            int length = buffer.length;
            md.update(buffer, 0, length);
            bigInt = new BigInteger(1, md.digest());
            md5 = bigInt.toString(16);
        }
        catch (Exception e)
        {
            MyLog.printStackTrace(e);
        }
        return md5;
    }



    public static String getStringMD5(String str){

        byte[] buffer = new byte[0];
        try {
            buffer = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            MyLog.printStackTrace(e);
        }

        return getByteMD5(buffer);
    }


    public static String md5(String string) {
        String md5Str = "";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            md5Str =  result.toString();
        } catch ( Exception e) {
            MyLog.printStackTrace(e);
        }
        return md5Str;
    }



}
