
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharedBy {

    @SerializedName("user")
    @Expose
    private User__4 user;

    public User__4 getUser() {
        return user;
    }

    public void setUser(User__4 user) {
        this.user = user;
    }

}
