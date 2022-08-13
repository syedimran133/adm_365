
package com.graph.adm.model.documents.HrPolocies;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class FileSystemInfo {

    @SerializedName("createdDateTime")
    @Expose
    private String createdDateTime;
    @SerializedName("lastModifiedDateTime")
    @Expose
    private String lastModifiedDateTime;

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

}
