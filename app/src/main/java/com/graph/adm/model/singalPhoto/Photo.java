
package com.graph.adm.model.singalPhoto;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Photo {

    @SerializedName("takenDateTime")
    @Expose
    private String takenDateTime;

    public String getTakenDateTime() {
        return takenDateTime;
    }

    public void setTakenDateTime(String takenDateTime) {
        this.takenDateTime = takenDateTime;
    }

}
