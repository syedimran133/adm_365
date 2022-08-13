package com.graph.adm.Utils;

import android.app.Activity;
import android.app.Application;


/**
 * Created by root on 12-12-2016.
 */

public class AppSingle extends Application {

    private static AppSingle _app;
    private Activity mainActivity;
    private boolean isAllowBack = true;
    private boolean isSuccessLogin;
    private String appVersion;

    public static AppSingle getInstance() {
        return _app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _app = this;
    }

    public Activity getActivity() {
        return mainActivity;
    }

    public void initActivity(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean isAllowBack() {
        return isAllowBack;
    }

    public void setAllowBack(boolean allowBack) {
        isAllowBack = allowBack;
    }

    public boolean isSuccessLogin() {
        return isSuccessLogin;
    }

    public void setSuccessLogin(boolean successLogin) {
        isSuccessLogin = successLogin;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}
