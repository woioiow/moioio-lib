package com.moioio.android.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;

import com.moioio.util.MyLog;
import com.moioio.util.StreamUtil;
import com.moioio.util.StringUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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



    public static String makeSdcardPath( String name) {
        String path = "";

        String cachepath = Environment.getExternalStorageDirectory()+"";
//        cachepath = 	context.getExternalFilesDir(null).getPath();
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


    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {


                final String id = DocumentsContract.getDocumentId(uri);

                if (id != null && id.startsWith("raw:")) {
                    return id.substring(4);
                }

                String[] contentUriPrefixesToTry = new String[]{
                        "content://downloads/public_downloads",
                        "content://downloads/my_downloads"
                };

                String path = null;
                for (String contentUriPrefix : contentUriPrefixesToTry) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse(contentUriPrefix), Long.valueOf(id));
                    try {
                        String path_ = getDataColumn(context, contentUri, null, null);
                        if (!StringUtil.isNull(path_)) {
                            path = path_;
                        }
                    } catch (Exception e) {
                    }
                }
                MyLog.debug("path---:"+path);
                if(StringUtil.isNull(path))
                {
                    String fileName = getFileName(context, uri);
                    MyLog.debug("fileName---:"+fileName);
                    File cacheDir = getDocumentCacheDir(context);
                    MyLog.debug("cacheDir---:"+cacheDir.getPath());
                    File file = generateFileName(fileName, cacheDir);
                    MyLog.debug("file---:"+file.getPath());
                    String destinationPath = null;
                    if (file != null) {
                        destinationPath = file.getAbsolutePath();
                        path = destinationPath;
                        saveFileFromUri(context, uri, destinationPath);
                    }
                }


                return path;
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static final String DOCUMENTS_DIR = "documents";

    public static File generateFileName( String name, File directory) {
        if (name == null) {
            return null;
        }

        File file = new File(directory, name);

        if (file.exists())
        {
            String fileName = name;
            String extension = "";
            int dotIndex = name.lastIndexOf('.');
            if (dotIndex > 0) {
                fileName = name.substring(0, dotIndex);
                extension = name.substring(dotIndex);
            }

            int index = 0;

            while (file.exists()) {
                index++;
                name = fileName + '(' + index + ')' + extension;
                name = name.trim();
                file = new File(directory, name);
            }
        }

        try {
            if (!file.createNewFile()) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        return file;
    }


    private static void saveFileFromUri(Context context, Uri uri, String destinationPath) {
        InputStream is = null;
        BufferedOutputStream bos = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            bos = new BufferedOutputStream(new FileOutputStream(destinationPath, false));
            byte[] buf = new byte[1024];
            is.read(buf);
            do {
                bos.write(buf);
            } while (is.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFileName( Context context, Uri uri) {
        String mimeType = context.getContentResolver().getType(uri);
        String filename = null;

        if (mimeType == null && context != null) {
            String path = getPathFromUri(context, uri);
            if (path == null) {
                filename = getName(uri.toString());
            } else {
                File file = new File(path);
                filename = file.getName();
            }
        } else {
            Cursor returnCursor = context.getContentResolver().query(uri, null,
                    null, null, null);
            if (returnCursor != null) {
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                filename = returnCursor.getString(nameIndex);
                returnCursor.close();
            }
        }

        return filename;
    }


    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        int index = filename.lastIndexOf('/');
        return filename.substring(index + 1);
    }

    public static File getDocumentCacheDir(Context context) {
        File dir = new File(context.getCacheDir(), DOCUMENTS_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        }
        catch (Exception e)
        {
//            e.printStackTrace();
        }
        finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }





}
