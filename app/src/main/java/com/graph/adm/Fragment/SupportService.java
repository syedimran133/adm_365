package com.graph.adm.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Adapter.DocumentsFragmentPagerAdapter;
import com.graph.adm.Adapter.SupportFragmentPagerAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutSupportServiceBinding;
import com.graph.adm.model.documents.myDocuments.MyDocumentsData;
import com.graph.adm.model.support.SupportServiceData;
import com.graph.adm.model.support.SupportServiceValue;

import java.util.List;

public class SupportService extends Fragment {

    private LayoutSupportServiceBinding binding;
    List<SupportServiceValue> outerData;

    //1.JService 2.Static 3.JOSS 4.ZingHR ,5.Home 6.Profile 7.Contact
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutSupportServiceBinding.inflate(inflater, container, false);
        binding.back.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Dashboard(), false));


        getMyDocumentsCallBack();

        binding.btnJservice.setOnClickListener(v -> {
            JService jService = new JService();
            jService.newInstance(outerData.get(0).getFields());
            FlowOrganizer.getInstance().add(jService);
        });
        binding.btnJoss.setOnClickListener(v -> {
            Joss joss = new Joss();
            joss.newInstance(outerData.get(2).getFields());
            FlowOrganizer.getInstance().add(joss);
        });
        binding.btnZinghr.setOnClickListener(v -> {
            ZingHR joss = new ZingHR();
            joss.newInstance(outerData.get(3).getFields());
            FlowOrganizer.getInstance().add(joss);
        });
        binding.btnJbuzz.setOnClickListener(v -> {
            JBuzz joss = new JBuzz();
            joss.newInstance(outerData.get(4).getFields());
            FlowOrganizer.getInstance().add(joss);
        });
        binding.btnMynexthire.setOnClickListener(v -> {
            JRecruit joss = new JRecruit();
            joss.newInstance(outerData.get(5).getFields());
            FlowOrganizer.getInstance().add(joss);
        });
        binding.btnJadeUniversity.setOnClickListener(v -> {
            Contact joss = new Contact();
            joss.newInstance(outerData.get(6).getFields());
            FlowOrganizer.getInstance().add(joss);
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

    private void getMyDocumentsCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/ebd8d60f-685d-4457-9f59-ad616b88baaa/items?$expand=fields($select=Title,Description,ServiceType,PrimaryContactEmail,PrimaryContactPhone)",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    SupportServiceData data = new Gson().fromJson(response.toString(), SupportServiceData.class);
                    binding.tvTitle.setText(data.getValue().get(1).getFields().getTitle());
                    binding.tvDescription.setText(data.getValue().get(1).getFields().getDescription());
                    binding.tvStaticEmail.setText("Email : " + data.getValue().get(1).getFields().getPrimaryContactEmail());
                    binding.tvStaticMobile.setText("Phone : " + data.getValue().get(1).getFields().getPrimaryContactPhone().toString());
                    outerData = data.getValue();
                   /* binding.viewpager.setAdapter(new SupportFragmentPagerAdapter(getChildFragmentManager(),
                            getContext(),data.getValue()));
                    TabLayout tabLayout = binding.slidingTabs2;
                    tabLayout.setupWithViewPager(binding.viewpager);*/
                    Utils.closeDilog();
                },
                error -> {
                    Utils.closeDilog();
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                });
    }
}
