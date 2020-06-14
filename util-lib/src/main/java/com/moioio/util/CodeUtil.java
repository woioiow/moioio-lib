package com.moioio.util;

import java.io.File;
import java.io.FileInputStream;
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
            e.printStackTrace();
        }
        return bigInt.toString(16);
    }

    public static String getStringMD5(String str){
        BigInteger bigInt = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = str.getBytes("utf-8");
            int length = buffer.length;
            md.update(buffer, 0, length);
            bigInt = new BigInteger(1, md.digest());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bigInt.toString(16);
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
            e.printStackTrace();
        }
        return md5Str;
    }



}
