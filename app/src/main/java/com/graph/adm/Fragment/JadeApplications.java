package com.graph.adm.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.graph.adm.Adapter.JadeApplicationAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.GridSpacingItemDecoration;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.JadeApplicationsBinding;
import com.graph.adm.model.jadeapp.JadeAppValue;
import com.graph.adm.model.jadeapp.JadeApplicationData;

import java.util.ArrayList;
import java.util.List;

public class JadeApplications extends Fragment {

    private JadeApplicationsBinding binding;
    private JadeApplicationAdapter adpter;
    int spanCount = 2; // 3 columns
    int spacing = 10; // 50px
    boolean includeEdge = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = JadeApplicationsBinding.inflate(inflater, container, false);

        getGetImagesBack();
        binding.backJa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new Dashboard(), false);
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

    private void getGetImagesBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/b43b6506-5505-4c9b-98fc-f2df9d1125ba/items?$expand=fields($select=Title,Description,Icon,LinkUrl,IsDisabled)&filter=fields/IsDisabled eq false",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Utils.closeDilog();
                    JadeApplicationData userData = new Gson().fromJson(response.toString(), JadeApplicationData.class);
                    userData.getValue().get(2).getFields().setIsDisabled(true);
                    List<JadeAppValue> data=filtervalues(userData.getValue());
                    adpter = new JadeApplicationAdapter(getContext(),data);
                    binding.rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    binding.rv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
                    binding.rv.setAdapter(adpter);
                    adpter.registerOnItemClickListener(position -> {
                        FragmentWebview photoSingel=new FragmentWebview();
                        Bundle bundle = new Bundle();
                        bundle.putString("URL",data.get(position).getFields().getLinkUrl().getUrl());
                        bundle.putString("TITLE",data.get(position).getFields().getTitle());
                        FlowOrganizer.getInstance().add(photoSingel, true,bundle);

                        /*Intent i = new Intent(getContext(), JUni.class);
                        i.putExtra("URL",data.get(position).getFields().getLinkUrl().getUrl());
                        startActivity(i);*/
                    });
                },
                error -> {
                    Utils.closeDilog();
                    Log.d("TAG", "Error: " + error.toString());
                });
    }

    private List<JadeAppValue> filtervalues(List<JadeAppValue> list) {
        List<JadeAppValue> filtervalue = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getFields().getIsDisabled()) {

                filtervalue.add(list.get(i));
            }
        }
        return filtervalue;
    }
}
