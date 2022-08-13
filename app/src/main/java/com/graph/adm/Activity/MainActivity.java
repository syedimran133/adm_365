package com.graph.adm.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.graph.adm.Fragment.DocumentsPage;
import com.graph.adm.Fragment.MyCalendar;
import com.graph.adm.Fragment.Dashboard;
import com.graph.adm.Fragment.Documents;
import com.graph.adm.Fragment.More;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.databinding.ActivityMainBinding;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private FrameLayout frag;
    LinearLayout btn_home, btn_more, btn_documents, btn_calendar;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        frag = findViewById(R.id.fragment_container);
        btn_home = findViewById(R.id.btn_home);
        btn_more = findViewById(R.id.btn_more);
        btn_documents = findViewById(R.id.btn_documents);
        btn_calendar = findViewById(R.id.btn_calendar);
        AppSingle.getInstance().initActivity(this);
        FlowOrganizer.getInstance().initParentFrame(binding.fragmentContainer);
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setSelected(4);
                FlowOrganizer.getInstance().add(new More(), true);
            }
        });
        btn_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setSelected(3);
                FlowOrganizer.getInstance().add(new DocumentsPage(), true);
            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setSelected(2);
                FlowOrganizer.getInstance().add(new MyCalendar(), true);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setSelected(1);
                FlowOrganizer.getInstance().add(new Dashboard(), true);
            }
        });

        FlowOrganizer.getInstance().add(new Dashboard(), true);
    }

    public void setSelected(int type) {
        if (type == 1) {
            binding.homeIcon.setColorFilter(Color.argb(255, 255, 255, 255));
        binding.tvHome.setTextColor(Color.parseColor("#ffffff"));
        } else if (type == 2) {
            binding.calendarIcon.setColorFilter(Color.argb(255, 255, 255, 255));
            binding.tvCalendar.setTextColor(Color.parseColor("#ffffff"));
        } else if (type == 3) {
            binding.documentsIcon.setColorFilter(Color.argb(255, 255, 255, 255));
            binding.tvDocuments.setTextColor(Color.parseColor("#ffffff"));
        } else if (type == 4) {
            binding.moreIcon.setColorFilter(Color.argb(255, 255, 255, 255));
            binding.tvMore.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}