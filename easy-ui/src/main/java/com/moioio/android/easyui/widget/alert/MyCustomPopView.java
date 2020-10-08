package com.moioio.android.easyui.widget.alert;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moioio.android.easyui.UIConf;
import com.moioio.android.easyui.dialog.PopUp;
import com.moioio.android.easyui.dialog.PopUpView;
import com.moioio.android.easyui.widget.MyLayout;
import com.moioio.android.util.DisplayUtil;
import com.moioio.android.util.ViewUtil;


public class MyCustomPopView extends PopUpView {

    private TextView titleView;
    private RelativeLayout contentView;
    private LinearLayout btnsLayout;


    public MyCustomPopView(Context context) {
        super(context);
    }


    @Override
    public void initView(Context context) {

        int height = this.getViewHeight();

        titleView = new TextView(context);
        ViewUtil.setViewID(titleView);
        titleView.setTextColor(UIConf.ALERT_TITLE_COLOR);
//        titleView.setTextColor(Color.WHITE);


        contentView = new RelativeLayout(context);
        ViewUtil.setViewID(contentView);
//        contentView.setBackgroundColor(Color.RED);
//        contentView.setTextColor(UIConf.ALERT_MESSAGE_COLOR);

        btnsLayout = new LinearLayout(context);
        ViewUtil.setViewID(btnsLayout);



        int titleHeight = DisplayUtil.getDip(context,50);
        int margin = DisplayUtil.getDip(context,10);


        RelativeLayout.LayoutParams titleView_params = new MyLayout(UIConf.MATCH_PARENT,titleHeight)
                .addRule(RelativeLayout.ALIGN_PARENT_TOP)
                .addRule(RelativeLayout.ALIGN_PARENT_LEFT).get();
        titleView.setLayoutParams(titleView_params);

        int bottomHeight = DisplayUtil.getDip(context,60);


        RelativeLayout.LayoutParams btnsLayout_params = new MyLayout(UIConf.MATCH_PARENT,bottomHeight)
                .addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                .addRule(RelativeLayout.ALIGN_PARENT_LEFT).get();
        btnsLayout.setLayoutParams(btnsLayout_params);




        RelativeLayout.LayoutParams messageView_params = new MyLayout(UIConf.MATCH_PARENT, UIConf.WRAP_CONTENT)
                .addRule(RelativeLayout.BELOW,titleView.getId())
                .addRule(RelativeLayout.ABOVE,btnsLayout.getId())
                .setMargins(margin,margin,margin,margin).get();
        contentView.setLayoutParams(messageView_params);


//        titleView.setBackgroundColor(Color.BLUE);
//        messageView.setBackgroundColor(Color.RED);
//        btnsLayout.setBackgroundColor(Color.GREEN);


        this.addView(titleView);
        this.addView(btnsLayout);
        this.addView(contentView);

    }


    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setContentView(View view)
    {
        contentView.removeAllViews();
        contentView.addView(view);
//        messageView.setText(msg);
    }

    public void setIcon(Bitmap icon) {
    }

    public void addButton(String name, PopUp.OnClickListener onClickListener) {

        int size = btnsLayout.getChildCount();
        if(size<4)
        {
            Button button = new Button(getContext());
            button.setTextColor(UIConf.ALERT_BUTTON_TEXT_COLOR);
            button.setBackgroundDrawable(ViewUtil.makeRoundDrawableBord(30, DisplayUtil.getDip(getContext(),1), UIConf.ALERT_BUTTON_BORDER_COLOR, UIConf.ALERT_BUTTON_COLOR));
//            ViewUtil.setViewShadow(button,20,DisplayUtil.getDip(getContext(),5),Color.BLACK);


            button.setTag(onClickListener);
            button.setText(name);


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopUp.OnClickListener listener = (PopUp.OnClickListener)v.getTag();
                    if(listener!=null)
                    {
                        listener.onClick(getPopUp());
                    }
                    getPopUp().cancel();
                }
            });
            btnsLayout.addView(button);
            relayoutBtns();

        }
    }


    private void relayoutBtns()
    {
        int margin = DisplayUtil.getDip(getContext(),10);
        int size = btnsLayout.getChildCount();

        int bw = getViewWidth()/size;

        bw = bw-2*margin;

        for(int i=0;i<size;i++)
        {
            View view = btnsLayout.getChildAt(i);
            LinearLayout.LayoutParams view_params = new LinearLayout.LayoutParams(bw, UIConf.MATCH_PARENT);
            view_params.setMargins(margin,margin,margin,margin);
            view.setLayoutParams(view_params);
        }



    }

}
