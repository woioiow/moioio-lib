package com.moioio.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil {

    static int BUFFER_SIZE = 8*1024;


    public static byte[] inputStreamToBytes(InputStream bis)
    {
        try
        {

            ByteArrayOutputStream fos = new ByteArrayOutputStream();

            int size = 0;
            byte[] buf = new byte[BUFFER_SIZE];


            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            byte[] data = fos.toByteArray();
            fos.close();
            bis.close();


            return data;
        }
        catch (Exception localException)
        {
        }
        return null;
    }

}
