package com.moioio.android.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import java.util.ArrayList;

public class PermissionUtil extends Activity
{

    /**通讯录权限*/
    public static final String[] PERMISSION_CONTACTS = new String[]{
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS
    };

    /**电话*/
    public static final String[] PERMISSION_PHONE = new String[]{
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.ADD_VOICEMAIL
    };

    /**日历*/
    public static final String[] PERMISSION_CALENDAR = new String[]{
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };

    /**相机*/
    public static final String[] PERMISSION_CAMERA = new String[]{
            Manifest.permission.CAMERA
    };

    /**传感器*/
    public static final String[] PERMISSION_SENSORS = new String[]{
            Manifest.permission.BODY_SENSORS
    };

    /**位置*/
    public static final String[] PERMISSION_LOCATION = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    /**存储*/
    public static final String[] PERMISSION_FILE_STORAGE = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**麦克风*/
    public static final String[] PERMISSION_MICROPHONE = new String[]{
            Manifest.permission.RECORD_AUDIO
    };

    /**短信*/
    public static final String[] PERMISSION_SMS = new String[]{
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS
    };


    public static interface PermissionListener {

        void onGranted();

        void onDenied(String[] permissions);

        boolean isTryAgain();
    }


    private static PermissionListener permissionCallback;
    private static int REQUEST_PERMISSIONS = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        String[] permissions = getIntent().getStringArrayExtra("permissions");
        if (permissions != null && permissions.length > 0)
        {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
            {
                requestPermissions(permissions, REQUEST_PERMISSIONS);
            }
        }
        else
        {
            finish();
        }
    }





    public static boolean requestPermissions(Context context, String[] permissions, PermissionListener callback)
    {

        boolean isOK = false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            if (callback != null)
            {
                callback.onGranted();
            }
            isOK = true;
        }
        else
        {
            String[] deniedPermissions = getDeniedPermissions(context,permissions);
            if (deniedPermissions.length > 0)
            {
                startRequestPermissions(context, deniedPermissions, callback);
            }
            else
            {
                isOK = true;
                if (callback != null)
                {
                    callback.onGranted();
                }
            }
        }
        return isOK;
    }


    private static String[] getDeniedPermissions(Context context,String[] permissions)
    {
        ArrayList<String> list_permissions = new ArrayList<String>();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        {
            for (String permission : permissions)
            {
                if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                {
                    list_permissions.add(permission);
                }
            }
        }

        return  list_permissions.toArray(new String[list_permissions.size()]);
    }

    private static void startRequestPermissions(Context context, String[] permissions, PermissionListener callback)
    {
        Intent intent = new Intent(context, PermissionUtil.class);
        intent.putExtra("permissions", permissions);
        permissionCallback = callback;
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void onRequestPermissionsResult(int mRequestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(mRequestCode, permissions, grantResults);
        if (mRequestCode == REQUEST_PERMISSIONS)
        {
            String[] deniedPermissions = getDeniedPermissions(this,permissions);
            if (deniedPermissions.length > 0)
            {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                {
                    boolean isFinish = true;
                    if(permissionCallback!=null)
                    {
                        permissionCallback.onDenied(deniedPermissions);
                        if(permissionCallback.isTryAgain())
                        {
                            isFinish = false;
                            requestPermissions(deniedPermissions, REQUEST_PERMISSIONS);
                        }
                    }
                    if(isFinish)
                    {
                        finish();
                    }
                }
            }
            else
            {
                if(permissionCallback!=null)
                {
                    permissionCallback.onGranted();
                }
                finish();
            }
        }
    }

}
