
package com.graph.adm.model.calendar;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCalValue {

    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
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
    @SerializedName("transactionId")
    @Expose
    private Object transactionId;
    @SerializedName("originalStartTimeZone")
    @Expose
    private String originalStartTimeZone;
    @SerializedName("originalEndTimeZone")
    @Expose
    private String originalEndTimeZone;
    @SerializedName("iCalUId")
    @Expose
    private String iCalUId;
    @SerializedName("reminderMinutesBeforeStart")
    @Expose
    private Integer reminderMinutesBeforeStart;
    @SerializedName("isReminderOn")
    @Expose
    private Boolean isReminderOn;
    @SerializedName("hasAttachments")
    @Expose
    private Boolean hasAttachments;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("bodyPreview")
    @Expose
    private String bodyPreview;
    @SerializedName("importance")
    @Expose
    private String importance;
    @SerializedName("sensitivity")
    @Expose
    private String sensitivity;
    @SerializedName("isAllDay")
    @Expose
    private Boolean isAllDay;
    @SerializedName("isCancelled")
    @Expose
    private Boolean isCancelled;
    @SerializedName("isOrganizer")
    @Expose
    private Boolean isOrganizer;
    @SerializedName("responseRequested")
    @Expose
    private Boolean responseRequested;
    @SerializedName("seriesMasterId")
    @Expose
    private Object seriesMasterId;
    @SerializedName("showAs")
    @Expose
    private String showAs;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("webLink")
    @Expose
    private String webLink;
    @SerializedName("onlineMeetingUrl")
    @Expose
    private Object onlineMeetingUrl;
    @SerializedName("isOnlineMeeting")
    @Expose
    private Boolean isOnlineMeeting;
    @SerializedName("onlineMeetingProvider")
    @Expose
    private String onlineMeetingProvider;
    @SerializedName("allowNewTimeProposals")
    @Expose
    private Boolean allowNewTimeProposals;
    @SerializedName("isDraft")
    @Expose
    private Boolean isDraft;
    @SerializedName("hideAttendees")
    @Expose
    private Boolean hideAttendees;
    @SerializedName("recurrence")
    @Expose
    private Object recurrence;
    @SerializedName("onlineMeeting")
    @Expose
    private OnlineMeeting onlineMeeting;
    @SerializedName("responseStatus")
    @Expose
    private ResponseStatus responseStatus;
    @SerializedName("body")
    @Expose
    private Body body;
    @SerializedName("start")
    @Expose
    private Start start;
    @SerializedName("end")
    @Expose
    private End end;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("locations")
    @Expose
    private List<Object> locations = null;
    @SerializedName("attendees")
    @Expose
    private List<Attendee> attendees = null;
    @SerializedName("organizer")
    @Expose
    private Organizer organizer;

    public String getOdataEtag() {
        return odataEtag;
    }

    public void setOdataEtag(String odataEtag) {
        this.odataEtag = odataEtag;
    }

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

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Object transactionId) {
        this.transactionId = transactionId;
    }

    public String getOriginalStartTimeZone() {
        return originalStartTimeZone;
    }

    public void setOriginalStartTimeZone(String originalStartTimeZone) {
        this.originalStartTimeZone = originalStartTimeZone;
    }

    public String getOriginalEndTimeZone() {
        return originalEndTimeZone;
    }

    public void setOriginalEndTimeZone(String originalEndTimeZone) {
        this.originalEndTimeZone = originalEndTimeZone;
    }

    public String getiCalUId() {
        return iCalUId;
    }

    public void setiCalUId(String iCalUId) {
        this.iCalUId = iCalUId;
    }

    public Integer getReminderMinutesBeforeStart() {
        return reminderMinutesBeforeStart;
    }

    public void setReminderMinutesBeforeStart(Integer reminderMinutesBeforeStart) {
        this.reminderMinutesBeforeStart = reminderMinutesBeforeStart;
    }

    public Boolean getIsReminderOn() {
        return isReminderOn;
    }

    public void setIsReminderOn(Boolean isReminderOn) {
        this.isReminderOn = isReminderOn;
    }

    public Boolean getHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(Boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
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

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public Boolean getIsAllDay() {
        return isAllDay;
    }

    public void setIsAllDay(Boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public Boolean getIsOrganizer() {
        return isOrganizer;
    }

    public void setIsOrganizer(Boolean isOrganizer) {
        this.isOrganizer = isOrganizer;
    }

    public Boolean getResponseRequested() {
        return responseRequested;
    }

    public void setResponseRequested(Boolean responseRequested) {
        this.responseRequested = responseRequested;
    }

    public Object getSeriesMasterId() {
        return seriesMasterId;
    }

    public void setSeriesMasterId(Object seriesMasterId) {
        this.seriesMasterId = seriesMasterId;
    }

    public String getShowAs() {
        return showAs;
    }

    public void setShowAs(String showAs) {
        this.showAs = showAs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public Object getOnlineMeetingUrl() {
        return onlineMeetingUrl;
    }

    public void setOnlineMeetingUrl(Object onlineMeetingUrl) {
        this.onlineMeetingUrl = onlineMeetingUrl;
    }

    public Boolean getIsOnlineMeeting() {
        return isOnlineMeeting;
    }

    public void setIsOnlineMeeting(Boolean isOnlineMeeting) {
        this.isOnlineMeeting = isOnlineMeeting;
    }

    public String getOnlineMeetingProvider() {
        return onlineMeetingProvider;
    }

    public void setOnlineMeetingProvider(String onlineMeetingProvider) {
        this.onlineMeetingProvider = onlineMeetingProvider;
    }

    public Boolean getAllowNewTimeProposals() {
        return allowNewTimeProposals;
    }

    public void setAllowNewTimeProposals(Boolean allowNewTimeProposals) {
        this.allowNewTimeProposals = allowNewTimeProposals;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean isDraft) {
        this.isDraft = isDraft;
    }

    public Boolean getHideAttendees() {
        return hideAttendees;
    }

    public void setHideAttendees(Boolean hideAttendees) {
        this.hideAttendees = hideAttendees;
    }

    public Object getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Object recurrence) {
        this.recurrence = recurrence;
    }

    public OnlineMeeting getOnlineMeeting() {
        return onlineMeeting;
    }

    public void setOnlineMeeting(OnlineMeeting onlineMeeting) {
        this.onlineMeeting = onlineMeeting;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public End getEnd() {
        return end;
    }

    public void setEnd(End end) {
        this.end = end;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Object> getLocations() {
        return locations;
    }

    public void setLocations(List<Object> locations) {
        this.locations = locations;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

}
