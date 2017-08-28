package com.example.lenovo.nextmyapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/5/4.
 */
public class Frgments extends Fragment {
    private  String gets,doctext,times;
    private ListView lv;
    private Document doc;
    private List<EntityMyapp> listbean,handlist;
    private EntityMyapp bean;
    private Mythread mythread=null;
    private Myhandler myhandler=null;
    private Listadapters listadapters;
    private final String httphead="https://github.com/trending";
    private final String htttps="https://github.com/";
    private SwipeRefreshLayout swipe;
    private int colors;
    private MainActivity min;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.frgmentjava,null);
              lv= ((ListView) v.findViewById(R.id.fl_tv));
            myhandler=new Myhandler();
            listbean=new ArrayList<>();

        swipe = (SwipeRefreshLayout) v.findViewById(R.id.swipe);
        min=(MainActivity) getActivity();




        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){


            @Override
            public void onRefresh() {
                colors=min.getColorsss();
                swipe.setColorSchemeResources(colors);
                  lv.setAdapter(null);
                listbean.clear();
                mythread=new Mythread();
                mythread.start();
            }

        });
        swipe.setRefreshing(true);

        handlist=new ArrayList<>();
            if(mythread==null){
                mythread=new Mythread();
                mythread.start();
            }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),WebActivity.class);
                String ass=listbean.get(i).bt.trim().replace(" ","");
                String http=htttps+ass;
                intent.putExtra("key",http);

                getActivity().startActivity(intent);
            }
        });

        return  v;

    }
public void fragmentsonrefreshhing(){
    swipe.setRefreshing(true);
    lv.setAdapter(null);
    listbean.clear();
    mythread=new Mythread();
    mythread.start();
}
    @Override
    public void onResume() {
        super.onResume();
        colors=min.getColorsss();
        swipe.setColorSchemeResources(colors);

    }

        class Mythread extends Thread {
            @Override
            public void run() {
                try {
                    doc= Jsoup.connect(httphead+"/"+gets+"?"+times).ignoreContentType(true).get();
                    Log.e( "run: ",gets+"------"+times );
                    Elements el = doc.select("ol li");

                    for (Element e : el) {
                        EntityMyapp bean = new EntityMyapp();
                        bean.url=(e.select(".d-inline-block").select("a").attr("href")) ;
                        bean.zz=(e.select(".d-inline-block").select(".text-normal").text()) ;
                        bean.bt=( e.select("h3").select("a").text());
                        bean.js=(e.select(".d-inline-block").select(".text-gray").text());
                        bean.star=(e.select("span.float-right").text());
                        bean.list = new ArrayList<String>();
                        //    Elements es= e.select("ol li");
                        Elements ess = e.select("div.f6 .no-underline img");
                        bean.count = ess.size();
                        for (int i = 0; i < bean.count; i++) {
                            String url = ess.get(i).attr("src");

                            bean.list.add(url);
                        }
                        listbean.add(bean);}
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Message message=new Message();
                message.what=1;
                myhandler.sendMessage(message);
            }
        }

    class Myhandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    mythread=null;
                    swipe.setRefreshing(false);
                    listadapters=new Listadapters(listbean,getContext());
                    lv.setAdapter(listadapters);
                    break;

            }
            super.handleMessage(msg);
        }
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @SuppressLint("ValidFragment")
public Frgments(String gets){

    this.gets=gets;

}
    public Frgments(){

        super();
    }
}
