package com.graph.adm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.graph.adm.Activity.ViewPhoto;
import com.graph.adm.Adapter.GallarySingelAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.GridSpacingItemDecoration;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutSingelphotoBinding;
import com.graph.adm.model.singalPhoto.SingalPhotoData;

public class PhotoSingel extends Fragment {

    private LayoutSingelphotoBinding binding;
    private GallarySingelAdapter adpter;
    int spanCount = 3; // 3 columns
    int spacing = 5; // 50px
    boolean includeEdge = false;
    private String fileName = "";
    public static SingalPhotoData userData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = LayoutSingelphotoBinding.inflate(inflater, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            fileName = getArguments().getString("FileName");
        }
        binding.tvTitle.setText(fileName);
        binding.back.setOnClickListener(v -> FlowOrganizer.getInstance().popUpBackTo(1));
        getGetImagesBack();
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

    private void getGetImagesBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqB26S3UZg90T6rz6Ii-zfRW/items/root:/"+fileName+":/children?$expand=thumbnails",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Utils.closeDilog();
                    Log.d("TAG", "Response: " + response.toString());
                    userData = new Gson().fromJson(response.toString(), SingalPhotoData.class);
                    adpter = new GallarySingelAdapter(getContext(), userData.getValue());
                    binding.rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    binding.rv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
                    binding.rv.setAdapter(adpter);
                    adpter.registerOnItemClickListener(position -> {
                        //ViewPhoto viewPhoto= new ViewPhoto();
                        //viewPhoto.getListImages(userData.getValue(),position);
                        Intent intent = new Intent(getActivity().getBaseContext(),ViewPhoto.class);
                        //intent.putExtra("SingalPhotoData",userData);
                        intent.putExtra("Index",position);
                        intent.putExtra("FileName",fileName);
                        intent.putExtra("FileURL",userData.getValue().get(position).getMicrosoftGraphDownloadUrl());
                        startActivity(intent);
                    });
                },
                error -> {
                    Utils.closeDilog();
                    Log.d("TAG", "Error: " + error.toString());
                });
    }
}
