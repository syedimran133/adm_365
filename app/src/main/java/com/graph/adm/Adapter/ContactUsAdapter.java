package com.graph.adm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import com.graph.adm.R;
import com.graph.adm.model.contactus.Fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactUsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private Map<String, List<Fields>> expandableListDetail;

    public ContactUsAdapter(Context context, List<String> expandableListTitle,
                            Map<String, List<Fields>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Fields getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    public List<Fields> getFields(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition));
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Fields expandedListText = (Fields) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_contactus, null);
        }

        LinearLayout llMain = (LinearLayout) convertView
                .findViewById(R.id.ll_text);
        if (isLastChild) {
            Log.d("Layout Check :: ", "expandedListPosition " + getFields(listPosition).size() + " " + expandedListPosition + " " + (getChildrenCount(listPosition)));
            if (expandedListPosition == (getChildrenCount(listPosition) - 1)) {
                llMain.setBackground(context.getDrawable(R.drawable.help_bg_bottom));
            } else {
                llMain.setBackground(AppCompatResources.getDrawable(context, R.drawable.help_bg_mid));
            }
        } else {
            llMain.setBackground(AppCompatResources.getDrawable(context,R.drawable.help_bg_mid));
        }
        //llMain.setBackground(AppCompatResources.getDrawable(context,R.drawable.help_bg_mid));
        llMain.removeAllViews();
        TextView tv_title = new TextView(context);
        tv_title.setText(expandedListText.getLocationCity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        Typeface typeface = ResourcesCompat.getFont(context, R.font.roboto_bold);
        tv_title.setTypeface(typeface, Typeface.BOLD);
        tv_title.setTextColor(Color.parseColor("#2d2d2d"));
        tv_title.setLayoutParams(params);
        TextView tv_add = new TextView(context);
        tv_add.setText(expandedListText.getAddress());
        Typeface typeface_add = ResourcesCompat.getFont(context, R.font.roboto);
        tv_add.setTypeface(typeface_add, Typeface.NORMAL);
        tv_add.setLayoutParams(params);
        tv_add.setTextColor(Color.parseColor("#2d2d2d"));
        llMain.addView(tv_title);
        llMain.addView(tv_add);
        //expandedListTextView.setText(expandedListText.getAddress());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {

        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_contactus, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setText(listTitle.replaceAll("[^A-Za-z]", ""));

        ImageView img = (ImageView) convertView
                .findViewById(R.id.img);
        LinearLayout ll_main = (LinearLayout) convertView
                .findViewById(R.id.ll_main);
        if (isExpanded) {
            img.setImageResource(R.drawable.chevronright_up);
            ll_main.setBackground(context.getDrawable(R.drawable.help_bg_contact));
        } else {
            img.setImageResource(R.drawable.chevronright_down);
            ll_main.setBackground(context.getDrawable(R.drawable.help_bg_main));
        }
       /* if (listPosition == 0) {
            Log.d("Layout Check :: ", "Zero " + isExpanded);
            ll_main.setBackground(context.getDrawable(R.drawable.help_bg_main));
        } else if (listPosition == getChildrenCount(listPosition)) {
            Log.d("Layout Check :: ", "Max Size " + isExpanded);

        } else if (listPosition != 0 && listPosition != getChildrenCount(listPosition)) {
            Log.d("Layout Check :: ", "not zore not max " + isExpanded);
            if (isExpanded)
                ll_main.setBackground(context.getDrawable(R.drawable.help_bg));
            else
                ll_main.setBackground(context.getDrawable(R.drawable.help_bg_main));
        } else {
            Log.d("Layout Check :: ", "Normal " + isExpanded);
            //if(isExpanded)
            //ll_main.setBackground(context.getDrawable(R.drawable.help_bg_main));
        }*/
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}