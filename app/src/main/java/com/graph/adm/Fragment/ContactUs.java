package com.graph.adm.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.graph.adm.Adapter.ContactUsAdapter;
import com.graph.adm.Adapter.HelpAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutHelpBinding;
import com.graph.adm.model.contactus.ContactUsData;
import com.graph.adm.model.contactus.Fields;
import com.graph.adm.model.contactus.Value;
import com.graph.adm.model.help.HelpData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactUs extends Fragment {

    private LayoutHelpBinding binding;
    ContactUsAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<Fields>> expandableListDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutHelpBinding.inflate(inflater, container, false);
        binding.title.setText("Contact US");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().popUpBackTo(1);
            }
        });
        getHelpCallBack();
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


    private void getHelpCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/aa2c972f-87b4-4bb1-be20-55debf616242/items?$expand=fields($select=id,Title,LocationCity,Address,IsActive)&filter=fields/IsActive eq 'Yes'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    ContactUsData docData = new Gson().fromJson(response.toString(), ContactUsData.class);
                    if (docData.getValue().size() != 0) {
                        getData(docData);
                    } else {
                        binding.expandableListView.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }

                    Utils.closeDilog();
                },
                error -> {
                    Utils.closeDilog();
                    binding.expandableListView.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                });
    }

    public void getData2(ContactUsData docData) {
        getData2(docData);
        expandableListDetail = new HashMap<String, List<Fields>>();
        for (int i = 0; i < docData.getValue().size(); i++) {
            ArrayList a = new ArrayList<>();
            a.add(docData.getValue().get(i).getFields().getLocationCity() + "\n" + docData.getValue().get(i).getFields().getAddress());
            expandableListDetail.put(docData.getValue().get(i).getFields().getId() + docData.getValue().get(i).getFields().getTitle(), a);
        }
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ContactUsAdapter(getContext(), expandableListTitle, expandableListDetail);
        binding.expandableListView.setAdapter(expandableListAdapter);
    }

    public void getData(ContactUsData docData) {
        List<Fields> students=new ArrayList<>();
        for (int i = 0; i < docData.getValue().size(); i++) {
           students.add(docData.getValue().get(i).getFields());
        }
        Map<String, List<Fields>> expandableListDetail
                = students.stream()
                .collect(Collectors.groupingBy(Fields::getTitle));

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ContactUsAdapter(getContext(), expandableListTitle, expandableListDetail);
        binding.expandableListView.setAdapter(expandableListAdapter);
    }
}