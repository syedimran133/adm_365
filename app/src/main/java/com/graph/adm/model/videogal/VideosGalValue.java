
package com.graph.adm.model.videogal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graph.adm.model.video.CreatedBy;
import com.graph.adm.model.video.LastModifiedBy;
import com.graph.adm.model.video.ParentReference;

public class VideosGalValue {

    @SerializedName("createdDateTime")
    @Expose
    private String createdDateTime;
    @SerializedName("eTag")
    @Expose
    private String eTag;
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
    @SerializedName("cTag")
    @Expose
    private String cTag;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("createdBy")
    @Expose
    private com.graph.adm.model.video.CreatedBy createdBy;
    @SerializedName("lastModifiedBy")
    @Expose
    private com.graph.adm.model.video.LastModifiedBy lastModifiedBy;
    @SerializedName("parentReference")
    @Expose
    private com.graph.adm.model.video.ParentReference parentReference;
    @SerializedName("fileSystemInfo")
    @Expose
    private FileSystemInfo fileSystemInfo;
    @SerializedName("folder")
    @Expose
    private Folder folder;

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
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

    public String getcTag() {
        return cTag;
    }

    public void setcTag(String cTag) {
        this.cTag = cTag;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public com.graph.adm.model.video.CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public com.graph.adm.model.video.LastModifiedBy getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(LastModifiedBy lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public com.graph.adm.model.video.ParentReference getParentReference() {
        return parentReference;
    }

    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
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

}
