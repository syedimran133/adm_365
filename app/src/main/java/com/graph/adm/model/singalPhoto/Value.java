
package com.graph.adm.model.singalPhoto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("@microsoft.graph.downloadUrl")
    @Expose
    private String microsoftGraphDownloadUrl;
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
    private CreatedBy createdBy;
    @SerializedName("lastModifiedBy")
    @Expose
    private LastModifiedBy lastModifiedBy;
    @SerializedName("parentReference")
    @Expose
    private ParentReference parentReference;
    @SerializedName("file")
    @Expose
    private File file;
    @SerializedName("fileSystemInfo")
    @Expose
    private FileSystemInfo fileSystemInfo;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("thumbnails@odata.context")
    @Expose
    private String thumbnailsOdataContext;
    @SerializedName("thumbnails")
    @Expose
    private List<Thumbnail> thumbnails = null;

    public String getMicrosoftGraphDownloadUrl() {
        return microsoftGraphDownloadUrl;
    }

    public void setMicrosoftGraphDownloadUrl(String microsoftGraphDownloadUrl) {
        this.microsoftGraphDownloadUrl = microsoftGraphDownloadUrl;
    }

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

    public ParentReference getParentReference() {
        return parentReference;
    }

    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileSystemInfo getFileSystemInfo() {
        return fileSystemInfo;
    }

    public void setFileSystemInfo(FileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = fileSystemInfo;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getThumbnailsOdataContext() {
        return thumbnailsOdataContext;
    }

    public void setThumbnailsOdataContext(String thumbnailsOdataContext) {
        this.thumbnailsOdataContext = thumbnailsOdataContext;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

}
