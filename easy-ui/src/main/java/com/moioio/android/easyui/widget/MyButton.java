package com.moioio.android.easyui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moioio.android.easyui.UI;
import com.moioio.android.util.ViewUtil;

public class MyButton extends MyView{


    private MyTextView textView;
    private MyImageView imageView;

    public MyButton(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {
        textView = new MyTextView(context);
        imageView = new MyImageView(context);

        imageView.makeLayout(UI.WRAP_CONTENT,UI.WRAP_CONTENT)
                .addRule(UI.CENTER_IN_PARENT);

        textView.makeLayout(UI.WRAP_CONTENT,UI.WRAP_CONTENT)
                .addRule(UI.CENTER_IN_PARENT);


        this.addView(imageView);
        this.addView(textView);
    }


    public void setBitmap(Bitmap bitmap)
    {
        imageView.setImageBitmap(bitmap);
    }


    public void setRoundDrawable(float radius,int bgColor)
    {
        Drawable drawable = ViewUtil.makeRoundDrawable(radius,bgColor);
        Drawable pressDrawable = ViewUtil.makeRoundDrawable(radius,ViewUtil.getAntiColor(bgColor));
        this.setBackground(ViewUtil.getClickDrawable(pressDrawable,drawable));
    }



    public void setRoundDrawableBord(float radius, int size, int bordcolor, int color)
    {
        Drawable drawable = ViewUtil.makeRoundDrawableBord(radius,size,bordcolor,color);
        Drawable pressDrawable = ViewUtil.makeRoundDrawableBord(radius,size,ViewUtil.getAntiColor(bordcolor),ViewUtil.getAntiColor(color));;
        this.setBackground(ViewUtil.getClickDrawable(pressDrawable,drawable));
    }

    public void setTextColor(int color)
    {
        textView.setTextColor(ViewUtil.getClickColor(ViewUtil.getAntiColor(color),color));
    }

    public void setTextMargin(int margin)
    {
        RelativeLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        textView.setLayoutParams(params);



    }

    public void setTextMargin(int top,int bottom,int left,int right)
    {
        RelativeLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
        params.topMargin = top;
        params.bottomMargin = bottom;
        params.leftMargin = left;
        params.rightMargin = right;
        textView.setLayoutParams(params);
    }


    public void setImageMargin(int margin)
    {
        RelativeLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        imageView.setLayoutParams(params);
    }

    public void setImageMargin(int top,int bottom,int left,int right)
    {
        RelativeLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
        params.topMargin = top;
        params.bottomMargin = bottom;
        params.leftMargin = left;
        params.rightMargin = right;
        imageView.setLayoutParams(params);
    }



    public MyTextView getTextView()
    {
        return textView;
    }

    public MyImageView getImageView()
    {
        return imageView;
    }


    public void setText(String text)
    {
        textView.setText(text);
    }


}
