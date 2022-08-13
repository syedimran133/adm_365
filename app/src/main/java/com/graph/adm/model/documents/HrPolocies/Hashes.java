
package com.graph.adm.model.documents.HrPolocies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hashes {

    @SerializedName("quickXorHash")
    @Expose
    private String quickXorHash;

    public String getQuickXorHash() {
        return quickXorHash;
    }

    public void setQuickXorHash(String quickXorHash) {
        this.quickXorHash = quickXorHash;
    }

}
