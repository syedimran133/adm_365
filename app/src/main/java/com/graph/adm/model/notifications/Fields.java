
package com.graph.adm.model.notifications;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Fields {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ExpireDate")
    @Expose
    private String expireDate;
    @SerializedName("NotificationTo")
    @Expose
    private String notificationTo;
    @SerializedName("IsRead")
    @Expose
    private String isRead;
    @SerializedName("NotificationAuthor")
    @Expose
    private String notificationAuthor;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(String notificationTo) {
        this.notificationTo = notificationTo;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getNotificationAuthor() {
        return notificationAuthor;
    }

    public void setNotificationAuthor(String notificationAuthor) {
        this.notificationAuthor = notificationAuthor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
