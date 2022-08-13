
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Folder__1 {

    @SerializedName("childCount")
    @Expose
    private Integer childCount;

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

}
