package com.moioio.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil
{


    public static String getCachFilePath(String cachePath,String url)
    {
        String name = CodeUtil.md5(url);
        String path = cachePath+name;
        return path;
    }

    public static void copyFile(String oldPath, String newPath) {
        try {
            File file = new File(newPath);
            file = file.getParentFile();
            if(file!=null)
            {
                if(!file.exists())
                {
                    file.mkdirs();
                }
            }
            FileInputStream input = new FileInputStream(oldPath);
            FileOutputStream output = new FileOutputStream(newPath);
            byte[] b = new byte[UtilConf.BUFFER_SIZE];
            int len;
            while ((len = input.read(b)) != -1) {
                output.write(b, 0, len);
            }
            output.flush();
            output.close();
            input.close();
        } catch (Exception e) {
//            MyLog.printStackTrace(e);
        }
    }

    public static byte[] readFileData(String name) {
        try {
            FileInputStream file = new FileInputStream(name);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[UtilConf.BUFFER_SIZE];
            int len;
            while ((len = file.read(b)) != -1) {
                bos.write(b, 0, len);
            }

            byte[] bs = bos.toByteArray();
            bos.close();
            file.close();
            return bs;
        } catch (Exception e) {
//            MyLog.printStackTrace(e);
        }
        return null;
    }

    public static boolean writeFileData(String path, byte[] data)
    {
        if(data==null)
        {
            return false;
        }
        return writeFileData(path,data,0,data.length);
    }

    public static boolean writeFileData(String path, byte[] data, int offset, int size)
    {
        if(data==null)
        {
            return false;
        }
        try
        {
            File localFile = new File(path);
            if (!(localFile.getParentFile().exists()))
            {
                localFile.getParentFile().mkdirs();
            }
            FileOutputStream localFileOutputStream = new FileOutputStream(path);
            localFileOutputStream.write(data, offset, size);
            localFileOutputStream.close();
            return true;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return false;
    }



    public static void copyFolder(File oldFile, File newFile)
    {
        copyFolder(oldFile.getPath(),newFile.getPath());
    }


    public static void copyFolder(String oldPath, String newPath)
    {
        try {
            File a = new File(oldPath);
            if (a.isFile())
            {
                File f = new File(newPath);
                if (!f.exists()) {
                    f.mkdirs();
                }


                FileInputStream input = new FileInputStream(a);
                File b_ = new File(newPath + "/"
                        + (a.getName()).toString());
                if (!b_.exists()) {
                    b_.createNewFile();
                }
                FileOutputStream output = new FileOutputStream(newPath + "/"
                        + (a.getName()).toString());
                byte[] b = new byte[UtilConf.BUFFER_SIZE];
                int len;
                while ((len = input.read(b)) != -1) {
                    output.write(b, 0, len);
                }
                output.flush();
                output.close();
                input.close();
            }
            else
            {
                (new File(newPath)).mkdirs();
                String[] file = a.list();
                File temp = null;
                for (int i = 0; i < file.length; i++) {
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + file[i]);
                    } else {
                        temp = new File(oldPath + File.separator + file[i]);
                    }

                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath + "/"
                                + (temp.getName()).toString());
                        byte[] b = new byte[UtilConf.BUFFER_SIZE];
                        int len;
                        while ((len = input.read(b)) != -1) {
                            output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    if (temp.isDirectory()) {
                        copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                    }
                }
            }
        } catch (Exception e) {
            //   MyLog.printStackTrace(e);
        }
    }


    public static boolean isExists(String path) {

        return new File(path).exists();
    }


    public static boolean delFolder(String path)
    {
        return delFolder(new File(path));
    }

    public static boolean delFolder(File folder) {


        boolean isOK = false;

        delAllFile(folder);

        isOK = delFile(folder);

        return isOK;

    }

    public static boolean delFile(File file)
    {
        boolean isOK = false;

        if(file.exists())
        {
            isOK = file.delete();
        }

        return isOK;
    }

    public static boolean delFile(String path) {

        return delFile(new File(path));
    }

    private static void delAllFile(File folder) {
        File file = folder;
        String path = folder.getPath();

        if (!file.exists()) {
            return;
        }

        if(file.isDirectory())
        {
            File[] subFiles = file.listFiles();
            if(subFiles!=null)
            {
                for(File sub:subFiles)
                {
                    if(sub.isFile())
                    {
                        delFile(sub);
                    }
                    else if(sub.isDirectory())
                    {
                        delFolder(sub);
                    }
                }
            }
        }
    }



}
