
package com.graph.adm.model.videogal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graph.adm.model.video.User;

public class CreatedBy {

    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
