package com.moioio.android.easyui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.easyui.widget.MyView;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;


public abstract class PopUpView extends MyView {

    private int viewWidth = 0;
    private int viewHeight = 0;
    private LayoutParams layout_params;
    private RelativeLayout layout;
    private PopUp popUp;

    public PopUpView(Context context) {
        super(context);
    }

    @Override
    public void initPage(Context context) {

        this.setBackgroundColor(UIConf.POPUP_BG_COLOR);
        layout = new RelativeLayout(context);
//        layout.setBackgroundDrawable(ViewUtil.makeRoundDrawable(GradientDrawable.Orientation.BL_TR,30,new int[]{0xFF110735,0xFF1A0040}));
//        layout.setBackgroundDrawable(ViewUtil.makeRoundDrawable(30,0xFF202020));//

        layout.setBackgroundDrawable(ViewUtil.makeRoundDrawableBord(30, DisplayUtil.getDip(getContext(),1), UIConf.POPUP_BORDER_COLOR, UIConf.POPUP_COLOR));//



        ViewUtil.setViewShadow(layout,20, DisplayUtil.getDip(context,10), Color.BLACK);

        layout_params = new MyLayout(viewWidth,viewHeight)
            .addRule(RelativeLayout.CENTER_IN_PARENT).get();
        layout.setLayoutParams(layout_params);
        super.addView(layout);


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popUp!=null)
                {
                    popUp.cancel();
                }
            }
        });

        layout.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setViewSize(int w,int h)
    {
        this.viewWidth = w;
        this.viewHeight = h;

        layout_params.width = w;
        layout_params.height = h;
        layout.setLayoutParams(layout_params);
        initView(getContext());
    }

    public void addView(View view)
    {
        layout.addView(view);
    }


    public abstract void initView(Context context);


    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public PopUp getPopUp() {
        return popUp;
    }

    public void setPopUp(PopUp popUp) {
        this.popUp = popUp;
    }

    public void onShow()
    {

    }

    public void onCancel()
    {

    }


}
