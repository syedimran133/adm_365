package com.graph.adm.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class AutoScrollPagerAdapter  extends FragmentPagerAdapter {

    public AutoScrollPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        // Return a SlideFragment (defined as a static inner class below).
        return SlideFragment.newInstance(position + 1);
    }
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}