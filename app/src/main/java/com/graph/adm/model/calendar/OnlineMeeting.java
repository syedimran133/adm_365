
package com.graph.adm.model.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnlineMeeting {

    @SerializedName("joinUrl")
    @Expose
    private String joinUrl;

    public String getJoinUrl() {
        return joinUrl;
    }

    public void setJoinUrl(String joinUrl) {
        this.joinUrl = joinUrl;
    }

}
