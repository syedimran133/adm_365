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
import com.graph.adm.Adapter.MyCalendarAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutCalendarBinding;
import com.graph.adm.model.calendar.CalendarData;
import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.options.HeaderOption;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.requests.extensions.EventCollectionPage;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IEventCollectionPage;

import java.util.LinkedList;

public class MyCalendar extends Fragment {
    private LayoutCalendarBinding binding;
    private MyCalendarAdapter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutCalendarBinding.inflate(inflater, container, false);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new Dashboard());
            }
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMyCalCallBack(Utils.getCalculatedDate(0), Utils.getCalculatedDate(14));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getMyCalCallBack(String s, String e) {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/me/calendarview?startdatetime=" + s + "&enddatetime=" + e,
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    CalendarData docData = new Gson().fromJson(response.toString(), CalendarData.class);
                    if (docData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new MyCalendarAdapter(getContext(), docData.getValue());
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
                    Log.d("TAG", "Error: " + error.toString());
                    binding.rv.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                });
        /*IGraphServiceClient graphClient =
                GraphServiceClient
                        .builder()
                        .authenticationProvider(new IAuthenticationProvider() {
                            @Override
                            public void authenticateRequest(IHttpRequest request) {
                                Log.d("TAG", "Authenticating request," + request.getRequestUrl());
                                request.addHeader("Authorization", "Bearer " + AppSingle.getInstance());
                            }
                        })
                        .buildClient();
        LinkedList<Option> requestOptions = new LinkedList<Option>();
        requestOptions.add(new HeaderOption("Prefer", "odata.maxpagesize=2"));
        requestOptions.add(new QueryOption("startdatetime", "2022-07-27T19:53:35Z"));
        requestOptions.add(new QueryOption("enddatetime", "2022-08-10T19:53:35Z"));

        IEventCollectionPage calendarView = graphClient.me().calendarView()
                .buildRequest( requestOptions )
                .get();
        String str=calendarView.getRawObject().toString();*/
    }
}
