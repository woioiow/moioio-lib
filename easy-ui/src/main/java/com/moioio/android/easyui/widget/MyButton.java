package com.moioio.android.easyui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moioio.android.easyui.UI;
import com.moioio.android.util.ViewUtil;

public class MyButton extends MyView{


    private TextView textView;

    public MyButton(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {
        textView = new TextView(context);

        textView.setLayoutParams(new MyLayout(UI.WRAP_CONTENT,UI.WRAP_CONTENT)
                .addRule(UI.CENTER_IN_PARENT).get());

        this.addView(textView);
    }


    public void setRoundDrawable(float radius,int bgcolor)
    {
        Drawable drawable = ViewUtil.makeRoundDrawable(radius,bgcolor);
        Drawable pressDrawable = ViewUtil.makeRoundDrawable(radius,ViewUtil.getAntiColor(bgcolor));
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

    public TextView getTextView()
    {
        return textView;
    }



    public void setText(String text)
    {
        textView.setText(text);
    }


}
