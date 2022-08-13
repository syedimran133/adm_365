
package com.graph.adm.model.documents.sharedDocuments;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharedWithMeData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<SharedDocumentsValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<SharedDocumentsValue> getValue() {
        return value;
    }

    public void setValue(List<SharedDocumentsValue> value) {
        this.value = value;
    }

}
