
package com.graph.adm.model.support;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SupportServiceData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<SupportServiceValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<SupportServiceValue> getValue() {
        return value;
    }

    public void setValue(List<SupportServiceValue> value) {
        this.value = value;
    }

}
