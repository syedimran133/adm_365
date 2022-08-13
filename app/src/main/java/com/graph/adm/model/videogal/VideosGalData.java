
package com.graph.adm.model.videogal;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideosGalData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<VideosGalValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<VideosGalValue> getValue() {
        return value;
    }

    public void setValue(List<VideosGalValue> value) {
        this.value = value;
    }

}
