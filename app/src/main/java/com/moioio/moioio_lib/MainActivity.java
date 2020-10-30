package com.moioio.moioio_lib;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.moioio.util.MyLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Locale;
import java.util.UUID;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(new TestView(this));

//        MyLog.debug("Build.TAGS:::"+Build.TAGS);

//        getDeviceSimAndroidUUID(this);
//        getUUID(this);

    }

    public static String getDeviceSimAndroidUUID(Context context) {
        try {
            {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                String str = "";
                String deviceId = telephonyManager != null ? telephonyManager.getDeviceId() : str;
                String string = telephonyManager != null ? Settings.Secure.getString(context.getContentResolver(), "android_id") : str;
                if (telephonyManager != null) {
                    str = telephonyManager.getSimSerialNumber();
                }
                if (TextUtils.isEmpty(str)) {
                    str = "null";
                }
                MyLog.debug("deviceId::::"+deviceId);
                MyLog.debug("string::::"+string);
                MyLog.debug("str::::"+str);
                UUID uuid = new UUID((long) string.hashCode(), ((long) str.hashCode()) | (((long) deviceId.hashCode()) << 32));
                String uuids = uuid.toString();
                MyLog.debug(uuids);
            }
        } catch (Exception e) {
            MyLog.printStackTrace(e);
        }
        return "";
    }



    public static String getUUID(Context context) {
        String str;
        if (Build.VERSION.SDK_INT < 23) {
            str = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT < 24) {
            str = getMacFromFile();
        } else {
            str = getMacFromHardware();
        }
        if (TextUtils.isEmpty(str)) {
            str = "02:00:00:00:00:00";
        }
        MyLog.debug("mac::::"+str);
        String uuids = UUID.nameUUIDFromBytes(str.getBytes()).toString();
        MyLog.debug(uuids);

        return uuids;
    }

    private static String getMacFromHardware() {
        try {
            for (NetworkInterface t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = t.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacFromFile() {
        try {
            return new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "02:00:00:00:00:00";
        }
    }

    private static String getMacDefault(Context context) {
        WifiManager wifiManager;
        WifiInfo wifiInfo;
        if (context == null || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null) {
            return "02:00:00:00:00:00";
        }
        try {
            wifiInfo = wifiManager.getConnectionInfo();
        } catch (Exception unused) {
            wifiInfo = null;
        }
        if (wifiInfo == null) {
            return null;
        }
        String macAddress = wifiInfo.getMacAddress();
        return !TextUtils.isEmpty(macAddress) ? macAddress.toUpperCase(Locale.ENGLISH) : macAddress;
    }

}