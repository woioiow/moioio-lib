package com.moioio.android.loader;

import android.content.Context;

import com.moioio.android.util.AppFileUtil;
import com.moioio.util.CodeUtil;
import com.moioio.util.FileUtil;
import com.moioio.util.HttpListener;
import com.moioio.util.MyLog;
import com.moioio.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class ResourceLoader {

    public static String URL_ASSETS = "assets://";
    public static String URL_HTTPS = "https://";
    public static String URL_HTTP = "http://";
    public static int NO_FILE = -1;
    public static int LOCAL_FILE = 0;
    public static int CACHE_FILE = 1;
    public static int NET_FILE = 2;



    public static byte[] getDecode(byte[] cipherData,String key)
    {
        try
        {
            SecureRandom random = new SecureRandom();
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("des");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("des");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            byte[] plainData = cipher.doFinal(cipherData);
            return plainData;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static File getCacheFile(Context context,String url,String md5,boolean isUseMd5)
    {
        String cachePath = AppFileUtil.makeCachePath(context,"cache");
        String path = cachePath+FileUtil.getCachFilePath(cachePath,url);
        if(isUseMd5)
        {
            path = cachePath+md5;
        }
        return new File(path);
    }

//    public static File getCacheFolder(Context context)
//    {
//        String cachePath = AppFileUtil.makeCachePath(context,"cache");
//        return new File(cachePath);
//    }

    public static boolean isCached(Context context,String url,String md5,boolean isUseMd5)
    {
        boolean isOK = false;
        isOK = getCacheFile(context,url,md5,isUseMd5).exists();
        return isOK;
    }

    public static boolean isLocal(Context context,String url)
    {
        boolean isOK = false;
        if(url.toLowerCase().startsWith(URL_ASSETS))
        {
            isOK = true;
        }
        return isOK;
    }

    public static void loadFileData(Context context,String url,String md5,boolean isUseMd5,ResourceLoaderListener listener)
    {
        if(StringUtil.isNull(url))
        {
            return;
        }

        if(!listener.isLoading())
        {
            listener.startLoading();

            new Thread(()->{

                try
                {

                    if(isLocal(context,url))
                    {
//                        MyLog.debug("loadFileData:::isLocal");
                        if(listener!=null)
                        {
                            listener.onFinish(url,LOCAL_FILE,url);
                            listener.endLoading();
                        }
                        return;
                    }

                    if(isCached(context,url,md5,isUseMd5))
                    {
//                        MyLog.debug("loadFileData:::isCached");
                        if(listener!=null)
                        {
                            String path = getCacheFile(context,url,md5,isUseMd5).getPath();
//                            MyLog.debug("loadFileData:::pathpathpathpath:::"+path);
                            listener.onFinish(url,CACHE_FILE,path);
                            listener.endLoading();
                        }
                        return;
                    }

                    if(url.toLowerCase().startsWith(URL_HTTP)||url.toLowerCase().startsWith(URL_HTTPS))
                    {

                        String cachePath = AppFileUtil.makeCachePath(context,"cache");
                        String path = saveHttpCache(url,md5,isUseMd5,cachePath,true,new HttpListener(){

                            @Override
                            public void loading(long total, long current) {
                                if(listener!=null)
                                {
                                    listener.loading(total,current);
                                }
                            }

                            @Override
                            public boolean isStop() {

                                if(listener!=null)
                                {
                                    return listener.isCancel();
                                }
                                return false;
                            }
                        });


                        if(path==null)
                        {
                            if(listener!=null)
                            {
                                listener.onFinish(url,NO_FILE,null);
                                listener.endLoading();
                            }
                            return;
                        }


//                        MyLog.debug("url:::"+url);
//                        MyLog.debug("md5:::"+md5);
//                        MyLog.debug("path:::"+path);

//                        MyLog.debug("loadFileData:::URL_HTTPURL_HTTPURL_HTTP:::"+path);
                        if(listener!=null)
                        {
                            boolean isOk = false;
                            if(isUseMd5)
                            {
                                String cmd5 = CodeUtil.getFileMD5(new File(path));
                                if(cmd5.equals(md5))
                                {
                                    isOk = true;
                                }
                            }
                            else
                            {
                                isOk = true;
                            }

                            if(isOk)
                            {
                                if(listener!=null)
                                {
                                    listener.onFinish(url,NET_FILE,path);
                                    listener.endLoading();
                                }
                            }
                            else
                            {
                                FileUtil.delFile(path);
                                if(listener!=null)
                                {
                                    listener.onFinish(url,NO_FILE,null);
                                    listener.endLoading();
                                }
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    MyLog.printStackTrace(e);
                }
            }).start();

        }
    }


    static int BUFFER_SIZE = 10*1024;

    public static String saveHttpCache(String url,String md5,boolean isUseMd5, String cachePath, boolean isUseCach, HttpListener listener) {

        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        byte[] buf = new byte[BUFFER_SIZE];
        String path = null;
        int size = 0;
        String name = CodeUtil.md5(url);
        if(isUseMd5)
        {
            name = md5;
        }
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
