
package com.graph.adm.model.email;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender {

    @SerializedName("emailAddress")
    @Expose
    private EmailAddress emailAddress;

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

}
