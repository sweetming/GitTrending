package com.example.lenovo.nextmyapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lenovo on 2017/5/9.
 */
public class ThemeDialog extends Dialog implements View.OnClickListener{

    private LeaveMyDialogListener listener;
    private Context context;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    public interface LeaveMyDialogListener{
        public void onClick(View view);
    }
    public ThemeDialog(Context context) {
        super(context);
    }

    public ThemeDialog(Context context, int themeResId,LeaveMyDialogListener listener) {
        super(context, themeResId);
        this.context=context;
        this.listener=listener;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        this.setContentView(R.layout.themedialog);
        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
        tv3=(TextView) findViewById(R.id.tv3);
        tv4=(TextView) findViewById(R.id.tv4);
        tv5=(TextView) findViewById(R.id.tv5);
        tv6=(TextView) findViewById(R.id.tv6);
        tv7=(TextView) findViewById(R.id.tv7);
        tv8=(TextView) findViewById(R.id.tv8);
        tv9=(TextView) findViewById(R.id.tv9);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
    }
}
