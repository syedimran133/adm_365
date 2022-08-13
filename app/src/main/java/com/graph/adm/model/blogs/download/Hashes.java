
package com.graph.adm.model.blogs.download;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
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
