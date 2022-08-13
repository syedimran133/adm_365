package com.graph.adm.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.graph.adm.R;
import com.graph.adm.databinding.LayoutAddNewTaskBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewTaskBottomsheetFragment extends BottomSheetDialogFragment{

    private LayoutAddNewTaskBinding binding;
    final Calendar myCalendar= Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        binding = LayoutAddNewTaskBinding.inflate(inflater, container, false);

        String[] mPriority = getResources().getStringArray(R.array.priority);
        ArrayAdapter<String> adapterPriority = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item_hint, mPriority);
        adapterPriority.setDropDownViewResource(R.layout.dropdown_item);
        binding.taskPrority.setAdapter(adapterPriority);

        String[] mCategory = getResources().getStringArray(R.array.category);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(getContext(), R.layout.dropdown_item_hint, mCategory);
        adapterPriority.setDropDownViewResource(R.layout.dropdown_item);
        binding.taskCategory.setAdapter(adapterCategory);
        //setDate();
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        binding.tvDate.setOnClickListener(v -> new DatePickerDialog(getContext(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        binding.btnAssign.setOnClickListener(v -> dismiss());
        binding.btnCancel.setOnClickListener(v -> dismiss());
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
public void setDate(){
    myCalendar.set(Calendar.YEAR, myCalendar.get(Calendar.YEAR));
    myCalendar.set(Calendar.MONTH,myCalendar.get(Calendar.MONTH));
    myCalendar.set(Calendar.DAY_OF_MONTH,myCalendar.get(Calendar.DAY_OF_MONTH)+10);
    updateLabel();
}
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void updateLabel(){
        String myFormat="EEE, dd MMM YYYY";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        binding.tvDate.setText(dateFormat.format(myCalendar.getTime()));
    }
    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }

}
