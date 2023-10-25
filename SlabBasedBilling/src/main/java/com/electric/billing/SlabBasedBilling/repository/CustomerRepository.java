package com.electric.billing.SlabBasedBilling.repository;

import com.electric.billing.SlabBasedBilling.entities.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {
    CustomerDetails findByEmail(String email);
}
