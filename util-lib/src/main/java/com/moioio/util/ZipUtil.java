package com.moioio.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.moioio.util.StringUtil.STRING;
import static com.moioio.util.StringUtil.UTF_8;

public class ZipUtil
{



    public static byte[] getZipData(String zippath, String name)
    {
        // System.out.println("zippath:"+zippath);
        // System.out.println("name:"+name);
        byte[] data = null;
        try
        {
            // ZipInputStream jis = new ZipInputStream(new
            // FileInputStream(zippath));
            // ZipEntry jarEntry;
            // while ((jarEntry = jis.getNextEntry()) != null)
            // {
            // System.out.println(jarEntry.getName());
            // }
            // jis.close();

            ZipFile zipFile = new ZipFile(zippath);
            ZipEntry je = zipFile.getEntry(name);// (JarEntry)entries.nextElement();
            InputStream is = null;
            int BUFFER_SIZE = 8 * 1024;
            if (je != null && je.isDirectory() == false)
            {
                is = zipFile.getInputStream(je);
                int size = (int) je.getSize();
                if (size > 0)
                {
                    ByteArrayOutputStream fos = new ByteArrayOutputStream();
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((size = is.read(buf)) != -1)
                    {
                        fos.write(buf, 0, size);
                    }
                    data = fos.toByteArray();
                    fos.close();
                    is.close();
                    buf = null;
                }
            }
            zipFile.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }


    public static String readString(DataInputStream dis) {
        try {
            int size = dis.readInt();
            if (size == 0) {
                return STRING;
            }

            byte[] data = new byte[size];
            dis.read(data, 0, size);
            data = Gzip.uncompress(data);
            String s = new String(data, UTF_8);
            data = null;
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean writeString(DataOutputStream dos, String s) {
        try {
            if (s == null) {
                dos.writeInt(0);
                return true;
            }


            byte[] data = s.getBytes(UTF_8);
//            int size = data.length;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gz = new GZIPOutputStream(baos);
            gz.write(data, 0, data.length);
            gz.finish();
            byte[] data_ = baos.toByteArray();
            if (data_.length > data.length) {
                dos.writeInt(data.length);
                dos.write(data, 0, data.length);
            } else {
                dos.writeInt(data_.length);
                dos.write(data_, 0, data_.length);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }






}
