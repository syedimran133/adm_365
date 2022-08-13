
package com.graph.adm.model.calendar;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CalendarData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<MyCalValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<MyCalValue> getValue() {
        return value;
    }

    public void setValue(List<MyCalValue> value) {
        this.value = value;
    }

}
