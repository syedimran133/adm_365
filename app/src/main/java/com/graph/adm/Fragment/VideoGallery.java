package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.databinding.LayoutDocumentsBinding;

public class PhotoGallery extends Fragment {

    private LayoutDocumentsBinding binding;
    private DocumentsAdapter adpter;
    public static final String ARG_PAGE = "ARG_PAGE";

    public static PhotoGallery newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PhotoGallery fragment = new PhotoGallery();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutDocumentsBinding.inflate(inflater, container, false);
        adpter = new DocumentsAdapter(getContext());
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adpter);
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
