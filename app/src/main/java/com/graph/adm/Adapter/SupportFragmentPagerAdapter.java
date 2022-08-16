package com.graph.adm.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.graph.adm.Fragment.Contact;
import com.graph.adm.Fragment.Documents;
import com.graph.adm.Fragment.HRPolocies;
import com.graph.adm.Fragment.JBuzz;
import com.graph.adm.Fragment.JRecruit;
import com.graph.adm.Fragment.JService;
import com.graph.adm.Fragment.Joss;
import com.graph.adm.Fragment.SharedDocuments;
import com.graph.adm.Fragment.ZingHR;
import com.graph.adm.model.support.SupportServiceValue;

import java.util.List;

public class SupportFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 6;
    ////1.JService 2.Static 3.JOSS 4.ZingHR ,5.Home 6.Profile 7.Contact
    private String tabTitles[] = new String[]{"JService", "Joss 2.0", "Zing HR", "JBuzz", "JRecruit","Contact"};
    private Context context;
    private List<SupportServiceValue> data;

    public SupportFragmentPagerAdapter(FragmentManager fm, Context context,List<SupportServiceValue> data) {
        super(fm);
        this.context = context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fr = null;
       /* if (position == 0) {
            fr= JService.newInstance(position,data.get(0).getFields());
        } else if (position == 1) {
            fr= Joss.newInstance(position,data.get(2).getFields());
        } else if (position == 2) {
            fr= ZingHR.newInstance(position,data.get(3).getFields());
        }else if (position == 3) {
            fr= JBuzz.newInstance(position,data.get(4).getFields());
        } else if (position == 4) {
            fr= JRecruit.newInstance(position,data.get(5).getFields());
        }else if (position == 5) {
            fr= Contact.newInstance(position,data.get(6).getFields());
        }*/
        return fr;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}