package com.graph.adm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.graph.adm.R;
import com.graph.adm.Utils.SliderViewModel;

public class SlideFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SliderViewModel sliderViewModel;
    public static int img;

    public static SlideFragment newInstance(int index) {
        SlideFragment fragment = new SlideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sliderViewModel = ViewModelProviders.of(this).get(SliderViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        sliderViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slider, container, false);
        final ImageView imageView = root.findViewById(R.id.imageView);
        final TextView tv_wish_type = root.findViewById(R.id.tv_wish_type);
        final TextView tv_name = root.findViewById(R.id.tv_name);
        final TextView tv_designation = root.findViewById(R.id.tv_designation);
        final TextView tv_description = root.findViewById(R.id.tv_description);
        sliderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                tv_wish_type.setText(Dashboard.list.get(index).getFields().getGreetingText());
                tv_name.setText(Dashboard.list.get(index).getFields().getAssociateName().trim());
                if (Dashboard.list.get(index).getFields().getDescription() != null) {
                    if (!Dashboard.list.get(index).getFields().getDescription().equals("")) {
                        tv_description.setVisibility(View.VISIBLE);
                        tv_designation.setText(Dashboard.list.get(index).getFields().getDesignation().trim());
                        tv_description.setText(Dashboard.list.get(index).getFields().getDescription().trim().replaceAll("\\n",""));
                    } else {
                        tv_description.setVisibility(View.GONE);
                        tv_designation.setText(Dashboard.list.get(index).getFields().getDesignation().trim());
                    }
                } else {
                    tv_description.setVisibility(View.GONE);
                    tv_designation.setText(Dashboard.list.get(index).getFields().getDesignation().trim());
                }
                imageView.setImageResource(getImg(Dashboard.list.get(index).getFields().getWishType()));
            }
        });
        return root;
    }

    private int getImg(String type) {
        int r = 0;
        if (type.equalsIgnoreCase("Certification")) {
            r = R.drawable.kudos_certification;
        } else if (type.equalsIgnoreCase("Birthday")) {
            r = R.drawable.kudos_birthday;
        } else if (type.equalsIgnoreCase("Anniversary")) {
            r = R.drawable.kudos_anniversary;
        } else if (type.equalsIgnoreCase("Award")) {
            r = R.drawable.kudos_achivement;
        }
        return r;
    }
}