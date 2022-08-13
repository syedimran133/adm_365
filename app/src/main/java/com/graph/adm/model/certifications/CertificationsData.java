
package com.graph.adm.model.certifications;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CertificationsData {

    @SerializedName("value")
    @Expose
    private List<CertificationsDataValue> value = null;

    public List<CertificationsDataValue> getValue() {
        return value;
    }

    public void setValue(List<CertificationsDataValue> value) {
        this.value = value;
    }

}
