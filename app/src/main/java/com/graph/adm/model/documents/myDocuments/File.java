
package com.graph.adm.model.documents.myDocuments;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class File {

    @SerializedName("mimeType")
    @Expose
    private String mimeType;
    @SerializedName("hashes")
    @Expose
    private Hashes hashes;

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Hashes getHashes() {
        return hashes;
    }

    public void setHashes(Hashes hashes) {
        this.hashes = hashes;
    }

}
