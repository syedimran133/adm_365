package com.graph.adm.model.documents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graph.adm.model.documents.myDocuments.Hashes;

public class DocNavData {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Path")
    @Expose
    private String path;

    public DocNavData(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
