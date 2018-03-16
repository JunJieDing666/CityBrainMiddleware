package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InterSection {

    /**
     * InterSectionID : 123
     * Start : 2018-2-8 12:00:00
     * End : 2018-2-8 13:00:00
     */

    @JsonProperty(value = "InterSectionID")
    private String InterSectionID;

    @JsonProperty(value = "Start")
    private String Start;

    @JsonProperty(value = "End")
    private String End;

    public String getInterSectionID() {
        return InterSectionID;
    }

    public void setInterSectionID(String InterSectionID) {
        this.InterSectionID = InterSectionID;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String Start) {
        this.Start = Start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String End) {
        this.End = End;
    }
}
