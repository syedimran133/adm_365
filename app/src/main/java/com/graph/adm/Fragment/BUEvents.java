package com.graph.adm.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.graph.adm.Adapter.BUEventsAdapter;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Adapter.MyCalendarAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutBuEventsBinding;
import com.graph.adm.databinding.LayoutCalendarBinding;
import com.graph.adm.model.buevents.BUEventsData;
import com.graph.adm.model.documents.myDocuments.MyDocumentsData;

public class BUEvents extends Fragment {

    private LayoutBuEventsBinding binding;
    BUEventsAdapter adpter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutBuEventsBinding.inflate(inflater, container, false);
        binding.back.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Dashboard(), false));
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBUEventsCallBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getBUEventsCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/f5c13d09-827f-403c-969f-1d894c15b69c/items?$expand=fields($select=Title,Location,EventDate,EndDate,Description,EventDescription,RegistrationLink,Category)&$filter=fields/EventDate ge '"+Utils.getCalculatedDate(0)+"'&$orderby=fields/EventDate",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    BUEventsData docData = new Gson().fromJson(response.toString(), BUEventsData.class);
                    if (docData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new BUEventsAdapter(getContext(), docData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                    } else {
                        binding.rv.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }

                    Utils.closeDilog();
                },
                error -> {
                    Utils.closeDilog();
                    binding.rv.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                });
    }
}
