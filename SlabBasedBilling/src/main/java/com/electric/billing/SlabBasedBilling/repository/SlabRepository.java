package com.electric.billing.SlabBasedBilling.repository;

import com.electric.billing.SlabBasedBilling.entities.SlabReading;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface SlabRepository extends JpaRepository<SlabReading, Integer> {
    List<SlabReading> findOneBySlabNameAndStartDateAndEndDate(String slabName , Date startDate , Date EndDate);
}
