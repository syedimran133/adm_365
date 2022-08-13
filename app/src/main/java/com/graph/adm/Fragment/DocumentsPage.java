package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.graph.adm.Adapter.DocumentsFragmentPagerAdapter;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutDocumentTabBinding;
import com.graph.adm.databinding.LayoutTestBinding;

public class DocumentsPage extends Fragment {

    private LayoutDocumentTabBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutDocumentTabBinding.inflate(inflater, container, false);
        binding.back.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Dashboard()));
        binding.btnMyDocuments.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Documents()));
        binding.btnShareDocuments.setOnClickListener(v -> FlowOrganizer.getInstance().add(new SharedDocuments()));
        binding.btnHrDocuments.setOnClickListener(v -> FlowOrganizer.getInstance().add(new HRPolocies()));
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        //setupViewPager(binding.viewpager);
        //binding.viewpager.setOffscreenPageLimit(3);
        //binding.slidingTabs.setupWithViewPager(binding.viewpager);
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

    private void setupViewPager(ViewPager viewPager) {
        DocumentsFragmentPagerAdapter adapter = new DocumentsFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Documents(), "My Documents");
        adapter.addFragment(new SharedDocuments(), "Shared Documents");
        adapter.addFragment(new HRPolocies(), "HR Polocies");
        viewPager.setAdapter(adapter);
    }
}