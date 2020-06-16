package com.moioio.android.util;

import android.content.Context;

import com.moioio.util.StreamUtil;
import com.moioio.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AppFileUtil {


    public static String makeCachePath(Context context, String name) {
        String path = "";

        String cachepath = "";
        cachepath = 	context.getExternalFilesDir(null).getPath();
        if(!StringUtil.isNull(cachepath))
        {
            if(!cachepath.endsWith("/"))
            {
                cachepath = cachepath+"/";
            }
            path = cachepath+name+"/";

//			Util.debug("getCachePath----"+path);

            boolean isOk = new File(path).mkdirs();
//			Util.debug("getCachePath----"+isOk);
        }
        return path;
    }


    private static int BUFFER_SIZE = 100*1024;
    public static void copyAssetsDataToFile(Context paramContext,String url,String path)
    {
        String str = url;
        try
        {
            if (url.startsWith("assets:"))
            {
                str = url.substring(9);
            }

            int size = 0;
            byte[] buf = new byte[BUFFER_SIZE];

//          Util.debug("url----------"+url);
//          Util.debug("path----------"+path);

            FileOutputStream fos = new FileOutputStream(path);
            InputStream bis = paramContext.getAssets().open(str);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.close();
            bis.close();
//          Util.debug("size----------"+new File(path).length());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static boolean writeAppFileData(Context paramContext, String paramString, byte[] paramArrayOfByte)
    {
        try
        {
            FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString, Context.MODE_PRIVATE);
            localFileOutputStream.write(paramArrayOfByte);
            localFileOutputStream.close();
            return true;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return false;
    }

    public static byte[] readAppFileData(Context paramContext, String paramString)
    {
        byte[] data = null;
        try
        {
            FileInputStream localFileOutputStream = paramContext.openFileInput(paramString);
            data = StreamUtil.inputStreamToBytes(localFileOutputStream);
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return data;
    }


    public static byte[] getAssetsData(Context paramContext, String paramString)
    {
        String str = urlToAssets(paramString);
        try
        {
            byte[] arrayOfByte = StreamUtil.inputStreamToBytes(paramContext.getAssets().open(str));
            return arrayOfByte;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }


    public static String urlToAssets(String paramString)
    {
        String str = paramString;
        str = StringUtil.replaceAll(str, "\\", "/", true);
        if (paramString.startsWith("assets:"))
        {
            str = paramString.substring(9);
        }
        return str;
    }

}
