package com.electric.billing.SlabBasedBilling.repository;

import com.electric.billing.SlabBasedBilling.entities.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<MeterReading, String> {
    MeterReading findByBillNo(String billNumber);
}
