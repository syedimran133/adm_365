package com.graph.adm.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.graph.adm.Fragment.SlideFragment;
import com.graph.adm.R;

public class AutoScrollPagerAdapter extends FragmentPagerAdapter {
int size;
Context ctx;
    public AutoScrollPagerAdapter(Context ctx,FragmentManager fm,int size) {
        super(fm);
        this.size=size;
        this.ctx=ctx;
    }
    @Override
    public Fragment getItem(int position) {
        // Return a SlideFragment (defined as a static inner class below).
        SlideFragment.img=R.drawable.kudos_certification;
        return SlideFragment.newInstance(position + 1);
    }
    @Override
    public int getCount() {
        return size;
    }
}