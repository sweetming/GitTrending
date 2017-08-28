package com.example.lenovo.nextmyapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.anye.greendao.gen.UserDao;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/17.
 */

public class FavoritesActivity extends Activity {

    private LinearLayout ll;
    private TextView tv;
    private ListView lv;
    private SharedPreferences shared;
    private int sco;
    private SharedPreferences.Editor edit;
    private SystemBarTintManager tintManager;
    private UserDao mUserDao=MyApplication.getinstances().getDaoSession().getUserDao();
    private List<EntityMyFav> list;
    private List<User> listuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);
        shared=getSharedPreferences("color", Context.MODE_PRIVATE);
        edit = shared.edit();
        sco=0;
        sco=  shared.getInt("cname",0);
        list=new ArrayList<>();
        listuser=new ArrayList<>();
        init();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void init() {
        ll=(LinearLayout) findViewById(R.id.fav_title);
        tv=(TextView) findViewById(R.id.fav_back);
        lv = ((ListView) findViewById(R.id.fav_lv));

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager.setStatusBarTintEnabled(true);
        if(sco!=0){
        ll.setBackgroundResource(sco);}else {
            ll.setBackgroundResource(R.color.blue);
        }
       listuser= mUserDao.loadAll();

        for (int i=0;i<listuser.size();i++){
            EntityMyFav myFav =new EntityMyFav();
            myFav.bt=listuser.get(i).getBt();
            myFav.js=listuser.get(i).getJs();
            myFav.wz=listuser.get(i).getWz();
            list.add(myFav);
        }
        FavListadapter favListadapter = new FavListadapter(list,this);
        lv.setAdapter(favListadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(FavoritesActivity.this,WebActivity.class);
                 String wz= list.get(i).wz;
                intent.putExtra("key",wz);
                startActivity(intent);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
