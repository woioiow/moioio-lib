package com.moioio.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

public class FileSelectorUtil extends Activity
{

    private static FileSelectListener fileSelectCallback;
    private static final int FILE_SELECT = 666;

    public static interface FileSelectListener
    {

        void onSelect(Uri[] uris);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        String title = getIntent().getStringExtra("title");
        String type = getIntent().getStringExtra("type");
        boolean isMult = getIntent().getBooleanExtra("isMult",false);
        Intent chooser = new Intent(Intent.ACTION_GET_CONTENT);
        chooser.setType(type);
        if (isMult)
        {
            chooser.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        Intent intent = Intent.createChooser(chooser, title);
        startActivityForResult(intent, FILE_SELECT);
    }

    public static void selectFile(Context context, String title, String type, boolean isMult, FileSelectListener fileSelectListener)
    {
        fileSelectCallback = fileSelectListener;
        Intent intent = new Intent(context, FileSelectorUtil.class);
        intent.putExtra("title",title);
        intent.putExtra("type",type);
        intent.putExtra("isMult", isMult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&requestCode==FILE_SELECT&& null != data)
        {
            ArrayList<Uri> uris = new ArrayList<Uri>();
            if (null != data.getClipData())
            {
                for (int i = 0; i < data.getClipData().getItemCount(); i++)
                {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    uris.add(uri);
                }
            }
            else
            {
                Uri uri = data.getData();
                uris.add(uri);
            }

            if(fileSelectCallback!=null)
            {
                fileSelectCallback.onSelect(uris.toArray(new Uri[uris.size()]));
            }
        }
        fileSelectCallback = null;
        finish();
    }



}
