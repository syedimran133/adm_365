package com.graph.adm.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.graph.adm.Adapter.DocNavAdapter;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Adapter.HrDocumentsAdapter;
import com.graph.adm.Adapter.ShareDocumentsAdapter;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutDocumentsBinding;
import com.graph.adm.model.documents.DocNavData;
import com.graph.adm.model.documents.HrPolocies.HrPoliciesData;
import com.graph.adm.model.documents.myDocuments.MyDocumentsData;
import com.graph.adm.model.documents.sharedDocuments.SharedWithMeData;

import java.util.ArrayList;
import java.util.List;

public class HRPolocies extends Fragment {

    private LayoutDocumentsBinding binding;
    private HrDocumentsAdapter adpter;
    private boolean isVisible = false;
    DocNavAdapter adapter;
    List<DocNavData> navData = new ArrayList<>();
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            getHrDocumentsCallBack();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutDocumentsBinding.inflate(inflater, container, false);
        getHrDocumentsCallBack();
        binding.tvTitle.setText(getString(R.string.hr_policy));
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new DocumentsPage());
            }
        });
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                adpter.getFilter().filter(s.toString());
            }
        });
        binding.search.setOnClickListener(v -> {
            binding.titleLl.setVisibility(View.GONE);
            binding.searchLl.setVisibility(View.VISIBLE);
        });
        binding.closeSearch.setOnClickListener(v -> {
            binding.titleLl.setVisibility(View.VISIBLE);
            binding.searchLl.setVisibility(View.GONE);
        });
        navData.add(new DocNavData("HR Policy", "0"));
        setTitleAdapter();
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

    private void getHrDocumentsCallBack() {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqCv5hvapwM0QaUOK3v0919J/items/root/children",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    HrPoliciesData docData = new Gson().fromJson(response.toString(), HrPoliciesData.class);

                    if (docData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new HrDocumentsAdapter(getContext(),docData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(new HrDocumentsAdapter.IonItemSelect() {
                            @Override
                            public void onItemSelect(int position) {
                                if (docData.getValue().get(position).getMicrosoftGraphDownloadUrl() == null) {
                                    getInnerDocumentsCallBack(docData.getValue().get(position).getName(),docData.getValue().get(0).getParentReference().getPath()+"/"+docData.getValue().get(0).getName().replace(" ", "%20"));
                                }else{
                                    Utils.downloadfile(docData.getValue().get(position).getMicrosoftGraphDownloadUrl(),docData.getValue().get(position).getName(),getContext());
                                }
                            }
                        });
                    } else {
                        binding.rv.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }
                    Utils.closeDilog();
                },
                error -> {
                    binding.rv.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                    Utils.closeDilog();
                });
    }

    private void getInnerDocumentsCallBack(String name,String path) {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859"+path.replace("root:","items/root:/")+":/children",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    HrPoliciesData docData = new Gson().fromJson(response.toString(), HrPoliciesData.class);
                    if (docData.getValue().size() != 0) {
                        navData.add(new DocNavData(name, path));
                        setTitleAdapter();
                        adpter = new HrDocumentsAdapter(getContext(),docData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(new HrDocumentsAdapter.IonItemSelect() {
                            @Override
                            public void onItemSelect(int position) {
                                if (docData.getValue().get(position).getFile() == null) {
                                    getInnerDocumentsCallBack(docData.getValue().get(position).getName(),docData.getValue().get(0).getParentReference().getPath()+"/"+docData.getValue().get(0).getName().replace(" ", "%20"));
                                }else{
                                    Utils.downloadfile(docData.getValue().get(position).getMicrosoftGraphDownloadUrl(),docData.getValue().get(position).getName(),getContext());
                                }
                            }
                        });
                    } else {
                        binding.rv.setVisibility(View.GONE);
                        binding.tvError.setVisibility(View.VISIBLE);
                        binding.tvError.setText("No Data");
                    }

                    Utils.closeDilog();
                },
                error -> {

                    binding.rv.setVisibility(View.GONE);
                    binding.tvError.setVisibility(View.VISIBLE);
                    if (error.getMessage() != null) {
                        binding.tvError.setText(error.getMessage());
                    } else {
                        binding.tvError.setText(error.toString());
                    }
                    Utils.closeDilog();
                });
    }

    private void setTitleAdapter() {
        adapter = new DocNavAdapter(getContext(), navData);
        binding.rvTitle.setAdapter(adapter);
        binding.rvTitle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapter.registerOnItemClickListener(position -> {
            if (navData.get(position).getPath().equalsIgnoreCase("0")) {
                getHrDocumentsCallBack();
            } else {
                getInnerDocumentsCallBack( navData.get(position).getName(),navData.get(position).getPath());
            }
            if (position == 0) {
                navData.clear();
                navData.add(new DocNavData("HR Policy", "0"));
                setTitleAdapter();
            } else {
                List<DocNavData> temp = new ArrayList<>();
                temp.addAll(filter(navData, position));
                navData.clear();
                navData.addAll(temp);
                setTitleAdapter();
            }
        });
    }

    private List<DocNavData> filter(List<DocNavData> data, int index) {
        List<DocNavData> temp = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            temp.add(data.get(i));
        }
        return temp;
    }
}
