
package com.graph.adm.model.documents.myDocuments;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyDocumentsData {

    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("value")
    @Expose
    private List<MyDocumentValue> value = null;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<MyDocumentValue> getValue() {
        return value;
    }

    public void setValue(List<MyDocumentValue> value) {
        this.value = value;
    }

}
