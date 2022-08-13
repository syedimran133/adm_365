
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedBy__1 {

    @SerializedName("user")
    @Expose
    private User__2 user;

    public User__2 getUser() {
        return user;
    }

    public void setUser(User__2 user) {
        this.user = user;
    }

}
