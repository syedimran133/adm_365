
package com.graph.adm.model.announcement.image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("path")
    @Expose
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
