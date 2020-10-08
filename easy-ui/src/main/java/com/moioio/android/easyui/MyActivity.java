package com.moioio.android.easyui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.moioio.util.MyLog;


public class MyActivity extends Activity {

    public Activity getActivity() {
        return this;
    }

    public Context getContext() {
        return this;
    }



    public void setWindowStatusBarTrans() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } catch (Exception e) {
            MyLog.printStackTrace(e);
        }
    }


    public static void openActivity(Context context, Class clz) {
        Intent intent = new Intent(context, clz);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        setWindowStatusBarColor(this);
//        String name = this.getPackageName()+"."+this.getLocalClassName();
//        MobclickAgent.onPageStart(name);
    }



    public void onResume()
    {
        super.onResume();
//        closeAndroidPDialog();
//        MobclickAgent.onResume(this);
    }

    public void onPause()
    {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    public void onDestroy()
    {
        super.onDestroy();
        String name = this.getPackageName()+"."+this.getLocalClassName();
//        MobclickAgent.onPageEnd(name);
    }

    public void setWindowStatusBarColor(Activity activity, int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
            }
        } catch (Exception e) {
            MyLog.printStackTrace(e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                } else {
//                    Toast.makeText(this, "权限已被用户拒绝", Toast.LENGTH_SHORT).show();
//                }
//                break;
//
//            default:
//                break;
//        }
    }


}
