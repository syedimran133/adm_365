
package com.graph.adm.model.documents.sharedDocuments;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ParentReference {

    @SerializedName("driveType")
    @Expose
    private String driveType;
    @SerializedName("driveId")
    @Expose
    private String driveId;
    @SerializedName("id")
    @Expose
    private String id;

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
