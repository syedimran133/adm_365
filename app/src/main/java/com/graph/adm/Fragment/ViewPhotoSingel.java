package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.graph.adm.Adapter.GallarySingelAdapter;
import com.graph.adm.R;
import com.graph.adm.Utils.DownloadImageTask;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.LayoutSingelphotoBinding;
import com.graph.adm.databinding.LayoutViewPhotoBinding;

public class ViewPhotoSingel extends Fragment {

    private LayoutViewPhotoBinding binding;
    String fileName = "",fileUrl="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutViewPhotoBinding.inflate(inflater, container, false);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            fileName = getArguments().getString("FileName");
            fileUrl = getArguments().getString("FileURL");
        }
        binding.tvTitle.setText(fileName);
        if (fileUrl != null) {
            new DownloadImageTask(getContext(),binding.inPhoto).execute(fileUrl);
        } else {
            binding.inPhoto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.jade_default));
        }

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
