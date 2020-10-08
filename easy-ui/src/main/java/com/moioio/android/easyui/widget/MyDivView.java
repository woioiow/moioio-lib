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
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        RelativeLayout.LayoutParams nameView_params = new MyLayout(UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT).get();
        layout.setLayoutParams(nameView_params);
        this.addView(layout);

    }

    public MyDivView setOrientation(int orientation) {
        layout.setOrientation(orientation);
        if (LinearLayout.HORIZONTAL == orientation) {
            layout.setGravity(Gravity.CENTER_VERTICAL);
        } else {
            layout.setGravity(Gravity.CENTER_HORIZONTAL);
        }
        return this;
    }


    public MyDivView addText(String name) {
        return addText(name, UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT, 0);
    }

    public MyDivView addImage(Bitmap image, int width, int height) {
        return addImage(image, width, height, 0);
    }

    public MyDivView addSubView(View view, int width, int height) {
        return addView(view, width, height, 0);
    }

    public MyDivView addSubView(View view) {
        return addView(view, UIConf.WRAP_CONTENT, UIConf.WRAP_CONTENT, 0);
    }

    public MyDivView setMargins(int margin) {
        return setMargins(margin, margin, margin, margin);
    }

    public MyDivView leftMargin(int margin) {
        setMargins(margin, -1, -1, -1);
        return this;
    }

    public MyDivView topMargin(int margin) {
        return setMargins(-1, margin, -1, -1);
    }

    public MyDivView rightMargin(int margin) {
        return setMargins(-1, -1, margin, -1);
    }

    public MyDivView bottomMargin(int margin) {

        return setMargins(-1, -1, -1, margin);
    }


    public MyDivView setTextSize(float size) {
        return setTextAttr(size, 0, null, null);
    }

    public MyDivView setTextColor(int color) {
        return setTextAttr(0, color, null, null);
    }

    public MyDivView setTextShader(Shader shader) {
        return setTextAttr(0, 0, shader, null);
    }


    public MyDivView setTextAttr(float size, int color, Shader shader, Drawable drawable) {
        if (layout.getChildCount() > 0) {
            View view = layout.getChildAt(layout.getChildCount() - 1);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (size > 0) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                }
                if (color != 0) {
                    textView.setTextColor(color);
                }
                if (shader != null) {
                    textView.getPaint().setShader(shader);
                }
                if (drawable != null) {
                    textView.setBackgroundDrawable(drawable);
                }
            }
        }
        return this;
    }


    public MyDivView setMargins(int left, int top, int right, int bottom) {
        if (layout.getChildCount() > 0) {
            View view = layout.getChildAt(layout.getChildCount() - 1);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            if (left >= 0) {
                layoutParams.leftMargin = left;
            }

            if (top >= 0) {
                layoutParams.topMargin = top;
            }

            if (right >= 0) {
                layoutParams.rightMargin = right;
            }

            if (bottom >= 0) {
                layoutParams.bottomMargin = bottom;
            }

//            layoutParams.setMargins(left,top,right,bottom);
            view.setLayoutParams(layoutParams);
        }
        return this;
    }

    public MyDivView addText(String name, int width, int height, int margin) {
        TextView textView = new TextView(getContext());
        textView.setSingleLine();
        textView.setText(name);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(margin, margin, margin, margin);
        textView.setLayoutParams(layoutParams);
        layout.addView(textView);
        return this;
    }

    public MyDivView addImage(Bitmap image, int width, int height, int margin) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(image);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(margin, margin, margin, margin);
        imageView.setLayoutParams(layoutParams);
        layout.addView(imageView);
        return this;
    }

    public MyDivView addView(View view, int width, int height, int margin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(margin, margin, margin, margin);
        view.setLayoutParams(layoutParams);
        layout.addView(view);
        return this;
    }

    public void clear() {
        layout.removeAllViews();
    }


    public MyDivView setText(String text, int index) {
        if (layout.getChildCount() > 0) {
            View view = layout.getChildAt(index);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setText(text);
            }
        }
        return this;
    }

    public MyDivView setText(String text) {
        return setText(text, layout.getChildCount() - 1);
    }


}
