package com.moioio.moioio_lib;

import android.text.TextUtils;
import android.util.Base64;
import com.moioio.util.MyLog;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PKCS7_Test {


    static byte[] a = new byte[]{10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91};
    static String d = "4099706666079904";
    public static void main(String[] args) throws InterruptedException {
        try {
            String json = "haha";//""[{\"duration\":60000,\"page_name\":\"com.shinyv.cnr.SongNewActivity\"}]";
            String str = a(json);
            MyLog.debug(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String a(String var1) {
        String var2 = null;

        try {
            if (TextUtils.isEmpty(d)) {
                var2 = var1;
            } else {
                byte[] var3 = a(var1.getBytes(), d.getBytes());
                var2 = Base64.encodeToString(var3, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return var2;
    }

    public static String b(String var1) {
        String var2 = null;

        try {
            if (TextUtils.isEmpty(d)) {
                var2 = var1;
            } else {
                byte[] var3 = Base64.decode(var1.getBytes(), 0);
                var2 = new String(b(var3, d.getBytes()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return var2;
    }


    public static byte[] a(byte[] var0, byte[] var1) throws Exception {
        Cipher var2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec var3 = new SecretKeySpec(var1, "AES");
        IvParameterSpec var4 = new IvParameterSpec(a);
        var2.init(1, var3, var4);
        return var2.doFinal(var0);
    }

    public static byte[] b(byte[] var0, byte[] var1) throws Exception {
        Cipher var2 = Cipher.getInstance("AES/CBC/PKCS7Padding");
        SecretKeySpec var3 = new SecretKeySpec(var1, "AES");
        IvParameterSpec var4 = new IvParameterSpec(a);
        var2.init(2, var3, var4);
        return var2.doFinal(var0);
    }



}
