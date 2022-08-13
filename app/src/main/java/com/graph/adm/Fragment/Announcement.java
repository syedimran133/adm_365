package com.graph.adm.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.graph.adm.Activity.Login;
import com.graph.adm.Activity.Spalsh;
import com.graph.adm.Adapter.AnnouncementAdapter;
import com.graph.adm.Adapter.MyCalendarAdapter;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutAnnouncementBinding;
import com.graph.adm.model.UserData;
import com.graph.adm.model.announcement.AnnouncementData;
import com.graph.adm.model.announcement.ImageData;
import com.graph.adm.model.announcement.Value;
import com.graph.adm.model.announcement.image.ImageDataInner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Announcement extends Fragment {

    private LayoutAnnouncementBinding binding;
    private AnnouncementAdapter adpter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutAnnouncementBinding.inflate(inflater, container, false);

        binding.backAnnou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new Dashboard(), false);
            }
        });

        getAnnouncementCallBack();
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

    private ImageData getImage(String str) {
        ImageData data = null;
        try {
            data = new Gson().fromJson(str, ImageData.class);
        } catch (Exception e) {
        }
        return data;
    }

    public void getImageCallBack(AnnouncementData userData) {
        AnnouncementData tempData = userData;
        List<Value> v_temp = userData.getValue();

        for (int i = 0; i < userData.getValue().size(); i++) {
            Value v=userData.getValue().get(i);
            final int k=i;
            if (userData.getValue().get(i).getFields().getImage() != null) {
                ImageData dat = new Gson().fromJson(userData.getValue().get(i).getFields().getImage(), ImageData.class);
                MSGraphRequestWrapper.callGraphAPIUsingVolley(
                        getContext(),
                        "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqD_xK9-pBwCSJDHbS4RTgoN/root:/Lists/e5f59071-dd2c-481b-9fd5-c4e20e13bd99:/children?$filter=name eq '" + dat.getFileName() + "'",
                        AppSingle.getInstance().getmAccessToken(),
                        response -> {
                            ImageDataInner imgUrlData = new Gson().fromJson(response.toString(), ImageDataInner.class);
                            com.graph.adm.model.announcement.Fields f=v.getFields();
                            f.setImage(imgUrlData.getValue().get(0).getMicrosoftGraphDownloadUrl());
                            v.setFields(f);
                            v_temp.set(k,v);
                            //imgarr.add(imgUrlData.getValue().get(0).getMicrosoftGraphDownloadUrl());
                            },
                        error -> {
                            Log.d("TAG", "Error: " + error.toString());
                            //displayError(error);
                        });
            }
        }
        new Handler().postDelayed(() -> {
            Utils.closeDilog();
            adpter = new AnnouncementAdapter(getContext(), v_temp);
            binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rv.setAdapter(adpter);
        }, 5000);

    }

    private void getAnnouncementCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/e5f59071-dd2c-481b-9fd5-c4e20e13bd99/items?$expand=fields($select=Id,Title,Description,Ranking,AnnouncementType,Image/serverRelativeUrl)",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    /* Successfully called graph, process data and send to UI */
                    Log.d("TAG", "Response: " + response.toString());
                    AnnouncementData userData = new Gson().fromJson(response.toString(), AnnouncementData.class);

                    if (userData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        getImageCallBack(userData);
                    } else {
                        binding.rv.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }
                },
                error -> {
                    Utils.closeDilog();
                    binding.rv.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                });
    }

}
