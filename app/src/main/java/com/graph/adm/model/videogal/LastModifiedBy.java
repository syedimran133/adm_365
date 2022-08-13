
package com.graph.adm.model.videogal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graph.adm.model.video.User__1;

public class LastModifiedBy {

    @SerializedName("user")
    @Expose
    private User__1 user;

    public User__1 getUser() {
        return user;
    }

    public void setUser(User__1 user) {
        this.user = user;
    }

}
