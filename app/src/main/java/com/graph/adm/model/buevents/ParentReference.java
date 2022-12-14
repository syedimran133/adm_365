
package com.graph.adm.model.buevents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParentReference {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("siteId")
    @Expose
    private String siteId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

}
