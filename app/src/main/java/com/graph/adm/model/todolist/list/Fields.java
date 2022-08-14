
package com.graph.adm.model.todolist.list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("@odata.etag")
    @Expose
    private String odataEtag;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Author")
    @Expose
    private String author;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Progress")
    @Expose
    private String progress;
    @SerializedName("Priority")
    @Expose
    private String priority;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("AssignedTo")
    @Expose
    private String assignedTo;

    @SerializedName("Note")
    @Expose
    private String note;

    public String getOdataEtag() {
        return odataEtag;
    }

    public void setOdataEtag(String odataEtag) {
        this.odataEtag = odataEtag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
