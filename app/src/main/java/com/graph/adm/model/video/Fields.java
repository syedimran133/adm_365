
package com.graph.adm.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("VideoURL")
    @Expose
    private VideoURL videoURL;
    @SerializedName("EmbedText")
    @Expose
    private String embedText;
    @SerializedName("VideoId")
    @Expose
    private String videoId;
    @SerializedName("VideoType")
    @Expose
    private String videoType;

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

    public VideoURL getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(VideoURL videoURL) {
        this.videoURL = videoURL;
    }

    public String getEmbedText() {
        return embedText;
    }

    public void setEmbedText(String embedText) {
        this.embedText = embedText;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

}
