package com.electric.billing.SlabBasedBilling.model.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SlabReadingRequest {
    private int slabId;
    private String slabName;
    private double slabRating;
    private Date startDate;
    private Date endDate;
    private double minLimit;
    private double maxLimit;
}
