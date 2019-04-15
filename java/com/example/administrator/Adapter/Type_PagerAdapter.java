package com.example.administrator.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/11.
 */

public class Type_PagerAdapter extends FragmentPagerAdapter {
    String[] mTitles ;
    List<Fragment> lists = new ArrayList<>();

    public Type_PagerAdapter(FragmentManager fm, String[] titles, List<Fragment> list) {
        super(fm);
        this.mTitles = titles;
        this.lists = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
