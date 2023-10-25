package com.electric.billing.SlabBasedBilling.model.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class BillDetailsResponse extends GenericResponse {
    private String billNo;
    private String billPeriod;
    private Date startDate;
    private Date endDate;
    private double currentReading;
    private double previousReading;
    private String rrNumber;
    private String tariff;
    private double billCharge;

    public BillDetailsResponse(String billNo, String billPeriod, Date startDate, Date endDate, double currentReading, double previousReading, String rrNumber, String tariff, double billCharge) {
        this.billNo = billNo;
        this.billPeriod = billPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentReading = currentReading;
        this.previousReading = previousReading;
        this.rrNumber = rrNumber;
        this.tariff = tariff;
        this.billCharge = billCharge;
    }

    public BillDetailsResponse() {
    }
}
