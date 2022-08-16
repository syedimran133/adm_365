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

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.graph.adm.Adapter.DocNavAdapter;
import com.graph.adm.Adapter.DocumentsAdapter;
import com.graph.adm.Adapter.ShareDocumentsAdapter;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutDocumentsBinding;
import com.graph.adm.model.documents.DocNavData;
import com.graph.adm.model.documents.myDocuments.MyDocumentsData;
import com.graph.adm.model.documents.sharedDocuments.SharedDocumentsValue;
import com.graph.adm.model.documents.sharedDocuments.SharedWithMeData;

import java.util.ArrayList;
import java.util.List;

public class SharedDocuments extends Fragment {

    private LayoutDocumentsBinding binding;
    private ShareDocumentsAdapter adpter;
    private boolean isVisible = false;
    DocNavAdapter adapter;
    String d_id = "";
    List<DocNavData> navData = new ArrayList<>();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            getMyDocumentsCallBack();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutDocumentsBinding.inflate(inflater, container, false);
        binding.tvTitle.setText("Shared Documents");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlowOrganizer.getInstance().add(new DocumentsPage());
            }
        });
        binding.search.setOnClickListener(v -> {
            binding.titleLl.setVisibility(View.GONE);
            binding.searchLl.setVisibility(View.VISIBLE);
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
        binding.closeSearch.setOnClickListener(v -> {
            binding.titleLl.setVisibility(View.VISIBLE);
            binding.searchLl.setVisibility(View.GONE);
        });
        navData.add(new DocNavData("Documents", "0"));
        setTitleAdapter();
        getMyDocumentsCallBack();
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
                "https://graph.microsoft.com/v1.0/me/drive/sharedWithMe",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    SharedWithMeData docData = new Gson().fromJson(response.toString(), SharedWithMeData.class);
                    if (docData.getValue().size() != 0) {
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new ShareDocumentsAdapter(getContext(), docData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(new ShareDocumentsAdapter.IonItemSelect() {
                            @Override
                            public void onItemSelect(int position) {
                                if (docData.getValue().get(position).getFile() == null) {
                                    d_id = docData.getValue().get(position).getRemoteItem().getParentReference().getDriveId();
                                    getInnerDocumentsCallBack(docData.getValue().get(position).getRemoteItem().getName(), docData.getValue().get(position).getRemoteItem().getParentReference().getDriveId(), docData.getValue().get(position).getId());
                                } else {
                                    try {
                                        Utils.downloadfile(docData.getValue().get(position).getMicrosoftGraphDownloadUrl(), docData.getValue().get(position).getName(), getContext());
                                    } catch (Exception e) {
                                        getDownloadCallBack(docData.getValue().get(position).getRemoteItem().getParentReference().getDriveId(), docData.getValue().get(position).getId());
                                    }
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

    private void getInnerDocumentsCallBack(String name, String drive_id, String item_id) {
        Utils.setProgressDialog(getContext());
        Log.e("URL :: ", "https://graph.microsoft.com/v1.0/me/drives/" + drive_id + "/items/" + item_id + "/children");
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/me/drives/" + drive_id + "/items/" + item_id + "/children",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    SharedWithMeData docData = new Gson().fromJson(response.toString(), SharedWithMeData.class);
                    if (docData.getValue().size() != 0) {
                        d_id=drive_id;
                        navData.add(new DocNavData(name, item_id));
                        setTitleAdapter();
                        binding.rv.setVisibility(View.VISIBLE);
                        binding.tvError.setVisibility(View.GONE);
                        adpter = new ShareDocumentsAdapter(getContext(), docData.getValue());
                        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        binding.rv.setAdapter(adpter);
                        adpter.registerOnItemClickListener(new ShareDocumentsAdapter.IonItemSelect() {
                            @Override
                            public void onItemSelect(int position) {
                                if (docData.getValue().get(position).getFile() == null) {
                                    if (docData.getValue().get(position).getFolder().getChildCount() != 0) {
                                        getInnerDocumentsCallBack(docData.getValue().get(position).getName(), docData.getValue().get(position).getParentReference().getDriveId(), docData.getValue().get(position).getId());
                                    } else {
                                        Toast.makeText(getContext(), "No Items inside this folder.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Utils.downloadfile(docData.getValue().get(position).getMicrosoftGraphDownloadUrl(), docData.getValue().get(position).getName(), getContext());
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

    private void getDownloadCallBack(String drive_id, String remort_id) {
        Utils.setProgressDialog(getContext());
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/me/drives/" + drive_id + "/items/" + remort_id,
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    SharedDocumentsValue docData = new Gson().fromJson(response.toString(), SharedDocumentsValue.class);
                    Utils.downloadfile(docData.getMicrosoftGraphDownloadUrl(), docData.getName(), getContext());
                    Utils.closeDilog();
                },
                error -> {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    Utils.closeDilog();
                });
    }

    private void setTitleAdapter() {
        adapter = new DocNavAdapter(getContext(), navData);
        binding.rvTitle.setAdapter(adapter);
        binding.rvTitle.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        adapter.registerOnItemClickListener(position -> {
            if (navData.get(position).getPath().equalsIgnoreCase("0")) {
                getMyDocumentsCallBack();
            } else {
                getInnerDocumentsCallBack(navData.get(position).getName(),d_id , navData.get(position).getPath());
            }
            if (position == 0) {
                navData.clear();
                navData.add(new DocNavData("Documents", "0"));
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
