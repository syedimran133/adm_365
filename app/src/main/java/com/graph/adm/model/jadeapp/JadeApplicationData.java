
package com.graph.adm.model.jadeapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JadeApplicationData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<JadeAppValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<JadeAppValue> getValue() {
        return value;
    }

    public void setValue(List<JadeAppValue> value) {
        this.value = value;
    }

}
