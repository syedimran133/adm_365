package com.graph.adm.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.graph.adm.Fragment.Documents;
import com.graph.adm.Fragment.HRPolocies;
import com.graph.adm.Fragment.SharedDocuments;

public class DocumentsFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"My Documents", "Shared Documents", "HR Polocies "};
    private Context context;

    public DocumentsFragmentPagerAdapter(FragmentManager fm, Context context) {
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
            fr= Documents.newInstance(position);
        } else if (position == 1) {
            fr= SharedDocuments.newInstance(position);
        } else if (position == 2) {
            fr= HRPolocies.newInstance(position);
        }
        return fr;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}