
package com.graph.adm.model.support;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
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
