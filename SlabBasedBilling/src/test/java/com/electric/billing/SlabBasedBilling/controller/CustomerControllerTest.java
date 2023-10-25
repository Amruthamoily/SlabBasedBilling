package com.electric.billing.SlabBasedBilling.controller;

import com.electric.billing.SlabBasedBilling.controllers.CustomerController;
import com.electric.billing.SlabBasedBilling.model.requests.LoginRequest;
import com.electric.billing.SlabBasedBilling.model.requests.MeterReadingRequest;
import com.electric.billing.SlabBasedBilling.model.requests.RegisterCustomerRequest;
import com.electric.billing.SlabBasedBilling.model.requests.SlabReadingRequest;
import com.electric.billing.SlabBasedBilling.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CustomerControllerTest {

    @Spy
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController = new CustomerController();

    @BeforeEach
    public void init() {
        customerService = Mockito.spy(CustomerService.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("when registerUsers api is called, then registerCustomer method of customer service is called")
    void forRegisterUsers_whenRegisterUsersApiIsCalled_thenRegisterCustomerMethodOfCustomerServiceIsCalled() {
        // Initialization
        RegisterCustomerRequest request = new RegisterCustomerRequest();
        request.setAddress("Anugraha nilaya");
        request.setEmail("xyz@gmail.com");
        request.setAccountNumber("1234567");
        request.setId(1);

        // Original method call
        customerController.registerUsers(request);

        // Test validations / Assertions
        Mockito.verify(customerService, Mockito.times(1)).registerCustomer(request);
    }

    @Test
    @DisplayName("when login api is called, then loginCustomer method of customer service is called")
    void forLogin_whenLoginApiIsCalled_thenLoginCustomerMethodOfCustomerServiceIsCalled() {
        // Initialization
        LoginRequest request = new LoginRequest();
        request.setEmail("xyz@gmail.com");
        request.setPassword("Amrutha@123");

        // Original method call
        customerController.login(request);

        // Test validations / Assertions
        Mockito.verify(customerService, Mockito.times(1)).loginCustomer(request);
    }

    @Test
    @DisplayName("when getPriceSlab api is called, then getPriceSlab method of customer service is called")
    void forGetPriceSlab_whenGetPriceSlabApiIsCalled_thenGetPriceSlabMethodOfCustomerServiceIsCalled() {
        // Original method call
        customerController.getPriceSlab();

        // Test validations / Assertions
        Mockito.verify(customerService, Mockito.times(1)).getPriceSlab();
    }

    @Test
    @DisplayName("when setPriceSlab is called, then setPriceSlab method of customer service is called")
    void forSetPriceSlab_whenSetPriceSlabApiIsCalled_thenSetPriceSlabMethodOfCustomerServiceIsCalled() {
        // Initialization
        SlabReadingRequest request = new SlabReadingRequest();
        request.setSlabId(1);
        request.setSlabName("1A");
        request.setSlabRating(8.67);

        // Original method call
        customerController.setPriceSlab(request);

        // Test validations / Assertions
        Mockito.verify(customerService, Mockito.times(1)).setPriceSlab(request);
    }

    @Test
    @DisplayName("when setMeterReading is called, then setMeterReading method of customer service is called")
    void forSetMeterReading_whenSetMeterReadingApiIsCalled_thenSetMeterReadingMethodOfCustomerServiceIsCalled() {
        // Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("488223");
        request.setAccountNumber("1234567");
        request.setMeterNumber("43768");

        // Original method call
        customerController.setMeterReading(request);

        // Test validations / Assertions
        Mockito.verify(customerService, Mockito.times(1)).setMeterReading(request);
    }

    @Test
    @DisplayName("when generateBill is called, then generateBill method of customer service is called")
    void forGenerateBill_whenGenerateBillApiIsCalled_thenGenerateBillMethodOfCustomerServiceIsCalled() {
        // Original method call
        customerController.generateBill("488223");

        // Test validations / Assertions
        Mockito.verify(customerService, Mockito.times(1)).generateBill("488223");
    }
}
