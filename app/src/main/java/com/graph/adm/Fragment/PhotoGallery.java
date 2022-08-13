package com.graph.adm.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.graph.adm.Adapter.BlogsWhitePapersAdapter;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Adapter.GallaryAdapter;
import com.graph.adm.Adapter.MyCalendarAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutDocumentsBinding;
import com.graph.adm.databinding.LayoutPhotoGalleryBinding;
import com.graph.adm.model.blogs.BlogsData;
import com.graph.adm.model.photo.GallaryPhotoData;

public class PhotoGallery extends Fragment {

    private LayoutPhotoGalleryBinding binding;
    private GallaryAdapter adpter;
    public static final String ARG_PAGE = "ARG_PAGE";

    public static PhotoGallery newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PhotoGallery fragment = new PhotoGallery();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutPhotoGalleryBinding.inflate(inflater, container, false);
        getPhotoCallBack();
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

    private void getPhotoCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqB26S3UZg90T6rz6Ii-zfRW/root/children",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Utils.closeDilog();
                    Log.d("TAG", "Response: " + response.toString());
                    GallaryPhotoData userData = new Gson().fromJson(response.toString(), GallaryPhotoData.class);
                    if (userData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new GallaryAdapter(getContext(), userData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(position -> {
                            PhotoSingel photoSingel=new PhotoSingel();
                            Bundle bundle = new Bundle();
                            bundle.putString("FileName",userData.getValue().get(position).getName());
                            FlowOrganizer.getInstance().add(photoSingel, true,bundle);
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
}
