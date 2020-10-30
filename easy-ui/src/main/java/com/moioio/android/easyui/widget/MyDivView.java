package com.moioio.android.easyui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moioio.android.easyui.UI;
import com.moioio.android.easyui.UIConf;

public class MyDivView extends MyView {

    LinearLayout layout;
    public MyDivView(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {

        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER_VERTICAL);

        LayoutParams nameView_params = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT).get();
        layout.setLayoutParams(nameView_params);
        this.addView(layout);
    }

    public void setOuterMargins(int left, int top, int right, int bottom)
    {
        ((LayoutParams)layout.getLayoutParams()).setMargins(left,top,right,bottom);
    }


    public MyDivView setOrientation(int orientation)
    {
        layout.setOrientation(orientation);
        if(UI.HORIZONTAL==orientation)
        {
            layout.setGravity(Gravity.CENTER_VERTICAL);
        }
        else
        {
            layout.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        return this;
    }

    public MyDivView setDivGravity(int gravity)
    {
        layout.setGravity(gravity);
        return this;
    }


    public MyDivView addText(String name)
    {
        return addText(name,UIConf.WRAP_CONTENT,UIConf.WRAP_CONTENT,0);
    }

    public MyDivView addImage(Bitmap image, int width, int height)
    {
        return addImage(image,width,height,0);
    }

    public MyDivView addSubView(View view, int width, int height)
    {
        return addView(view,width,height,0);
    }

    public MyDivView addSubView(View view)
    {
        return addView(view,UIConf.WRAP_CONTENT,UIConf.WRAP_CONTENT,0);
    }

    public MyDivView setMargins(int margin)
    {
        return setMargins(margin,margin,margin,margin);
    }

    public MyDivView leftMargin(int margin)
    {
        setMargins(margin,-1,-1,-1);
        return this;
    }

    public MyDivView topMargin(int margin)
    {
        return setMargins(-1,margin,-1,-1);
    }

    public MyDivView rightMargin(int margin)
    {
        return setMargins(-1,-1,margin,-1);
    }

    public MyDivView bottomMargin(int margin)
    {

        return setMargins(-1,-1,-1,margin);
    }



    public MyDivView setTextSize(float size)
    {
        return setTextAttr(size, 0, null,null);
    }

    public MyDivView setTextColor(int color)
    {
        return setTextAttr(0, color, null,null);
    }

    public MyDivView setTextShader(Shader shader)
    {
        return setTextAttr(0, 0, shader,null);
    }


    private View getLastView()
    {
        View view = null;
        if(layout.getChildCount()>0)
        {
            view = layout.getChildAt(layout.getChildCount()-1);
        }
        return view;
    }

    private LinearLayout.LayoutParams getLastLayoutParams()
    {
        LinearLayout.LayoutParams layoutParams = null;
        if(layout.getChildCount()>0)
        {
            View view = layout.getChildAt(layout.getChildCount()-1);
            layoutParams = (LinearLayout.LayoutParams)view.getLayoutParams();
        }
        return layoutParams;
    }

    private TextView getLastTextView()
    {
        View view = null;
        if(layout.getChildCount()>0)
        {
            view = layout.getChildAt(layout.getChildCount()-1);
        }
        if(view instanceof TextView)
        {
            return (TextView)view;
        }
        return null;
    }


    public MyDivView setTextAttr(float size, int color, Shader shader, Drawable drawable)
    {
        TextView textView = getLastTextView();
        if(textView==null)
        {
            return this;
        }
        if(size>0)
        {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
        }
        if(color!=0)
        {
            textView.setTextColor(color);
        }
        if(shader!=null)
        {
            textView.getPaint().setShader(shader);
        }
        if(drawable!=null)
        {
            textView.setBackgroundDrawable(drawable);
        }
        return this;
    }



    public MyDivView setMargins(int left, int top, int right, int bottom)
    {
        View view = getLastView();
        LinearLayout.LayoutParams layoutParams = getLastLayoutParams();
        if(view==null||layoutParams==null)
        {
            return this;
        }

        if(left>=0)
        {
            layoutParams.leftMargin = left;
        }

        if(top>=0)
        {
            layoutParams.topMargin = top;
        }

        if(right>=0)
        {
            layoutParams.rightMargin = right;
        }

        if(bottom>=0)
        {
            layoutParams.bottomMargin = bottom;
        }
        view.setLayoutParams(layoutParams);

        return this;
    }

    public MyDivView addText(String name, int width, int height, int margin)
    {
        TextView textView = new TextView(getContext());
        textView.setSingleLine();
        textView.setText(name);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(margin,margin,margin,margin);
        textView.setLayoutParams(layoutParams);
        layout.addView(textView);
        return this;
    }

    public MyDivView addImage(Bitmap image, int width, int height, int margin)
    {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(image);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(margin,margin,margin,margin);
        imageView.setLayoutParams(layoutParams);
        layout.addView(imageView);
        return this;
    }

    public MyDivView addImage(Bitmap image, int index, int width, int height, int margin)
    {
        layout.removeViewAt(index);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(image);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(margin,margin,margin,margin);
        imageView.setLayoutParams(layoutParams);
        layout.addView(imageView,index);
        return this;
    }


    public MyDivView addView(View view, int width, int height, int margin)
    {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(margin,margin,margin,margin);
        view.setLayoutParams(layoutParams);
        layout.addView(view);
        return this;
    }

    public MyDivView addView(View view, int index, int width, int height, int margin)
    {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(margin,margin,margin,margin);
        view.setLayoutParams(layoutParams);
        layout.addView(view,index);
        return this;
    }


    public void clear() {
        layout.removeAllViews();
    }


//    public MyDivView setText(String text, int index)
//    {
//        TextView textView = getLastTextView();
//        if(textView==null)
//        {
//            return this;
//        }
//        textView.setText(text);
//        return this;
//    }

    public MyDivView setText(String text)
    {
        TextView textView = getLastTextView();
        if(textView==null)
        {
            return this;
        }
        textView.setText(text);
        return this;
    }

    public MyDivView setSingleLine(boolean singleLine)
    {
        TextView textView = getLastTextView();
        if(textView==null)
        {
            return this;
        }
        textView.setSingleLine(singleLine);
        return this;
    }

    public void setView(int index, View view) {

        layout.removeViewAt(index);
        addView(view,index, UIConf.WRAP_CONTENT,UIConf.WRAP_CONTENT,0);
    }

    public void setView(int index, View view,int width,int height,int margin) {
        layout.removeViewAt(index);
        addView(view,index,width,height,margin);
    }

    public View getView(int index)
    {
        View view = layout.getChildAt(index);
        return view;
    }

    public void removeView(int index)
    {
        layout.removeViewAt(index);
    }


}
