
package com.graph.adm.model.blogs;

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
    @SerializedName("BlogsDescription")
    @Expose
    private String blogsDescription;
    @SerializedName("PublishedBy")
    @Expose
    private String publishedBy;
    @SerializedName("PublishedOn")
    @Expose
    private String publishedOn;
    @SerializedName("id")
    @Expose
    private String id;

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

    public String getBlogsDescription() {
        return blogsDescription;
    }

    public void setBlogsDescription(String blogsDescription) {
        this.blogsDescription = blogsDescription;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
