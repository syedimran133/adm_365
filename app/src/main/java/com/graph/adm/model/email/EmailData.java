
package com.graph.adm.model.email;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailData {

    @SerializedName("value")
    @Expose
    private List<EmailValue> value = null;

    public List<EmailValue> getValue() {
        return value;
    }

    public void setValue(List<EmailValue> value) {
        this.value = value;
    }

}
