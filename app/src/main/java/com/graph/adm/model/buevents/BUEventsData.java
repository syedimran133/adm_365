
package com.graph.adm.model.buevents;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BUEventsData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<BUEventValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<BUEventValue> getValue() {
        return value;
    }

    public void setValue(List<BUEventValue> value) {
        this.value = value;
    }

}
