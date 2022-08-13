
package com.graph.adm.model.email;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Value {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createdDateTime")
    @Expose
    private String createdDateTime;
    @SerializedName("lastModifiedDateTime")
    @Expose
    private String lastModifiedDateTime;
    @SerializedName("changeKey")
    @Expose
    private String changeKey;
    @SerializedName("categories")
    @Expose
    private List<Object> categories = null;
    @SerializedName("receivedDateTime")
    @Expose
    private String receivedDateTime;
    @SerializedName("sentDateTime")
    @Expose
    private String sentDateTime;
    @SerializedName("hasAttachments")
    @Expose
    private Boolean hasAttachments;
    @SerializedName("internetMessageId")
    @Expose
    private String internetMessageId;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("bodyPreview")
    @Expose
    private String bodyPreview;
    @SerializedName("importance")
    @Expose
    private String importance;
    @SerializedName("parentFolderId")
    @Expose
    private String parentFolderId;
    @SerializedName("conversationId")
    @Expose
    private String conversationId;
    @SerializedName("conversationIndex")
    @Expose
    private String conversationIndex;
    @SerializedName("isDeliveryReceiptRequested")
    @Expose
    private Boolean isDeliveryReceiptRequested;
    @SerializedName("isReadReceiptRequested")
    @Expose
    private Boolean isReadReceiptRequested;
    @SerializedName("isRead")
    @Expose
    private Boolean isRead;
    @SerializedName("isDraft")
    @Expose
    private Boolean isDraft;
    @SerializedName("webLink")
    @Expose
    private String webLink;
    @SerializedName("inferenceClassification")
    @Expose
    private String inferenceClassification;
    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("from")
    @Expose
    private From from;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getChangeKey() {
        return changeKey;
    }

    public void setChangeKey(String changeKey) {
        this.changeKey = changeKey;
    }

    public List<Object> getCategories() {
        return categories;
    }

    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public String getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(String sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public Boolean getHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(Boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public String getInternetMessageId() {
        return internetMessageId;
    }

    public void setInternetMessageId(String internetMessageId) {
        this.internetMessageId = internetMessageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBodyPreview() {
        return bodyPreview;
    }

    public void setBodyPreview(String bodyPreview) {
        this.bodyPreview = bodyPreview;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getParentFolderId() {
        return parentFolderId;
    }

    public void setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationIndex() {
        return conversationIndex;
    }

    public void setConversationIndex(String conversationIndex) {
        this.conversationIndex = conversationIndex;
    }

    public Boolean getIsDeliveryReceiptRequested() {
        return isDeliveryReceiptRequested;
    }

    public void setIsDeliveryReceiptRequested(Boolean isDeliveryReceiptRequested) {
        this.isDeliveryReceiptRequested = isDeliveryReceiptRequested;
    }

    public Boolean getIsReadReceiptRequested() {
        return isReadReceiptRequested;
    }

    public void setIsReadReceiptRequested(Boolean isReadReceiptRequested) {
        this.isReadReceiptRequested = isReadReceiptRequested;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getInferenceClassification() {
        return inferenceClassification;
    }

    public void setInferenceClassification(String inferenceClassification) {
        this.inferenceClassification = inferenceClassification;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

}
