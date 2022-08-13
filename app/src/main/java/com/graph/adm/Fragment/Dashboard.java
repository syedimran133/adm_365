package com.graph.adm.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.graph.adm.Activity.Chat;
import com.graph.adm.Adapter.AutoScrollPagerAdapter;
import com.graph.adm.Adapter.HrDocumentsAdapter;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.FlowOrganizer;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.Utils.Utils;
import com.graph.adm.databinding.LayoutDashboardBinding;
import com.graph.adm.model.UserData;
import com.graph.adm.model.certifications.CertificationsData;
import com.graph.adm.model.certifications.CertificationsDataValue;
import com.graph.adm.model.documents.HrPolocies.HrPoliciesData;
import com.graph.adm.model.email.EmailData;
import com.graph.adm.model.video.VideosData;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends Fragment {

    private LayoutDashboardBinding binding;
    private static final int AUTO_SCROLL_THRESHOLD_IN_MILLI = 5000;
    private AutoScrollPagerAdapter autoScrollPagerAdapter = null;
    private YouTubePlayerView youTubePlayerView = null;
    public static List<CertificationsDataValue> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = LayoutDashboardBinding.inflate(inflater, container, false);

        youTubePlayerView = binding.youtubePlayerView;
        binding.btnAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new Announcement(), true);
            }
        });
        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new Notifications(), true);
            }
        });
        binding.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new FragmentEmail(), true);
            }
        });
        binding.btnjGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new Gallary(), true);
            }
        });
        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                //FlowOrganizer.getInstance().add(new FragmentChat(), false);
                Intent intent = new Intent(getContext(), Chat.class);
                startActivity(intent);
            }
        });
        binding.btnBuEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new BUEvents(), true);
            }
        });
        binding.btnJadeApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new JadeApplications(), true);
            }
        });
        binding.btnServiceSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new SupportService(), true);
            }
        });
        binding.btnToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new ToDoList(), true);
            }
        });
        binding.btnBlogsWhitePapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.release();
                FlowOrganizer.getInstance().add(new BlogsWhitePapers(), true);
            }
        });

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUserCallBack();

        play(AppSingle.getInstance().getmVideosId());
    }

    @Override
    public void onPause() {
        super.onPause();
        youTubePlayerView.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AppSingle.getInstance().getNotification_count() == 0) {
            binding.notificationCount.setVisibility(View.GONE);
        } else {
            binding.notificationCount.setVisibility(View.VISIBLE);
            binding.notificationCount.setText(String.valueOf(AppSingle.getInstance().getNotification_count()));
        }
        play(AppSingle.getInstance().getmVideosId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        youTubePlayerView.release();
    }

    public void runSlider(int size) {
        autoScrollPagerAdapter = new AutoScrollPagerAdapter(getContext(), getChildFragmentManager(), size);
        binding.viewPager.setAdapter(autoScrollPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.startAutoScroll();
        binding.viewPager.setInterval(AUTO_SCROLL_THRESHOLD_IN_MILLI);
        binding.viewPager.setCycle(true);

    }

    private void getUserCallBack() {
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/me",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    /* Successfully called graph, process data and send to UI */
                    Log.d("TAG", "Response: " + response.toString());
                    UserData userData = new Gson().fromJson(response.toString(), UserData.class);
                    AppSingle.getInstance().setUserName(userData.getDisplayName());
                    AppSingle.getInstance().setUserEmail(userData.getMail());
                    try {
                        binding.tvName.setText(userData.getDisplayName());
                    } catch (Exception e) {
                    }
                    getPhotoCallBack();
                },
                error -> {
                    Log.d("TAG", "Error: " + error.toString());
                    //displayError(error);
                });
    }

    private void getCertificationsCallBack() {
        try {
            MSGraphRequestWrapper.callGraphAPIUsingVolley(
                    getContext(),
                    "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/9c4b01d9-2d34-4b1f-8048-67f2403ece23/items?$expand=fields($select=id,AssociateName,WishType,Designation,Description,GreetingText)",
                    AppSingle.getInstance().getmAccessToken(),
                    response -> {
                        /* Successfully called graph, process data and send to UI */
                        getUnReadEmailCountCallBack();
                        Log.d("TAG", "Response: " + response.toString());
                        CertificationsData userData = new Gson().fromJson(response.toString(), CertificationsData.class);
                        if (userData.getValue().size() != 0) {
                            try {
                                binding.slider.setVisibility(View.VISIBLE);
                                list = userData.getValue();
                                AppSingle.getInstance().setmSlideData(response.toString());
                                runSlider(list.size());
                            } catch (Exception e) {
                            }
                        } else {
                            binding.slider.setVisibility(View.GONE);
                        }
                    },
                    error -> {
                        binding.slider.setVisibility(View.GONE);
                        Log.d("TAG", "Error: " + error.toString());
                    });
        } catch (Exception e) {
        }
    }

    private void getVideosCallBack() {
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/lists/ce9a6d8e-eca3-4891-8945-9211802e9254/items?$expand=fields($select=Title,VideoURL,EmbedText,VideoId,VideoType)&$top=1",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    /* Successfully called graph, process data and send to UI */
                    Log.d("TAG", "Response: " + response.toString());
                    VideosData userData = new Gson().fromJson(response.toString(), VideosData.class);
                    AppSingle.getInstance().setmVideosId(userData.getValue().get(0).getFields().getVideoId());
                    play(userData.getValue().get(0).getFields().getVideoId());
                    getCertificationsCallBack();
                },
                error -> {
                    Log.d("TAG", "Error: " + error.toString());
                    //displayError(error);
                });
    }

    private void getPhotoCallBack() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://graph.microsoft.com/v1.0/me/photo/$value";
        ImageRequest request = new ImageRequest(url,
                bitmap -> {
                    // mImageView.setImageBitmap(bitmap);
                    binding.ivProfile.setImageBitmap(bitmap);
                    getVideosCallBack();
                }, 0, 0, null,
                error -> binding.ivProfile.setImageDrawable(getContext().getDrawable(R.drawable.jade_default))) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + AppSingle.getInstance().getmAccessToken());
                return headers;
            }
        };
        queue.add(request);
    }

    public void play(String videoId) {
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                try {
                    AppSingle.getInstance().setYouTubePlayerView(youTubePlayerView);
                    youTubePlayer.loadVideo(videoId, 0);
                } catch (Exception e) {
                }
            }
        });
    }


    private void getUnReadEmailCountCallBack() {
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                getContext(),
                "https://graph.microsoft.com/v1.0/me/messages?$filter=isRead eq false&top=10000",
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    Log.d("TAG", "Response: " + response.toString());
                    EmailData docData = new Gson().fromJson(response.toString(), EmailData.class);

                    if (docData.getValue().size() != 0) {
                        binding.unreadEmailCount.setVisibility(View.VISIBLE);
                        binding.unreadEmailCount.setText("" + docData.getValue().size());
                    } else {
                        binding.unreadEmailCount.setVisibility(View.GONE);
                    }

                },
                error -> {
                    binding.unreadEmailCount.setVisibility(View.GONE);
                });
    }

}