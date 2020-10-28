package com.moioio.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.moioio.util.StreamUtil.BUFFER_SIZE;

public class HttpUtil {


    public static byte[] getHttpData(String destUrl,boolean isbreak) {

//        MyLog.debug("getHttpData---------:"+destUrl);
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

        try {

            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();

            bis = new BufferedInputStream(httpUrl.getInputStream());

            ByteArrayOutputStream fos = new ByteArrayOutputStream();

//            int length = 0;

            while ((size = bis.read(buf)) != -1) {
//            	length += size;
                fos.write(buf, 0, size);
                if(isbreak)
                {
                    break;
                }
            }
            byte[] data = fos.toByteArray();
            fos.close();
            bis.close();

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }

        return null;
    }

    public static boolean getHttpDataToFile(String destUrl, String path, HttpListener listener) {

        boolean isOK = false;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;

        try {

            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();

            bis = new BufferedInputStream(httpUrl.getInputStream());

            FileUtil.delFile(path+".tmp");
            FileOutputStream fos = new FileOutputStream(path+".tmp");

            int total = httpUrl.getContentLength();
            int length = 0;
            boolean isBreak = false;

            while ((size = bis.read(buf)) != -1) {
                length += size;
                fos.write(buf, 0, size);
                if(listener!=null)
                {
                    listener.loading(total, length);
                    if(listener.isStop())
                    {
                        isBreak = true;
                        break;
                    }
                }
            }
            fos.close();
            bis.close();
            if(!isBreak)
            {
                FileUtil.copyFile(path+".tmp", path);
                isOK = true;
            }
            else
            {
                isOK = false;
            }
            FileUtil.delFile(path+".tmp");

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }


        return isOK;
    }

    public static byte[] getHttpData(String destUrl, HttpListener listener, boolean isbreak)
    {
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        byte[] data = null;

        try {

            HttpURLConnection.setFollowRedirects(true);
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();

            bis = new BufferedInputStream(httpUrl.getInputStream());

            ByteArrayOutputStream fos = new ByteArrayOutputStream();

            int total = httpUrl.getContentLength();
            int length = 0;

            while ((size = bis.read(buf)) != -1) {
                length += size;
                fos.write(buf, 0, size);
                if(isbreak)
                {
                    break;
                }
                if(listener!=null)
                {
                    listener.loading(total, length);
                }
            }
            buf = null;
            data = fos.toByteArray();
            fos.close();
            bis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }

        return data;
    }


    public static String saveHttpCache(String url, String cachePath, boolean isUseCach, HttpListener listener) {

        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        byte[] buf = new byte[BUFFER_SIZE];
        String path = null;
        int size = 0;
        String name = CodeUtil.md5(url);
        new File(cachePath).mkdirs();
        File file = new File(cachePath+name);
        try {

            boolean isHaveCache = false;
            if(isUseCach)
            {
                if(file.exists())
                {
                    isHaveCache = true;
                }
            }
            else
            {
                file.delete();
            }

            if(!isHaveCache)
            {
                FileUtil.delFile(cachePath+name+"_tmp");

                HttpURLConnection.setFollowRedirects(true);
                httpUrl = (HttpURLConnection) new URL(url).openConnection();
//                httpUrl.setRequestProperty("Accept-Encoding", "identity");
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                long total = httpUrl.getContentLength();
                FileOutputStream fos = new FileOutputStream(cachePath+name+"_tmp");
                long length = 0;
                boolean isBreak = false;
                while ((size = bis.read(buf)) != -1) {
                    fos.write(buf, 0, size);
                    length += size;

//                    MyLog.debug("saveHttpCache---:total:"+total);
//                    MyLog.debug("saveHttpCache---:length:"+length);


                    if(listener!=null)
                    {
                        listener.loading(total,length);
                        if(listener.isStop())
                        {
                            isBreak = true;
                            break;
                        }
                    }
                }
                fos.close();
                bis.close();
                if(!isBreak)
                {
                    FileUtil.copyFile(cachePath+name+"_tmp",cachePath+name);
                }
                FileUtil.delFile(cachePath+name+"_tmp");
            }

        } catch (Exception e) {
            FileUtil.delFile(cachePath+name+"_tmp");
            e.printStackTrace();
        }
        finally
        {
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
        if(file.exists())
        {
            path = cachePath+name;
        }

        return path;
    }


}
