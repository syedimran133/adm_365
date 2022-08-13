
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastModifiedBy__1 {

    @SerializedName("user")
    @Expose
    private User__3 user;

    public User__3 getUser() {
        return user;
    }

    public void setUser(User__3 user) {
        this.user = user;
    }

}
