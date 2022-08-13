package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.graph.adm.databinding.LayoutSupportTabBinding;
import com.graph.adm.model.support.Fields;

public class JRecruit extends Fragment {

    private LayoutSupportTabBinding binding;
    public static final String ARG_PAGE = "ARG_PAGE";
    private static Fields dataInner;
    public static JRecruit newInstance(int page,Fields data) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        JRecruit fragment = new JRecruit();
        fragment.setArguments(args);
        dataInner=data;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutSupportTabBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvServiceTitle.setText(dataInner.getTitle());
        binding.tvServiceDescription.setText(dataInner.getDescription());
        binding.tvServiceEmail.setText("Email : "+dataInner.getPrimaryContactEmail());
        binding.tvServiceMobile.setText("Phone : "+dataInner.getPrimaryContactPhone().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
