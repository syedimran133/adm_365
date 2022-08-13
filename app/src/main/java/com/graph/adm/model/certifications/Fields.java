
package com.graph.adm.model.certifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("AssociateName")
    @Expose
    private String associateName;
    @SerializedName("WishType")
    @Expose
    private String wishType;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("GreetingText")
    @Expose
    private String greetingText;

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

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

    public String getWishType() {
        return wishType;
    }

    public void setWishType(String wishType) {
        this.wishType = wishType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

}
