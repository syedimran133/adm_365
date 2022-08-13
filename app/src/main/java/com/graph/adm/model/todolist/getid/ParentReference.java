
package com.graph.adm.model.todolist.getid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentReference {

    @SerializedName("siteId")
    @Expose
    private String siteId;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

}
