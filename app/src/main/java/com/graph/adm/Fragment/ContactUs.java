package com.graph.adm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.graph.adm.Activity.ViewPhoto;
import com.graph.adm.Adapter.BUEventsAdapter;
import com.graph.adm.Adapter.GallaryFragmentPagerAdapter;
import com.graph.adm.Adapter.GallarySingelAdapter;
import com.graph.adm.Adapter.HelpAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.ExpandableListDataPump;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.GridSpacingItemDecoration;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutGallaryBinding;
import com.graph.adm.databinding.LayoutHelpBinding;
import com.graph.adm.model.buevents.BUEventsData;
import com.graph.adm.model.help.HelpData;
import com.graph.adm.model.singalPhoto.SingalPhotoData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Help extends Fragment {

    private LayoutHelpBinding binding;
    HelpAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutHelpBinding.inflate(inflater, container, false);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().popUpBackTo(1);
            }
        });
        //expandableListDetail = ExpandableListDataPump.getData();
        //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        getHelpCallBack();
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getHelpCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/a693a26b-f111-4b0a-9e2d-11f6b5e545ab/items?expand=fields($select=id,Title,Description,IsActive)&$filter=fields/IsActive eq 'Yes'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    HelpData docData = new Gson().fromJson(response.toString(), HelpData.class);
                    if (docData.getValue().size() != 0) {
                        getData(docData);
                    } else {
                        binding.expandableListView.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }

                    Utils.closeDilog();
                },
                error -> {
                    Utils.closeDilog();
                    binding.expandableListView.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                });
    }

    public void getData(HelpData docData){
        expandableListDetail = new HashMap<String, List<String>>();
        for (int i=0;i<docData.getValue().size();i++){
            ArrayList a=new ArrayList<>();
            a.add(docData.getValue().get(i).getFields().getDescription());
            expandableListDetail.put(docData.getValue().get(i).getFields().getTitle(), a);
        }
        expandableListTitle= new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new HelpAdapter(getContext(), expandableListTitle, expandableListDetail);
        binding.expandableListView.setAdapter(expandableListAdapter);
    }
}