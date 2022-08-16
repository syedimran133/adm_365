package com.graph.adm.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Adapter.ToDoListAdapter;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutToDoListBinding;
import com.graph.adm.model.documents.myDocuments.MyDocumentsData;
import com.graph.adm.model.todolist.Progressdata;
import com.graph.adm.model.todolist.getid.ToDoListGetIdData;
import com.graph.adm.model.todolist.list.ToDoListData;
import com.graph.adm.model.todolist.list.ToDoListValue;

import java.util.ArrayList;
import java.util.List;

public class ToDoList extends Fragment {

    private LayoutToDoListBinding binding;
    private ToDoListAdapter adpter;
    private ToDoList frag;
    private Progressdata progressdata;
    private String ids="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutToDoListBinding.inflate(inflater, container, false);
        frag = this;
        binding.back.setOnClickListener(v -> FlowOrganizer.getInstance().add(new Dashboard(), false));
        binding.btnAdd.setOnClickListener(v -> {
            AddNewTaskBottomsheetFragment addNewTaskBottomsheetFragment = new AddNewTaskBottomsheetFragment();
            addNewTaskBottomsheetFragment.setvalue(getContext(),frag,ids);
            addNewTaskBottomsheetFragment.show(getParentFragmentManager(), addNewTaskBottomsheetFragment.getTag());
        });
        binding.btnFilters.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(getContext(), binding.btnFilters);
            popup.getMenuInflater()
                    .inflate(R.menu.poupup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(
                        getContext(),
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            });
            popup.show();
        });
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getToDoListIdCallBack(AppSingle.getInstance().getUserEmail());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getToDoListIdCallBack(String email) {
        Utils.setProgressDialog(getContext());
        Log.d("GURL :: ", "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists('User Information List')/items?expand=fields(select=Id,Email)&$filter=fields/EMail eq '" + email.trim() + "'");
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists('User Information List')/items?expand=fields(select=Id,Email)&$filter=fields/EMail eq '" + email.trim() + "'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Utils.closeDilog();
                    ToDoListGetIdData data = new Gson().fromJson(response.toString(), ToDoListGetIdData.class);
                    ids=data.getValue().get(0).getFields().getId();
                    getToDoListCallBack(data.getValue().get(0).getFields().getId());
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

    void getToDoListCallBack(String id) {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/374b2ad9-6521-48ed-915c-98816e66a970/items?$expand=fields($select=Title,Progress,Description,Title,AssignedTo,Priority,DueDate,Author)&filter=fields/AssignedToLookupId eq '" + id + "'",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    ToDoListData docData = new Gson().fromJson(response.toString(), ToDoListData.class);
                    if (docData.getValue().size() != 0) {
                        displayCount(docData.getValue());
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new ToDoListAdapter(getContext(), frag, ids,docData.getValue());
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
    private void displayCount(List<ToDoListValue> value) {

        int total_count = 0;
        int blocked_count = 0;
        int Completed_count = 0;
        int InProgress_count = 0;
        int NotStarted_count = 0;
        for (int i = 0; i < value.size(); i++) {
            total_count = value.size();
            if (value.get(i).getFields().getProgress().equalsIgnoreCase("Blocked")) {
                blocked_count++;
            }
            if (value.get(i).getFields().getProgress().equalsIgnoreCase("Completed")) {
                Completed_count++;
            }
            if (value.get(i).getFields().getProgress().equalsIgnoreCase("In progress")) {
                InProgress_count++;
            }
            //Not strated
            if (value.get(i).getFields().getProgress().equalsIgnoreCase("Not strated")) {
                NotStarted_count++;
            }
        }
        progressdata = new Progressdata(total_count, blocked_count , Completed_count , InProgress_count , NotStarted_count,0);
        binding.tvAssignedTasks.setText(total_count + "");
        binding.tvWorkingOn.setText(InProgress_count + "");
        binding.tvCompleted.setText(Completed_count + "");
        binding.tvHold.setText(blocked_count + "");
        Utils.setupPieChart(binding.piechartAssigned,Utils.getPieEntry((float)NotStarted_count,(float)Completed_count,(float)InProgress_count,(float)blocked_count,0));
        Utils.setupPieChart(binding.piechartCompleted,Utils.getPieEntry(0,(float)Completed_count,0,0,(float) (total_count-Completed_count)));
        Utils.setupPieChart(binding.piechartWorkingOn,Utils.getPieEntry(0,0,(float)InProgress_count,0,(float) (total_count-InProgress_count)));
        Utils.setupPieChart(binding.piechartHold,Utils.getPieEntry(0,0,0,(float)blocked_count,(float) (total_count-blocked_count)));
    }


}

