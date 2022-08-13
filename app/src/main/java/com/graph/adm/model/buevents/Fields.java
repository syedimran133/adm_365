
package com.graph.adm.model.buevents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("EventDate")
    @Expose
    private String eventDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("RegistrationLink")
    @Expose
    private RegistrationLink registrationLink;
    @SerializedName("EventDescription")
    @Expose
    private String eventDescription;

    public String getOdataEtag() {
        return odataEtag;
    }

    public void setOdataEtag(String odataEtag) {
        this.odataEtag = odataEtag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RegistrationLink getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(RegistrationLink registrationLink) {
        this.registrationLink = registrationLink;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

}
