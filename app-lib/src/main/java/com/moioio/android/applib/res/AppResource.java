package com.moioio.android.applib.res;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.moioio.android.util.BitmapUtil;
import com.moioio.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

public class AppResource
{
    public static String str_many_sound;

    public static String HTTP = "http";
    public static String LOCAL = "local:";
    public static String NET = "net:";
    public static String LOCAL_BASE = "assets://db/";
    static String assets = "assets://";
    static String glide_assets = "file:///android_asset/";
    static Context context;

    public static void init(Context context)
    {
        AppResource.context = context;
//        defaultFont = Typeface.createFromAsset(context.getAssets(),"font/default.ttf");
    }


//    private static Typeface defaultFont;
//    public static Typeface getDefaultFont()
//    {
//        return defaultFont;
//    }


    private static Hashtable<String,Bitmap> cacheImages = new Hashtable<>();
    public static Bitmap getUIBitmap(String name)
    {
        Bitmap bitmap = null;
        String path = assets+"ui/"+name+".png";
        if(!cacheImages.containsKey(path))
        {
            bitmap = BitmapUtil.getAssetsBitmap(context,path);
            cacheImages.put(path,bitmap);
        }
        bitmap = cacheImages.get(path);
//        MyLog.debug("getUIBitmap:::"+path+"::::"+bitmap);
        return bitmap;
    }

    public static void loadUIToView(String name,ImageView imageView)
    {
        String path = glide_assets+"ui/"+name+".png";
        loadToView(context,path,imageView);
    }
    public static void loadUIToBitmap(String name,BitmapLoadListener loadListener)
    {
        String path = glide_assets+"ui/"+name+".png";
        loadToBitmap(context,path,loadListener);
    }




    public static Bitmap getBitmap(String name)
    {
        Bitmap bitmap = null;
        String path = assets+""+name;
        if(!cacheImages.containsKey(path))
        {
            bitmap = BitmapUtil.getAssetsBitmap(context,path);
            cacheImages.put(path,bitmap);
        }
        bitmap = cacheImages.get(path);

        return bitmap;
    }



    public static String getString(int id, String def)
    {
        String ret = StringUtil.STRING;
        ret = context.getString(id);
        if(ret==null)
        {
            ret = def;
        }
        return ret;
    }

    public static String getString(int id)
    {
        String ret = StringUtil.STRING;
        ret = context.getString(id);
        return ret;
    }





    public static String getResUrl(String url)
    {
        String path = null;

        if(url!=null)
        {
            if(url.toLowerCase().startsWith(HTTP))
            {
                path = url;
            }
            else if(url.toLowerCase().startsWith(LOCAL))
            {
                String md5 = url.substring(url.indexOf(':')+1);
                path = LOCAL_BASE+md5;
            }
//                if(url.startsWith(LOCAL))
//                {
//                }
//                else if(url.startsWith(NET))
//                {
//                    String baseUrl = AppResource.host_url+"/db/";
//                    path = baseUrl+md5;
//                }
//            }
        }
//        MyLog.debug(path);
        return path;
    }

    public static void loadToView(Context context, String path, ImageView bgView) {
        try {
            Glide.with(context).load(path).into(bgView);
        }
        catch (Exception e)
        {

        }
    }



    public static void loadToView(Context context, File file, ImageView bgView) {
        try {
            Glide.with(context).load(file).into(bgView);
        }
        catch (Exception e)
        {

        }
    }

    public static void thumbnailToView(Context context, String url, ImageView imageView) {
        try {
            Glide.with(context).load(url).thumbnail(0.1f).into(imageView);
        }
        catch (Exception e)
        {

        }

    }

    static HashMap<String,String> picLoaders = new HashMap<>();


    public static void loadToBitmap(Context context, String url, BitmapLoadListener loadListener) {
//        MyLog.debug("loadToBitmap:"+url);

        if(context==null)
        {
            return;
        }
        try {
            Glide.with(context).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap bitmap,  Transition<? super Bitmap> transition) {
                    try {
                        if(loadListener!=null)
                        {
                            loadListener.onLoad(bitmap);
                        }

                    }
                    catch (Exception ee)
                    {

                    }
                }
            });
        }
        catch (Exception e)
        {

        }


//        boolean isCache = ResourceLoader.isCached(context,url,"",false);
//
//        if(isCache)
//        {
//            File file = ResourceLoader.getCacheFile(context,url,"",false);
//            Glide.with(context).asBitmap().load(file).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap bitmap,  Transition<? super Bitmap> transition) {
//                    if(loadListener!=null)
//                    {
//                        loadListener.onLoad(bitmap);
//                    }
//                }
//            });
//        }
//        else
//        {
//            if(picLoaders.containsKey(url))
//            {
//                return;
//            }
//            picLoaders.put(url,"");
//            ResourceLoader.loadFileData(context, url, "", false, new ResourceLoaderListener() {
//                @Override
//                public boolean isCancel() {
//                    return false;
//                }
//
//                @Override
//                public boolean isLoading() {
//                    return false;
//                }
//
//                @Override
//                public void startLoading() {
//
//                }
//
//                @Override
//                public void endLoading() {
//
//                }
//
//                @RequiresApi(api = Build.VERSION_CODES.N)
//                @Override
//                public void onFinish(String url, int type, String path) {
//                    picLoaders.remove(url,"");
//
//                }
//
//                @Override
//                public void loading(long total, long current) {
//
//                }
//            });
//        }






    }

    public static Bitmap getBitmap(int id) {

        return BitmapFactory.decodeResource(context.getResources(),id);

    }


    public interface BitmapLoadListener
    {
        void onLoad(Bitmap bitmap);
    }

