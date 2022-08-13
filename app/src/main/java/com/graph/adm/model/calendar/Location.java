
package com.graph.adm.model.calendar;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Location {

    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("locationType")
    @Expose
    private String locationType;
    @SerializedName("uniqueIdType")
    @Expose
    private String uniqueIdType;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getUniqueIdType() {
        return uniqueIdType;
    }

    public void setUniqueIdType(String uniqueIdType) {
        this.uniqueIdType = uniqueIdType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
