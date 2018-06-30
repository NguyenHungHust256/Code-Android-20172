package com.example.bahung.vtask1.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bahung.vtask1.view.fragment.TabJobFragment.WaitingProjectFragment;

/**
 * Created by bahung on 04/03/2018.
 **/

public class TabJobAdapter extends FragmentPagerAdapter {
    public static final int PAGE_COUNT = 4;
    public static final String tieuDeTabs[] = new String[]{"ĐANG CHỜ", "ĐANG LÀM", "HOÀN THÀNH", "QÚA HẠN"};


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position == 0){
            fragment = new WaitingProjectFragment();

        } else if(position == 1){
            fragment = new WaitingProjectFragment();
        } else if(position == 2){
            fragment = new WaitingProjectFragment();
        } else if(position == 3){
            fragment = new WaitingProjectFragment();
        }
        return  fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tieuDeTabs[position];
    }
    public TabJobAdapter(FragmentManager fm) {
        super(fm);
    }

}