//    public static String getResMD5(String url)
//    {
//        String md5 = null;
//
//        try {
//            if(url!=null)
//            {
//                String file = url.substring(url.indexOf(':')+1);
//                md5 = file.substring(0,file.lastIndexOf("."));
//            }
//        }
//        catch (Exception e)
//        {
//
//        }
//        MyLog.debug(md5);
//        return md5;
//    }

//
//    public static Bitmap getSoundDataIcon(Context context, SoundData data) {
//
//        String icon = data.getIcon();
//       return getSoundDataIcon(context,data.getIconMD5(),icon);
//    }
//
//    public static Bitmap getSoundDataIcon(Context context,String md5, String icon) {
//
//        String url = getResUrl(icon);
//        return getIcon(context,md5,url,true);
//    }
//
//
//    public static Bitmap getIcon(Context context,String md5, String url,boolean isUseCache) {
//        if(url==null)
//        {
//            return null;
//        }
//        Bitmap bmp = null;
//        if(ResourceLoader.isLocal(context,url))
//        {
//            bmp = BitmapUtil.getAssetsBitmap(context,url);
//        }
//        else
//        {
//            String cachePath = ResourceLoader.getCacheFile(context,md5,url).getPath();
////            MyLog.debug("getIcon:::"+cachePath);
////            if(!isUseCache)
////            {
////                cachePath = url;
////            }
//            bmp = BitmapUtil.getFileBitmap(cachePath);
//        }
//        return bmp;
//    }
//
//
//
//    public static boolean saveNetDB(Context context,byte[] data)
//    {
//        boolean isOk = true;
//        byte[] old_data = AppFileUtil.readAppFileData(context,"mydb");
//
//        String md5_1 = CodeUtil.getByteMD5(old_data);
//        String md5_2 = CodeUtil.getByteMD5(data);
////        MyLog.debug("md5_1:::"+md5_1);
////        MyLog.debug("md5_2:::"+md5_2);
//        if(md5_1.equals(md5_2))
//        {
//            isOk = false;
//        }
//
//
//        AppFileUtil.writeAppFileData(context,"mydb",data);
//
//
//        return isOk;
//    }
//
//    public static void loadIcon(Context context,String url,String md5,  LoadBitmapListener loadBitmapListener)
//    {
//        if(StringUtil.isNull(url))
//        {
//            return;
//        }
//
////        MyLog.debug("loadIcon---::: "+url);
//
//        ResourceLoader.loadFileData(context, url,md5, new ResourceLoaderListener() {
//            @Override
//            public boolean isCancel() {
//                return loadBitmapListener.isCancel();
//            }
//
//            @Override
//            public boolean isLoading() {
//                return loadBitmapListener.isLoading();
//            }
//
//            @Override
//            public void startLoading() {
//                loadBitmapListener.startLoading();
//            }
//
//            @Override
//            public void endLoading() {
//                loadBitmapListener.endLoading();
//            }
//
//            @Override
//            public void onFinish(String url, int type, String path) {
//
//                if(type==ResourceLoader.NO_FILE)
//                {
//                    loadBitmapListener.onError();
//                }
//                else
//                {
//                    Bitmap bitmap = getIcon(context,md5,path,false);
//                    loadBitmapListener.onFinish(bitmap);
//                }
//            }
//
//            @Override
//            public void loading(long total, long current) {
//            }
//        });
//    }
//
//    public static void loadSoundIcon(Context context, SoundData soundData, LoadBitmapListener loadBitmapListener) {
//
//        String url = getResUrl(soundData.getIcon());
//        String md5 = soundData.getIconMD5();//getResMD5(soundData.getIcon());
//
//        loadIcon(context,url,md5,loadBitmapListener);
//    }
//
//
//    public static void loadCollectionIcon(Context context, CollectionData collectionData, LoadBitmapListener loadBitmapListener) {
//
//        String url = getResUrl(collectionData.getIcon());
//        String md5 = collectionData.getIconMD5();//getResMD5(collectionData.getIcon());
//
//        loadIcon(context,url,md5,loadBitmapListener);
//    }
//
//    public static boolean isSoundCached(Context context, SoundData data) {
//
//        boolean isOk = false;
//        String url = getResUrl(data.getUrl());
//
//        isOk = ResourceLoader.isCached(context,data.getMd5(),url);
//
//        return isOk;
//    }
//
//
//    public static byte[] loadCategoryDBCache(Context context)
//    {
//        byte[] data = null;
//        try
//        {
//            FileInputStream fis = context.openFileInput("cate.data");
//            DataInputStream dis = new DataInputStream(fis);
//            data = new byte[dis.readInt()];
//            dis.read(data);
//            dis.close();
//            fis.close();
//        }
//        catch (Exception e)
//        {
//            MyLog.printStackTrace(e);
//        }
//        return data;
//    }
//
//
//    public static void saveCategoryDBCache(Context context,byte[] data)
//    {
//        try {
//            FileOutputStream fos = context.openFileOutput("cate.data",Context.MODE_PRIVATE);
//            DataOutputStream dos = new DataOutputStream(fos);
//            dos.writeInt(data.length);
//            dos.write(data);
//            dos.close();
//            fos.close();
//        }
//        catch (Exception e)
//        {
//            MyLog.printStackTrace(e);
//        }
//    }

}
