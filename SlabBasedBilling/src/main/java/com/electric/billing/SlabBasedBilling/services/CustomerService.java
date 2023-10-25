package com.electric.billing.SlabBasedBilling.services;

import com.electric.billing.SlabBasedBilling.model.requests.LoginRequest;
import com.electric.billing.SlabBasedBilling.model.requests.MeterReadingRequest;
import com.electric.billing.SlabBasedBilling.model.requests.RegisterCustomerRequest;
import com.electric.billing.SlabBasedBilling.model.requests.SlabReadingRequest;
import com.electric.billing.SlabBasedBilling.model.responses.BillDetailsResponse;
import com.electric.billing.SlabBasedBilling.model.responses.GenericResponse;
import com.electric.billing.SlabBasedBilling.model.responses.SlabReadingResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    GenericResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest);

    GenericResponse loginCustomer(LoginRequest loginRequest);

    SlabReadingResponse getPriceSlab();

    GenericResponse setPriceSlab(SlabReadingRequest slabReadingRequest);

    GenericResponse setMeterReading(MeterReadingRequest meterReadingRequest);

    BillDetailsResponse generateBill(String billNumber);
}
