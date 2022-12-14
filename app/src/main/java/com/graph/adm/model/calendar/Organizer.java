
package com.graph.adm.model.calendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organizer {

    @SerializedName("emailAddress")
    @Expose
    private EmailAddress__1 emailAddress;

    public EmailAddress__1 getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress__1 emailAddress) {
        this.emailAddress = emailAddress;
    }

}
