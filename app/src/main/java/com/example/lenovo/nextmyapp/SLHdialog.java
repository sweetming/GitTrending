package com.example.lenovo.nextmyapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lenovo on 2017/5/21.
 */
public class SLHdialog extends Dialog implements View.OnClickListener {

    private SLHDialogListener listener;
    private Context context;
    private TextView tv1,tv2,tv3,tv4,tv5;
    public interface SLHDialogListener{
        public void onClick(View view);
    }

    public SLHdialog(Context context) {
        super(context);
    }

    public SLHdialog(Context context, int themeResId,SLHDialogListener listener) {
        super(context, themeResId);
        this.context=context;
        this.listener=listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {
        this.setContentView(R.layout.slhdialog);
        tv1=(TextView)findViewById(R.id.slh_ll1);
        tv2=(TextView)findViewById(R.id.slh_ll2);
        tv3=(TextView)findViewById(R.id.slh_ll3);
        tv4=(TextView)findViewById(R.id.slh_ll4);
        tv5=(TextView)findViewById(R.id.slh_ll5);

        tv1.setOnClickListener(this);tv2.setOnClickListener(this);tv3.setOnClickListener(this);tv4.setOnClickListener(this);tv5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
   listener.onClick(view);
    }
}
