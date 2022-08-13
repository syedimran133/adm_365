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
import com.graph.adm.Adapter.VideosGallaryAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutPhotoGalleryBinding;
import com.graph.adm.model.videogal.VideosGalData;

public class VideoGallery  extends Fragment {

    private LayoutPhotoGalleryBinding binding;
    private VideosGallaryAdapter adpter;
    public static final String ARG_PAGE = "ARG_PAGE";

    public static VideoGallery newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        VideoGallery fragment = new VideoGallery();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutPhotoGalleryBinding.inflate(inflater, container, false);
        getVideosCallBack();
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
    private void getVideosCallBack() {
        //Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqDZ0rbvGoVVRIIOpIZOJmRN/items/root/children",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                   // Utils.closeDilog();
                    Log.d("TAG", "Response: " + response.toString());
                    VideosGalData userData = new Gson().fromJson(response.toString(), VideosGalData.class);
                    if (userData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new VideosGallaryAdapter(getContext(), userData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(position -> {
                           /* PhotoSingel photoSingel=new PhotoSingel();
                            Bundle bundle = new Bundle();
                            bundle.putString("FileName",userData.getValue().get(position).getName());
                            FlowOrganizer.getInstance().add(photoSingel, true,bundle);*/
                        });
                    } else {
                        binding.rv.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }

                },
                error -> {
                   // Utils.closeDilog();
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
