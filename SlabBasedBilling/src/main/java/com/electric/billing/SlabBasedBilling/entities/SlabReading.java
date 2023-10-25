package com.electric.billing.SlabBasedBilling.entities;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "slab_reading")
public class SlabReading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "slab_id")
    private Integer slabId;

    @Column(name = "slab_name")
    private String slabName;

    @Column(name = "slab_rate")
    private double slabRate;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "limit_value", length = 100)
    private double minLimit;

    @Column(name = "maxLimit", length = 100)
    private double maxLimit;


    public SlabReading(int slabId, String slabName, double slabRate, Date startDate, Date endDate, double minLimit, double maxLimit) {
        this.slabId = slabId;
        this.slabName = slabName;
        this.slabRate = slabRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }

    public SlabReading() {

    }
}
