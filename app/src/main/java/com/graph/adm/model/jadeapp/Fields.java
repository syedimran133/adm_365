
package com.graph.adm.model.jadeapp;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Fields {

    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Icon")
    @Expose
    private String icon;
    @SerializedName("IsDisabled")
    @Expose
    private Boolean isDisabled;
    @SerializedName("LinkUrl")
    @Expose
    private LinkUrl linkUrl;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public LinkUrl getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(LinkUrl linkUrl) {
        this.linkUrl = linkUrl;
    }

}
