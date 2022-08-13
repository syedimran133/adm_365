
package com.graph.adm.model.video;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class VideosData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("@odata.nextLink")
    @Expose
    private String odataNextLink;
    @SerializedName("value")
    @Expose
    private List<Value> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public String getOdataNextLink() {
        return odataNextLink;
    }

    public void setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

}
