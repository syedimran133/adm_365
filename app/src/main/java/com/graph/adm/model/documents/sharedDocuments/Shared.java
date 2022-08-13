
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shared {

    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("sharedDateTime")
    @Expose
    private String sharedDateTime;
    @SerializedName("sharedBy")
    @Expose
    private SharedBy sharedBy;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSharedDateTime() {
        return sharedDateTime;
    }

    public void setSharedDateTime(String sharedDateTime) {
        this.sharedDateTime = sharedDateTime;
    }

    public SharedBy getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(SharedBy sharedBy) {
        this.sharedBy = sharedBy;
    }

}
