package com.graph.adm.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.JsonObject;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutModifyTaskBinding;
import com.graph.adm.model.todolist.list.ToDoListValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModifyTaskBottomsheetFragment extends BottomSheetDialogFragment {

    private LayoutModifyTaskBinding binding;
    private ToDoListValue doListValue;
    private String select="";
    private Context mContext;
    private ToDoList fragment;
    private String ids;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutModifyTaskBinding.inflate(inflater, container, false);

        String[] stringArray = getResources().getStringArray(R.array.status);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.dropdown_item_hint, stringArray);
        adapter.setDropDownViewResource(R.layout.dropdown_item);
        binding.autoCompleteTextView.setAdapter(adapter);
        binding.autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select=stringArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.btnCancel.setOnClickListener(v -> dismiss());
        binding.btnModify.setOnClickListener(v -> {
            try {
                postDataUsingVolley(select,binding.edNote.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
        try {
            binding.tvTaskName.setText(doListValue.getFields().getTitle());
            binding.tvTaskDetails.setText(doListValue.getFields().getDescription());
            binding.autoCompleteTextView.setSelection(findUsingStream(doListValue.getFields().getProgress(),stringArray)-1);
            binding.tvDate.setText(Utils.getStringDateString(doListValue.getFields().getDueDate(), "EEE, dd MMM YY"));
            binding.edNote.setText(doListValue.getFields().getNote());
        } catch (Exception e) {
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public int findUsingStream(String search, String[] list) {
        int index= 0,i=0;

        for(String str: list) {
            i++;
            if (str.equalsIgnoreCase(search)) {
                index=i;
            }
        }

        return index;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setvalue(Context mContext, ToDoList fragment,String ids, ToDoListValue doListValue) {
        this.doListValue = doListValue;
        this.mContext=mContext;
        this.fragment=fragment;
        this.ids=ids;

    }
    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
    private void postDataUsingVolley(String progress, String note) throws JSONException {
        Utils.setProgressDialog(mContext);
        String url = "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/374b2ad9-6521-48ed-915c-98816e66a970/items/"+doListValue.getId()+"/fields";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JSONObject parameters = new JSONObject();
        parameters.put("Progress", progress);
        parameters.put("Notes", note);;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, url,parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(mContext, "successfully updated", Toast.LENGTH_SHORT).show();
                Utils.closeDilog();
                fragment.getToDoListCallBack(ids);
                dismiss();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.closeDilog();
                Toast.makeText(mContext, "Fail to get response = " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Prefer", "HonorNonIndexedQueriesWarningMayFailRandomly");
                headers.put("X-HTTP-Method-Override","PATCH");
                headers.put("Authorization", "Bearer " + AppSingle.getInstance().getmAccessToken());
                return headers;
            }
        };
        queue.add(request);
    }
}
