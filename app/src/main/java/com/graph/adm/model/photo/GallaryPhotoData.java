
package com.graph.adm.model.photo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class GallaryPhotoData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<Value> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

}
