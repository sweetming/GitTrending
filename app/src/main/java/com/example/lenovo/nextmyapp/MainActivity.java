package com.example.lenovo.nextmyapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout toolbar_zdy;
    private ImageView main_cd,main_star,main_slh;
    private TabLayout myteb;
    private ViewPager myvp;

    private DrawerLayout mdrawerlayout;
    private SLHdialog slHdialog;

    private TextView dl_tv;
    private List<String> mTitle = new ArrayList<String>();
    private String[] Ltitles = {"All Languages", "Java", "JavaScript", "HTML", "Python"};
    private Fragment[] LtItletext = {new Frgments(""), new Frgments("java"), new Frgments("javascript"), new Frgments("html"), new Frgments("python")};
    private List<Fragment> mFragment = new ArrayList<Fragment>();
    private ThemeDialog dialog;
    private SystemBarTintManager tintManager;
    private int colorsss = R.color.blue;
    private Handler mhandler;
    private SharedPreferences shared;
    private int sco;
    private SharedPreferences.Editor edit;
    private TextView tvday, tvweek, tvmouth, dl_fav, dl_theme;
    private String[] listtimes = {"since=daily", "since=weekly", "since=monthly"};
    private VPadapter vPadapter;
    private FragmentManager fm;
    private int getint = 1;
    private Frgments bs, bs1, bs2, bs3, bs4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = getSharedPreferences("color", Context.MODE_PRIVATE);
        edit = shared.edit();
        sco = 0;
        sco = shared.getInt("cname", 0);

        initview();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
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

    private void initview() {
        fm = getSupportFragmentManager();
        // 4.4及以上版本开启
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager.setStatusBarTintEnabled(true);

//- -  - - - -!
        toolbar_zdy=(LinearLayout)findViewById(R.id.toolbar_zdy);

        main_star=(ImageView)findViewById(R.id.main_star);
        main_star.setOnClickListener(this);

        main_slh=(ImageView)findViewById(R.id.main_slh);
        main_slh.setOnClickListener(this);
        main_cd=(ImageView)findViewById(R.id.main_cd);
        main_cd.setOnClickListener(this);
        tvday = (TextView) findViewById(R.id.tvtoday);
        tvweek = (TextView) findViewById(R.id.tvweek);
        tvmouth = (TextView) findViewById(R.id.tvmonth);
        dl_fav = (TextView) findViewById(R.id.dl_fav);
        dl_theme = ((TextView) findViewById(R.id.dl_theme));
        tvday.setOnClickListener(this);
        tvmouth.setOnClickListener(this);
        tvweek.setOnClickListener(this);
        dl_fav.setOnClickListener(this);
        dl_theme.setOnClickListener(this);
        myteb = (TabLayout) findViewById(R.id.popular_tab);
        myvp = (ViewPager) findViewById(R.id.popular_vp);

        myteb.setTabGravity(TabLayout.GRAVITY_FILL);
        myteb.setTabMode(TabLayout.MODE_SCROLLABLE);
        dl_tv = ((TextView) findViewById(R.id.dl_tv));
        dl_tv.setOnClickListener(this);

        mdrawerlayout = ((DrawerLayout) findViewById(R.id.dl_left));
        // drawerList= ((ListView) findViewById(R.id.lv_left_menu));

        // colorsss=((ColorDrawable)toolbar.getBackground()).getColor();



        vPadapter = new VPadapter(getSupportFragmentManager(), Ltitles, LtItletext);
        myvp.setAdapter(vPadapter);
        myvp.setOffscreenPageLimit(5);
        myteb.setupWithViewPager(myvp);
        myteb.setTabsFromPagerAdapter(vPadapter);
        if (sco != 0) {
            colorsss = sco;
            toolbar_zdy.setBackgroundResource(colorsss);

            myteb.setBackgroundResource(colorsss);
            dl_tv.setBackgroundResource(colorsss);
            Log.e("onCreate: ", colorsss + "" + sco + " ");
        }

        bs = (Frgments) vPadapter.getItem(0);
        bs1 = (Frgments) vPadapter.getItem(1);
        bs2 = (Frgments) vPadapter.getItem(2);
        bs3 = (Frgments) vPadapter.getItem(3);
        bs4 = (Frgments) vPadapter.getItem(4);
    }

    private Toolbar.OnMenuItemClickListener clickListener = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_fav:
                    Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                    startActivity(intent);

                    break;

                case R.id.action_share:
                    ShowSLHdia();
                  Window win =slHdialog.getWindow();
                    WindowManager.LayoutParams params = slHdialog.getWindow().getAttributes();
                    params.gravity= Gravity.TOP|Gravity.RIGHT;
                     params.windowAnimations=1;
                //    params.x=title_text.getScrollX()+160;

                //    slHdialog
                    slHdialog.show();

            }

            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * @param colors
     */
    public void setback(int colors) {
        edit.putInt("cname", colors);
        edit.commit();
        colorsss = colors;
        toolbar_zdy.setBackgroundResource(colors);

        myteb.setBackgroundResource(colors);
        dl_tv.setBackgroundResource(colors);
    }


    public int getColorsss() {
        return colorsss;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.main_star:
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);

                break;

            case R.id.main_slh:
                ShowSLHdia();
                Window win =slHdialog.getWindow();
                WindowManager.LayoutParams params = slHdialog.getWindow().getAttributes();
                params.gravity= Gravity.TOP|Gravity.RIGHT;
                params.windowAnimations=1;
                //    params.x=title_text.getScrollX()+160;

                //    slHdialog
                slHdialog.show();
                break;
            case R.id.main_cd:
                mdrawerlayout.openDrawer(GravityCompat.START);
                break;
            case R.id.dl_fav:

                Intent intentgo = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intentgo);

                break;

            case R.id.dl_theme:
                showDialog(MainActivity.this);
                dialog.show();

                break;
            case R.id.dl_tv:
                Toast.makeText(this, "John Smith", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvtoday:
                mdrawerlayout.closeDrawers();

                if (getint != 1) {
                    bs4.setTimes(listtimes[0]);
                    bs4.fragmentsonrefreshhing();
                    bs3.setTimes(listtimes[0]);
                    bs3.fragmentsonrefreshhing();
                    bs2.setTimes(listtimes[0]);
                    bs2.fragmentsonrefreshhing();
                    bs1.setTimes(listtimes[0]);
                    bs1.fragmentsonrefreshhing();
                    bs.setTimes(listtimes[0]);
                    bs.fragmentsonrefreshhing();
                    getint = 1;
                    Toast.makeText(this, "正在加载本日排行", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "已经是本日排行", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tvweek:
                mdrawerlayout.closeDrawers();
                if (getint != 2) {

                    bs4.setTimes(listtimes[1]);
                    bs4.fragmentsonrefreshhing();
                    bs3.setTimes(listtimes[1]);
                    bs3.fragmentsonrefreshhing();
                    bs2.setTimes(listtimes[1]);
                    bs2.fragmentsonrefreshhing();
                    bs1.setTimes(listtimes[1]);
                    bs1.fragmentsonrefreshhing();
                    bs.setTimes(listtimes[1]);
                    bs.fragmentsonrefreshhing();
                    getint = 2;
                    Toast.makeText(this, "正在加载本周排行", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "已经是本周排行", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvmonth:
                mdrawerlayout.closeDrawers();

                if (getint != 3) {
                    bs4.setTimes(listtimes[2]);
                    bs4.fragmentsonrefreshhing();
                    bs3.setTimes(listtimes[2]);
                    bs3.fragmentsonrefreshhing();
                    bs2.setTimes(listtimes[2]);
                    bs2.fragmentsonrefreshhing();
                    bs1.setTimes(listtimes[2]);
                    bs1.fragmentsonrefreshhing();
                    bs.setTimes(listtimes[2]);
                    bs.fragmentsonrefreshhing();
                    getint = 3;
                    Toast.makeText(this, "正在加载本月排行", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "已经是本月排行", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void showDialog(Context context) {
        dialog = new ThemeDialog(context, R.style.MyDialog, new ThemeDialog.LeaveMyDialogListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv1:

                        setback(R.color.blue);

                        dialog.dismiss();
                        break;
                    case R.id.tv2:

                        setback(R.color.bluenext);
                        dialog.dismiss();
                        break;
                    case R.id.tv3:
                        setback(R.color.green);
                        dialog.dismiss();
                        break;
                    case R.id.tv4:
                        setback(R.color.red);
                        dialog.dismiss();
                        break;
                    case R.id.tv5:
                        setback(R.color.grey);
                        dialog.dismiss();
                        break;
                    case R.id.tv6:
                        setback(R.color.black);
                        dialog.dismiss();
                        break;
                    case R.id.tv7:
                        setback(R.color.purple);
                        dialog.dismiss();
                        break;
                    case R.id.tv8:
                        setback(R.color.orange);
                        dialog.dismiss();
                        break;
                    case R.id.tv9:
                        setback(R.color.pink);
                        dialog.dismiss();
                        break;
                }
            }
        });


    }
    public void ShowSLHdia(){
        slHdialog=new SLHdialog(MainActivity.this, R.style.seldialog, new SLHdialog.SLHDialogListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.slh_ll1:
                        slHdialog.dismiss();
                        if (getint != 1) {
                            bs4.setTimes(listtimes[0]);
                            bs4.fragmentsonrefreshhing();
                            bs3.setTimes(listtimes[0]);
                            bs3.fragmentsonrefreshhing();
                            bs2.setTimes(listtimes[0]);
                            bs2.fragmentsonrefreshhing();
                            bs1.setTimes(listtimes[0]);
                            bs1.fragmentsonrefreshhing();
                            bs.setTimes(listtimes[0]);
                            bs.fragmentsonrefreshhing();
                            getint = 1;
                            Toast.makeText(MainActivity.this, "正在加载本日排行", Toast.LENGTH_SHORT).show();
                        } else {
                            slHdialog.dismiss();
                            Toast.makeText(MainActivity.this, "已经是本日排行", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.slh_ll2:
                        slHdialog.dismiss();
                        if (getint != 2) {

                            bs4.setTimes(listtimes[1]);
                            bs4.fragmentsonrefreshhing();
                            bs3.setTimes(listtimes[1]);
                            bs3.fragmentsonrefreshhing();
                            bs2.setTimes(listtimes[1]);
                            bs2.fragmentsonrefreshhing();
                            bs1.setTimes(listtimes[1]);
                            bs1.fragmentsonrefreshhing();
                            bs.setTimes(listtimes[1]);
                            bs.fragmentsonrefreshhing();
                            getint = 2;
                            Toast.makeText(MainActivity.this, "正在加载本周排行", Toast.LENGTH_SHORT).show();
                        } else {
                            slHdialog.dismiss();
                            Toast.makeText(MainActivity.this, "已经是本周排行", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.slh_ll3:
                        slHdialog.dismiss();
                        if (getint != 3) {
                            bs4.setTimes(listtimes[2]);
                            bs4.fragmentsonrefreshhing();
                            bs3.setTimes(listtimes[2]);
                            bs3.fragmentsonrefreshhing();
                            bs2.setTimes(listtimes[2]);
                            bs2.fragmentsonrefreshhing();
                            bs1.setTimes(listtimes[2]);
                            bs1.fragmentsonrefreshhing();
                            bs.setTimes(listtimes[2]);
                            bs.fragmentsonrefreshhing();
                            getint = 3;
                            Toast.makeText(MainActivity.this, "正在加载本月排行", Toast.LENGTH_SHORT).show();
                        } else {
                            slHdialog.dismiss();
                            Toast.makeText(MainActivity.this, "已经是本月排行", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.slh_ll4:
                        slHdialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                        startActivity(intent);

                        break;
                    case R.id.slh_ll5:
                        slHdialog.dismiss();
                        showDialog(MainActivity.this);
                        dialog.show();
                        break;
                }
            }
        });
    }

}
