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
import com.graph.adm.Adapter.NotificationAdapter;
import com.graph.adm.Adapter.ToDoListAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutNoteficationsBinding;
import com.graph.adm.model.notifications.NotificationsData;
import com.graph.adm.model.todolist.getid.ToDoListGetIdData;
import com.graph.adm.model.todolist.list.ToDoListData;

public class Notifications extends Fragment {

    private LayoutNoteficationsBinding binding;
    private NotificationAdapter adpter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutNoteficationsBinding.inflate(inflater, container, false);

        binding.back.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Dashboard(), false));
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNotificationsListIdCallBack(AppSingle.getInstance().getUserEmail());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getNotificationsListIdCallBack(String email) {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists('User Information List')/items?expand=fields(select=Id,Email)&$filter=fields/EMail eq '"+email+"'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Utils.closeDilog();
                    ToDoListGetIdData data = new Gson().fromJson(response.toString(), ToDoListGetIdData.class);
                    getNotificationsListCallBack(data.getValue().get(0).getFields().getId());
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
    private void getNotificationsListCallBack(String id) {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/5131ca4d-4d0e-4092-9196-93b42fde665b/items?$expand=fields($select=Title,Description,ExpireDate,NotificationTo,NotificationAuthor,Status,IsRead)&filter=fields/IsRead eq 'No' and fields/Status eq 'Approved' and fields/NotificationToLookupId eq '"+id+"' and fields/ExpireDate ge '"+Utils.getCalculatedDate(0)+"'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    NotificationsData docData = new Gson().fromJson(response.toString(), NotificationsData.class);
                    if (docData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new NotificationAdapter(getContext(),docData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        AppSingle.getInstance().setNotification_count(docData.getValue().size());
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
