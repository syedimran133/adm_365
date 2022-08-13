package com.graph.adm.Utils;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import com.graph.adm.R;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IPublicClientApplication;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.PublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


/**
 * Created by root on 12-12-2016.
 */

public class AppSingle extends Application {

    private static AppSingle _app;
    private Activity mainActivity;
    private boolean isAllowBack = false;
    private String userName;
    private int notification_count;
    private String userEmail;
    private String mAccessToken;
    private String mVideosId;
    private String mSlideData;
    private YouTubePlayerView youTubePlayerView;

    public static ISingleAccountPublicClientApplication mSingleAccountApp;

    public static AppSingle getInstance() {
        return _app;
    }

    public static ISingleAccountPublicClientApplication getmSingleAccountApp() {
        return mSingleAccountApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _app = this;
        PublicClientApplication.createSingleAccountPublicClientApplication(getApplicationContext(),
                R.raw.auth_config_single_account,
                new IPublicClientApplication.ISingleAccountApplicationCreatedListener() {
                    @Override
                    public void onCreated(ISingleAccountPublicClientApplication application) {
                        mSingleAccountApp = application;
                    }

                    @Override
                    public void onError(MsalException exception) {
                        //displayError(exception);
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public Activity getActivity() {
        return mainActivity;
    }

    public void initActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getmVideosId() {
        return mVideosId;
    }

    public void setmVideosId(String mVideosId) {
        this.mVideosId = mVideosId;
    }

    public YouTubePlayerView getYouTubePlayerView() {
        return youTubePlayerView;
    }

    public void setYouTubePlayerView(YouTubePlayerView youTubePlayerView) {
        this.youTubePlayerView = youTubePlayerView;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getmSlideData() {
        return mSlideData;
    }

    public void setmSlideData(String mSlideData) {
        this.mSlideData = mSlideData;
    }

    public int getNotification_count() {
        return notification_count;
    }

    public void setNotification_count(int notification_count) {
        this.notification_count = notification_count;
    }
}
