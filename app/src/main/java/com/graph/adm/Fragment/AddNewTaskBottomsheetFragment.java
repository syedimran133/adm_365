package com.graph.adm.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutAddNewTaskBinding;
import com.graph.adm.model.todolist.list.ToDoListValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddNewTaskBottomsheetFragment extends BottomSheetDialogFragment {

    private LayoutAddNewTaskBinding binding;
    final Calendar myCalendar = Calendar.getInstance();
    private String category = "", prority = "";
    private Context mContext;
    private String select = "";
    private ToDoList fragment;
    private String ids;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutAddNewTaskBinding.inflate(inflater, container, false);
        mContext = getContext();
        String[] mPriority = getResources().getStringArray(R.array.priority);
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item_hint, mPriority);
        adapterPriority.setDropDownViewResource(R.layout.dropdown_item);
        binding.taskPrority.setAdapter(adapterPriority);

        String[] mCategory = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(getContext(), R.layout.dropdown_item_hint, mCategory);
        adapterPriority.setDropDownViewResource(R.layout.dropdown_item);
        binding.taskCategory.setAdapter(adapterCategory);
        //setDate();

        binding.taskCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = mCategory[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.taskPrority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prority = mPriority[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };
        binding.tvDate.setOnClickListener(v -> new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        binding.btnAssign.setOnClickListener(v -> {
            dismiss();
            if (isValid()) {
                try {
                    postDataUsingVolley();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        binding.btnCancel.setOnClickListener(v -> dismiss());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setvalue(Context mContext, ToDoList fragment, String ids) {
        this.mContext = mContext;
        this.fragment = fragment;
        this.ids = ids;

    }

    public void setDate() {
        myCalendar.set(Calendar.YEAR, myCalendar.get(Calendar.YEAR));
        myCalendar.set(Calendar.MONTH, myCalendar.get(Calendar.MONTH));
        myCalendar.set(Calendar.DAY_OF_MONTH, myCalendar.get(Calendar.DAY_OF_MONTH) + 10);
        updateLabel();
    }

    private boolean isValid() {
        boolean result = true;
        if (binding.edTaskName.getText().toString().length() == 0) {
            //UiUtil.showToast(this, "Please enter first name");
            binding.edTaskName.setBackgroundResource(R.drawable.edittex_errort_border);
            binding.errorName.setVisibility(View.VISIBLE);
            result = false;
        }
        if (binding.edTaskInDetail.getText().toString().length() < 5) {
            //UiUtil.showToast(this, "Please enter first name min two character");
            binding.edTaskInDetail.setBackgroundResource(R.drawable.edittex_errort_border);
            binding.errorDetail.setVisibility(View.VISIBLE);
            result = false;
        }
        if (prority.equalsIgnoreCase("Select Priority")) {
            // UiUtil.showToast(this, "Please enter last name");
            binding.llTaskPrority.setBackgroundResource(R.drawable.edittex_errort_border);
            binding.errorPrority.setVisibility(View.VISIBLE);
            result = false;
        }
        if (category.equalsIgnoreCase("Select Category")) {
            // UiUtil.showToast(this, "Please enter last name min two character");
            binding.llTaskCategory.setBackgroundResource(R.drawable.edittex_errort_border);
            binding.errorCategory.setVisibility(View.VISIBLE);
            result = false;
        }
        if (binding.tvDate.getText().toString().length() == 0) {
            // UiUtil.showToast(this, "Please enter valid phone number");
            binding.tvDate.setBackgroundResource(R.drawable.edittex_errort_border);
            binding.errorDate.setVisibility(View.VISIBLE);
            result = false;
        }
        return result;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateLabel() {
        String myFormat = "EEE, dd MMM YYYY";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.tvDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    private String getDate() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        return dateFormat.format(myCalendar.getTime());
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }

    private void postDataUsingVolley() throws JSONException {
        Utils.setProgressDialog(mContext);
        String url = "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/374b2ad9-6521-48ed-915c-98816e66a970/items";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JSONObject parameters = new JSONObject();
        JSONObject param_fileds = new JSONObject();
        param_fileds.put("Title", binding.edTaskName.getText().toString());
        param_fileds.put("Description", binding.edTaskInDetail.getText().toString());
        param_fileds.put("Category", category);
        param_fileds.put("Progress", "Not strated");
        param_fileds.put("Priority", prority);
        param_fileds.put("DueDate", getDate());
        param_fileds.put("AssignedToLookupId", ids);
        parameters.put("fields", param_fileds);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, response -> {
            Toast.makeText(mContext, "successfully updated", Toast.LENGTH_SHORT).show();
            Utils.closeDilog();
            fragment.getToDoListCallBack(ids);
            dismiss();
        }, error -> {
            Utils.closeDilog();
            Toast.makeText(mContext, "Fail to get response = " + error.toString(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Prefer", "HonorNonIndexedQueriesWarningMayFailRandomly");
                headers.put("Authorization", "Bearer " + AppSingle.getInstance().getmAccessToken());
                return headers;
            }
        };
        queue.add(request);
    }
}
