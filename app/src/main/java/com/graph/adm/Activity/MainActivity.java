package com.graph.adm.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

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
                setSelected(4);
                try {
                    AppSingle.getInstance().getYouTubePlayerView().release();
                } catch (Exception e) {
                }
                FlowOrganizer.getInstance().add(new More(), false);
            }
        });
        btn_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(3);
                try {
                    AppSingle.getInstance().getYouTubePlayerView().release();
                } catch (Exception e) {
                }
                FlowOrganizer.getInstance().add(new DocumentsPage(), true);
            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(2);
                try {
                    AppSingle.getInstance().getYouTubePlayerView().release();
                } catch (Exception e) {
                }
                FlowOrganizer.getInstance().add(new MyCalendar(), true);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(1);
                try {
                    AppSingle.getInstance().getYouTubePlayerView().release();
                } catch (Exception e) {
                }
                FlowOrganizer.getInstance().add(new Dashboard(), true);
            }
        });

        FlowOrganizer.getInstance().add(new Dashboard(), true);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setSelected(int type) {
        if (type == 1) {
            binding.homeIcon.setImageDrawable(getDrawable(R.drawable.ic_fhome_sel));
            binding.tvHome.setTextColor(Color.parseColor("#ffffff"));
            binding.calendarIcon.setImageDrawable(getDrawable(R.drawable.calendar_footer));
            binding.tvCalendar.setTextColor(Color.parseColor("#6BDFFF"));
            binding.documentsIcon.setImageDrawable(getDrawable(R.drawable.docs_footer));
            binding.tvDocuments.setTextColor(Color.parseColor("#6BDFFF"));
            binding.moreIcon.setImageDrawable(getDrawable(R.drawable.more));
            binding.tvMore.setTextColor(Color.parseColor("#6BDFFF"));
        } else if (type == 2) {
            binding.homeIcon.setImageDrawable(getDrawable(R.drawable.home_alt));
            binding.tvHome.setTextColor(Color.parseColor("#6BDFFF"));
            binding.calendarIcon.setImageDrawable(getDrawable(R.drawable.ic_fcalendar_sel));
            binding.tvCalendar.setTextColor(Color.parseColor("#ffffff"));
            binding.documentsIcon.setImageDrawable(getDrawable(R.drawable.docs_footer));
            binding.tvDocuments.setTextColor(Color.parseColor("#6BDFFF"));
            binding.moreIcon.setImageDrawable(getDrawable(R.drawable.more));
            binding.tvMore.setTextColor(Color.parseColor("#6BDFFF"));
        } else if (type == 3) {
            binding.homeIcon.setImageDrawable(getDrawable(R.drawable.home_alt));
            binding.tvHome.setTextColor(Color.parseColor("#6BDFFF"));
            binding.calendarIcon.setImageDrawable(getDrawable(R.drawable.calendar_footer));
            binding.tvCalendar.setTextColor(Color.parseColor("#6BDFFF"));
            binding.documentsIcon.setImageDrawable(getDrawable(R.drawable.ic_fdocs_sel));
            binding.tvDocuments.setTextColor(Color.parseColor("#ffffff"));
            binding.moreIcon.setImageDrawable(getDrawable(R.drawable.more));
            binding.tvMore.setTextColor(Color.parseColor("#6BDFFF"));
        } else if (type == 4) {
            binding.homeIcon.setImageDrawable(getDrawable(R.drawable.home_alt));
            binding.tvHome.setTextColor(Color.parseColor("#6BDFFF"));
            binding.calendarIcon.setImageDrawable(getDrawable(R.drawable.calendar_footer));
            binding.tvCalendar.setTextColor(Color.parseColor("#6BDFFF"));
            binding.documentsIcon.setImageDrawable(getDrawable(R.drawable.docs_footer));
            binding.tvDocuments.setTextColor(Color.parseColor("#6BDFFF"));
            binding.moreIcon.setImageDrawable(getDrawable(R.drawable.ic_more_sel));
            binding.tvMore.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public void onBackPressed() {
        if (FlowOrganizer.getInstance().hasNoMoreBacks()) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            finishAffinity();
        } else {
            super.onBackPressed();
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            getLifecycle().addObserver(AppSingle.getInstance().getYouTubePlayerView());
            AppSingle.getInstance().getYouTubePlayerView().addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    try {
                        AppSingle.getInstance().setYouTubePlayerView(AppSingle.getInstance().getYouTubePlayerView());
                        youTubePlayer.loadVideo(AppSingle.getInstance().getmVideosId(), 0);
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSelected(1);
    }
}