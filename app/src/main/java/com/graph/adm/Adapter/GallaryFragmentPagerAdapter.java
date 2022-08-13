package com.graph.adm.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.graph.adm.Fragment.Documents;
import com.graph.adm.Fragment.HRPolocies;
import com.graph.adm.Fragment.PhotoGallery;
import com.graph.adm.Fragment.SharedDocuments;
import com.graph.adm.Fragment.VideoGallery;

public class GallaryFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Photo Gallery", "Video Gallery"};
    private Context context;

    public GallaryFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fr = null;
        if (position == 0) {
            fr= PhotoGallery.newInstance(position);
        } else if (position == 1) {
            fr= VideoGallery.newInstance(position);
        }
        return fr;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}