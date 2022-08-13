
package com.azuresamples.msalandroidapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("businessPhones")
    @Expose
    private List<String> businessPhones = null;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("givenName")
    @Expose
    private String givenName;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    @SerializedName("officeLocation")
    @Expose
    private String officeLocation;
    @SerializedName("preferredLanguage")
    @Expose
    private Object preferredLanguage;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("userPrincipalName")
    @Expose
    private String userPrincipalName;
    @SerializedName("id")
    @Expose
    private String id;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<String> getBusinessPhones() {
        return businessPhones;
    }

    public void setBusinessPhones(List<String> businessPhones) {
        this.businessPhones = businessPhones;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public Object getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Object preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
