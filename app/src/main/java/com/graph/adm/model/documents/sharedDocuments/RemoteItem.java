
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteItem {

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
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("webDavUrl")
    @Expose
    private String webDavUrl;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("createdBy")
    @Expose
    private CreatedBy__1 createdBy;
    @SerializedName("fileSystemInfo")
    @Expose
    private FileSystemInfo__1 fileSystemInfo;
    @SerializedName("folder")
    @Expose
    private Folder__1 folder;
    @SerializedName("lastModifiedBy")
    @Expose
    private LastModifiedBy__1 lastModifiedBy;
    @SerializedName("parentReference")
    @Expose
    private ParentReference parentReference;
    @SerializedName("shared")
    @Expose
    private Shared shared;
    @SerializedName("sharepointIds")
    @Expose
    private SharepointIds sharepointIds;
    @SerializedName("file")
    @Expose
    private File file;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getWebDavUrl() {
        return webDavUrl;
    }

    public void setWebDavUrl(String webDavUrl) {
        this.webDavUrl = webDavUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public CreatedBy__1 getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy__1 createdBy) {
        this.createdBy = createdBy;
    }

    public FileSystemInfo__1 getFileSystemInfo() {
        return fileSystemInfo;
    }

    public void setFileSystemInfo(FileSystemInfo__1 fileSystemInfo) {
        this.fileSystemInfo = fileSystemInfo;
    }

    public Folder__1 getFolder() {
        return folder;
    }

    public void setFolder(Folder__1 folder) {
        this.folder = folder;
    }

    public LastModifiedBy__1 getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(LastModifiedBy__1 lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ParentReference getParentReference() {
        return parentReference;
    }

    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
    }

    public Shared getShared() {
        return shared;
    }

    public void setShared(Shared shared) {
        this.shared = shared;
    }

    public SharepointIds getSharepointIds() {
        return sharepointIds;
    }

    public void setSharepointIds(SharepointIds sharepointIds) {
        this.sharepointIds = sharepointIds;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
