
package com.graph.adm.model.email;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class From {

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