
package com.graph.adm.model.documents.sharedDocuments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharepointIds {

    @SerializedName("listId")
    @Expose
    private String listId;
    @SerializedName("listItemId")
    @Expose
    private String listItemId;
    @SerializedName("listItemUniqueId")
    @Expose
    private String listItemUniqueId;
    @SerializedName("siteId")
    @Expose
    private String siteId;
    @SerializedName("siteUrl")
    @Expose
    private String siteUrl;
    @SerializedName("tenantId")
    @Expose
    private String tenantId;
    @SerializedName("webId")
    @Expose
    private String webId;

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListItemId() {
        return listItemId;
    }

    public void setListItemId(String listItemId) {
        this.listItemId = listItemId;
    }

    public String getListItemUniqueId() {
        return listItemUniqueId;
    }

    public void setListItemUniqueId(String listItemUniqueId) {
        this.listItemUniqueId = listItemUniqueId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

}
