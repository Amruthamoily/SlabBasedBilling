package com.electric.billing.SlabBasedBilling.model.responses.to;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Date;

@Getter
@Setter
@ToString
public class SlabReadingTo {
    @JsonProperty("slabId")
    private int slabId;
    @JsonProperty("slabName")
    private String slabName;
    @JsonProperty("slabRating")
    private double slabRating;
    @JsonProperty("minLimit")
    private double minLimit;
    @JsonProperty("maxLimit")
    private double maxLimit;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
}
