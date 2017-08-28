package com.example.lenovo.nextmyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：NextMyApp
 * 类描述：
 * 创建人：lenovo
 * 创建时间：2017/8/20 6:43
 * 修改人：lenovo
 * 修改时间：2017/8/20 6:43
 * 修改备注：
 */
public class LoginActivity extends Activity {

    private RelativeLayout rlbz;
    private TextView tv;
    private Handler handler=null;
    private Runnable run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_view);
        rlbz=(RelativeLayout)findViewById(R.id.bj_rl);
        tv= ((TextView) findViewById(R.id.login_btn));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler=null;
                startActivity(new Intent(LoginActivity.this, MainActivity.class));


                LoginActivity.this.finish();
                if(run!=null){
                    handler.removeCallbacks(run);
                }

            }
        });
        Integer time = 4000;    //设置等待时间，单位为毫秒

        if(getCurrentTime()){
            rlbz.setBackgroundResource(R.drawable.bzzz);
        }else {
            rlbz.setBackgroundResource(R.drawable.bz);

        }
        handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(run=new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }
        }, time);

    }

    public boolean getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hour= sdf.format(new Date());
        int k  = Integer.parseInt(hour)  ;
        if ((k>=0 && k<6) ||(k >=18 && k<24)){
            return true;
        } else {
            return false;
        }
    }
}

