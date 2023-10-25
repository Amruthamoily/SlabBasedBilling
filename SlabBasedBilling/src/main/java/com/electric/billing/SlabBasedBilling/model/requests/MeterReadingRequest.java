package com.electric.billing.SlabBasedBilling.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
public class MeterReadingRequest {
    @JsonProperty("billNo")
    private String billNo;
    @JsonProperty("billPeriod")
    private String billPeriod;
    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("materNumber")
    private String meterNumber;
    @JsonProperty("startData")
    private Date startDate;
    @JsonProperty("endData")
    private Date endDate;
    @JsonProperty("currentReading")
    private double currentReading;
    @JsonProperty("previousReading")
    private double previousReading;
    @JsonProperty("rrNumber")
    private String rrNumber;
    @JsonProperty("tariff")
    private String tariff;
}
