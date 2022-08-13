package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.graph.adm.Adapter.AnnouncementAdapter;
import com.graph.adm.Adapter.BlogsWhitePapersAdapter;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutBlogsWhitePapersBinding;
import com.graph.adm.databinding.LayoutDocumentsBinding;

public class Documents extends Fragment {

    private LayoutDocumentsBinding binding;
    private DocumentsAdapter adpter;
    public static final String ARG_PAGE = "ARG_PAGE";

    public static Documents newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Documents fragment = new Documents();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutDocumentsBinding.inflate(inflater, container, false);
        adpter = new DocumentsAdapter(getContext());
        binding.title.setText("Documents");
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
