package com.example.lenovo.nextmyapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lenovo on 2017/5/5.
 */
public class VPadapter extends FragmentPagerAdapter {
private String[] listtitle;
    private Fragment[] listfragment;

    public VPadapter(FragmentManager fm,String[] title,Fragment[] fragments) {
        super(fm);
        this.listtitle=title;
        this.listfragment=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return listfragment[position];
    }

    @Override
    public int getCount() {
        return listfragment.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return listtitle[position % listtitle.length];
    }


}

