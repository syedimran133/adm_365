
package com.graph.adm.model.documents.HrPolocies;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class HrPoliciesData {

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
