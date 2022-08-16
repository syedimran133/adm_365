package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.R;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutDocumentsBinding;
import com.graph.adm.databinding.LayoutSupportTabBinding;
import com.graph.adm.model.support.Fields;

public class JService extends Fragment {

    private LayoutSupportTabBinding binding;
    private static Fields dataInner;

    public void newInstance(Fields data) {
        dataInner=data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutSupportTabBinding.inflate(inflater, container, false);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new SupportService());
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.title.setText(dataInner.getTitle());
        binding.img.setImageDrawable(getContext().getDrawable(R.drawable.jservice));
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
