
package com.graph.adm.model.blogs.download;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
