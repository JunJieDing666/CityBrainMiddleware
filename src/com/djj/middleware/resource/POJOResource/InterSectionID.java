package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InterSectionID {
    @JsonProperty(value = "InterSectionID")
    private String interSectionID;

    public InterSectionID() {

    }

    public InterSectionID(String interSectionID) {
        this.interSectionID = interSectionID;
    }

    public String getInterSectionID() {
        return interSectionID;
    }

    public void setInterSectionID(String interSectionID) {
        this.interSectionID = interSectionID;
    }

}
