
package com.graph.adm.model.todolist.getid;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToDoListGetIdData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<ToDoGetIdValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<ToDoGetIdValue> getValue() {
        return value;
    }

    public void setValue(List<ToDoGetIdValue> value) {
        this.value = value;
    }

}
