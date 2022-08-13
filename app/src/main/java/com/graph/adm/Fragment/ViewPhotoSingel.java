package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.graph.adm.Adapter.GallaryAdapter;
import com.graph.adm.Adapter.GallarySingelAdapter;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutPhotoGalleryBinding;
import com.graph.adm.databinding.LayoutSingelphotoBinding;

public class PhotoSingel extends Fragment {

    private LayoutSingelphotoBinding binding;
    private GallarySingelAdapter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutSingelphotoBinding.inflate(inflater, container, false);
        adpter = new GallarySingelAdapter(getContext());
        binding.rv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.rv.setAdapter(adpter);
        adpter.registerOnItemClickListener(new GallarySingelAdapter.IonItemSelect() {
            @Override
            public void onItemSelect(int position) {
                FlowOrganizer.getInstance().add(new PhotoSingel(), true);
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().popUpBackTo(1);
            }
        });
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
}
