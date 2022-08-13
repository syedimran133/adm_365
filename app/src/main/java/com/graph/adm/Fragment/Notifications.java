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
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutAnnouncementBinding;
import com.graph.adm.databinding.LayoutBlogsWhitePapersBinding;

public class BlogsWhitePapers extends Fragment {
    private LayoutBlogsWhitePapersBinding binding;
    private BlogsWhitePapersAdapter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutBlogsWhitePapersBinding.inflate(inflater, container, false);
        adpter = new BlogsWhitePapersAdapter(getContext());
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adpter);
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