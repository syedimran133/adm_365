
package com.graph.adm.model.announcement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageData {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("nativeFile")
    @Expose
    private NativeFile nativeFile;
    @SerializedName("fieldName")
    @Expose
    private String fieldName;
    @SerializedName("serverUrl")
    @Expose
    private String serverUrl;
    @SerializedName("fieldId")
    @Expose
    private String fieldId;
    @SerializedName("serverRelativeUrl")
    @Expose
    private String serverRelativeUrl;
    @SerializedName("id")
    @Expose
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public NativeFile getNativeFile() {
        return nativeFile;
    }

    public void setNativeFile(NativeFile nativeFile) {
        this.nativeFile = nativeFile;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getServerRelativeUrl() {
        return serverRelativeUrl;
    }

    public void setServerRelativeUrl(String serverRelativeUrl) {
        this.serverRelativeUrl = serverRelativeUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
