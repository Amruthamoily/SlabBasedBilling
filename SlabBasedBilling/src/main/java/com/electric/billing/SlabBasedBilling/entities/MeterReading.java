package com.electric.billing.SlabBasedBilling.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "meter_reading")
public class MeterReading {
    @Id
    @Column(name = "bill_no")
    private String billNo;

    @Column(name = "bill_period")
    private String billPeriod;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_data")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_data")
    private Date endDate;

    @Column(name = "current_reading", length = 100)
    private double currentReading;

    @Column(name = "previous_reading", length = 100)
    private double previousReading;

    @Column(name = "rr_number")
    private String rrNumber;

    @Column(name = "tariff")
    private String tariff;

    @Column(name = "bill_charge", length = 100)
    private double billCharge;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "mater_number")
    private String meterNumber;

    public MeterReading(String billNo, String billPeriod, Date startDate, Date endDate, double currentReading, double previousReading, String rrNumber, String tariff, double billCharge, String accountNumber, String meterNumber) {
        this.billNo = billNo;
        this.billPeriod = billPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currentReading = currentReading;
        this.previousReading = previousReading;
        this.rrNumber = rrNumber;
        this.tariff = tariff;
        this.billCharge = billCharge;
        this.accountNumber = accountNumber;
        this.meterNumber = meterNumber;
    }

    public MeterReading() {

    }
}
