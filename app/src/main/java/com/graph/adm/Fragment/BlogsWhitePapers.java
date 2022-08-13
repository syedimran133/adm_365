package com.graph.adm.Fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.graph.adm.Adapter.AnnouncementAdapter;
import com.graph.adm.Adapter.BlogsWhitePapersAdapter;
import com.graph.adm.Adapter.MyCalendarAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutAnnouncementBinding;
import com.graph.adm.databinding.LayoutBlogsWhitePapersBinding;
import com.graph.adm.model.announcement.AnnouncementData;
import com.graph.adm.model.blogs.BlogsData;
import com.graph.adm.model.blogs.download.BlogsDownloadData;

public class BlogsWhitePapers extends Fragment {
    private LayoutBlogsWhitePapersBinding binding;
    private BlogsWhitePapersAdapter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutBlogsWhitePapersBinding.inflate(inflater, container, false);
        binding.backBp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new Dashboard(), false);
            }
        });
        getBlogsCallBack();
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

    private void getBlogsCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/54c2852b-62a7-4a27-9778-04adfbbcf7db/items?$expand=fields($select=Id,Title,BlogsDescription,Name,PublishedBy,PublishedOn)",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Utils.closeDilog();
                    /* Successfully called graph, process data and send to UI */
                    Log.d("TAG", "Response: " + response.toString());
                    BlogsData userData = new Gson().fromJson(response.toString(), BlogsData.class);
                    if (userData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new BlogsWhitePapersAdapter(getContext(), userData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(new BlogsWhitePapersAdapter.IonItemSelect() {
                            @Override
                            public void onItemSelect(int position) {
                                // Toast.makeText(getContext(), userData.getValue().get(position).getFields().getId(), Toast.LENGTH_SHORT).show();
                                getBlogsDownloadCallBack(userData.getValue().get(position).getFields().getId());
                            }
                        });
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

    private void getBlogsDownloadCallBack(String id) {
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/54c2852b-62a7-4a27-9778-04adfbbcf7db/items/" + id + "/driveItem",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    /* Successfully called graph, process data and send to UI */
                    Log.d("TAG", "Response: " + response.toString());
                    BlogsDownloadData userData = new Gson().fromJson(response.toString(), BlogsDownloadData.class);
                    Utils.downloadfile(userData.getMicrosoftGraphDownloadUrl(),userData.getName(),getContext());
                },
                error -> {
                    Log.d("TAG", "Error: " + error.toString());
                    //displayError(error);
                });
    }
}
