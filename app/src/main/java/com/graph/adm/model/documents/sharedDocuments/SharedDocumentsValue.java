
package com.graph.adm.model.documents.sharedDocuments;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Value {

    @SerializedName("@odata.type")
    @Expose
    private String odataType;
    @SerializedName("createdDateTime")
    @Expose
    private String createdDateTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lastModifiedDateTime")
    @Expose
    private String lastModifiedDateTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("createdBy")
    @Expose
    private CreatedBy createdBy;
    @SerializedName("lastModifiedBy")
    @Expose
    private LastModifiedBy lastModifiedBy;
    @SerializedName("fileSystemInfo")
    @Expose
    private FileSystemInfo fileSystemInfo;
    @SerializedName("folder")
    @Expose
    private Folder folder;
    @SerializedName("remoteItem")
    @Expose
    private RemoteItem remoteItem;
    @SerializedName("file")
    @Expose
    private File__1 file;

    public String getOdataType() {
        return odataType;
    }

    public void setOdataType(String odataType) {
        this.odataType = odataType;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public LastModifiedBy getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(LastModifiedBy lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public FileSystemInfo getFileSystemInfo() {
        return fileSystemInfo;
    }

    public void setFileSystemInfo(FileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = fileSystemInfo;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public RemoteItem getRemoteItem() {
        return remoteItem;
    }

    public void setRemoteItem(RemoteItem remoteItem) {
        this.remoteItem = remoteItem;
    }

    public File__1 getFile() {
        return file;
    }

    public void setFile(File__1 file) {
        this.file = file;
    }

}
