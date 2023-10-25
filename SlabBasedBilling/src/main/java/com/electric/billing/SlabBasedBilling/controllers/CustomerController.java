package com.electric.billing.SlabBasedBilling.controllers;


import com.electric.billing.SlabBasedBilling.model.requests.LoginRequest;
import com.electric.billing.SlabBasedBilling.model.requests.MeterReadingRequest;
import com.electric.billing.SlabBasedBilling.model.requests.RegisterCustomerRequest;

import com.electric.billing.SlabBasedBilling.model.requests.SlabReadingRequest;
import com.electric.billing.SlabBasedBilling.model.responses.BillDetailsResponse;
import com.electric.billing.SlabBasedBilling.model.responses.GenericResponse;
import com.electric.billing.SlabBasedBilling.model.responses.SlabReadingResponse;
import com.electric.billing.SlabBasedBilling.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/register")
    public GenericResponse registerUsers(@RequestBody RegisterCustomerRequest registerCustomerRequest) {
        return customerService.registerCustomer(registerCustomerRequest);
    }

    @PostMapping(value = "/login")
    public GenericResponse login(@RequestBody LoginRequest loginRequest) {
        return customerService.loginCustomer(loginRequest);
    }

    @GetMapping(value = "/getPriceSlab")
    public SlabReadingResponse getPriceSlab() {
        return customerService.getPriceSlab();
    }

    @PostMapping(value = "/setPriceSlab")
    public GenericResponse setPriceSlab(@RequestBody SlabReadingRequest slabReadingRequest) {
        return customerService.setPriceSlab(slabReadingRequest);
    }

    @PostMapping(value = "/setMeterReading")
    public GenericResponse setMeterReading(@RequestBody MeterReadingRequest meterReadingRequest) {
        return customerService.setMeterReading(meterReadingRequest);
    }

    @PostMapping(value = "/generateBill")
    public BillDetailsResponse generateBill(@RequestBody String billNumber) {
        return customerService.generateBill(billNumber);
    }
}
