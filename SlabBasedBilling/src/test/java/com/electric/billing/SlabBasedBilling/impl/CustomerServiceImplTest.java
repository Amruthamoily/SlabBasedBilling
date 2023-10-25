package com.electric.billing.SlabBasedBilling.impl;

import com.electric.billing.SlabBasedBilling.entities.CustomerDetails;
import com.electric.billing.SlabBasedBilling.entities.MeterReading;
import com.electric.billing.SlabBasedBilling.entities.SlabReading;
import com.electric.billing.SlabBasedBilling.model.requests.LoginRequest;
import com.electric.billing.SlabBasedBilling.model.requests.MeterReadingRequest;
import com.electric.billing.SlabBasedBilling.model.requests.RegisterCustomerRequest;
import com.electric.billing.SlabBasedBilling.model.requests.SlabReadingRequest;
import com.electric.billing.SlabBasedBilling.model.responses.BillDetailsResponse;
import com.electric.billing.SlabBasedBilling.model.responses.GenericResponse;
import com.electric.billing.SlabBasedBilling.model.responses.SlabReadingResponse;
import com.electric.billing.SlabBasedBilling.repository.CustomerRepository;
import com.electric.billing.SlabBasedBilling.repository.MeterRepository;
import com.electric.billing.SlabBasedBilling.repository.SlabRepository;
import com.electric.billing.SlabBasedBilling.services.impl.CustomerServiceImpl;
import com.electric.billing.SlabBasedBilling.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerServiceImplTest {

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    SlabRepository slabRepository;

    @Mock
    MeterRepository meterRepository;

    @InjectMocks
    CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("when customer is not  registered already and request is valid, then the registration is successful")
    void forRegisterCustomer_whenCustomerIsAlreadyNotRegisteredAndRequestIsValid_thenTheRegistrationIsSuccessful() {
        //Initialization
        RegisterCustomerRequest request = new RegisterCustomerRequest();
        request.setId(2);
        request.setFullName("xyz user");
        request.setUserName("xyz");
        request.setEmail("xyz@gmail.com");
        request.setPassword("Xyz@1234");
        request.setAddress("Anugraha Nilaya");
        request.setAccountNumber("12345");
        request.setMobileNumber("9657678554");
        request.setMeterNumber("4534564");
        request.setNotificationPref("email");
        request.setPaymentMethod("UPI");
        request.setSecurityQn("what is your pet name");
        request.setTariff("1A");
        CustomerDetails customerDetails = new CustomerDetails(request.getId(), request.getFullName(), request.getUserName(), request.getEmail()
                , request.getAddress(), request.getMobileNumber(), request.getAccountNumber(), this.passwordEncoder.encode(request.getPassword())
                , request.getSecurityQn(), request.getMeterNumber(), request.getPaymentMethod(), request.getNotificationPref()
                , request.getTariff());
        List<CustomerDetails> customerDetailsList = new ArrayList<>();
        CustomerDetails customerDetails1 = new CustomerDetails();
        customerDetails1.setId(2);
        customerDetails1.setFullName("abc user");
        customerDetails1.setUserName("abc");
        customerDetails1.setPassword("Abc@123");
        customerDetails1.setAddress("Anugraha Nilaya");
        customerDetails1.setAccountNumber("12345");
        customerDetails1.setMobileNumber("9856324755");
        customerDetails1.setMeterNumber("4534564");
        customerDetails1.setNotificationPref("email");
        customerDetails1.setPaymentMethod("UPI");
        customerDetails1.setSecurityQn("what is your pet name");
        customerDetails1.setTariff("1A");
        customerDetails1.setEmail("abcd@gmail.com");
        customerDetailsList.add(customerDetails1);

        // Mocking response
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customerDetails);
        Mockito.when(customerRepository.findAll()).thenReturn(customerDetailsList);

        //calling original method
        GenericResponse response = customerServiceImpl.registerCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Success", response.getStatus());
        Assertions.assertEquals("Registration success", response.getMessage());
    }

    @Test
    @DisplayName("when customer is already registered, then the method returns appropriate error response")
    void forRegisterCustomer_whenCustomerIsAlreadyRegistered_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        RegisterCustomerRequest request = new RegisterCustomerRequest();
        request.setId(2);
        request.setFullName("xyz user");
        request.setUserName("xyz");
        request.setEmail("xyz@gmail.com");
        request.setPassword("Xyz@1234");
        request.setAddress("Anugraha Nilaya");
        request.setAccountNumber("12345");
        request.setMobileNumber("9657678554");
        request.setMeterNumber("4534564");
        request.setNotificationPref("email");
        request.setPaymentMethod("UPI");
        request.setSecurityQn("what is your pet name");
        request.setTariff("1A");
        CustomerDetails customerDetails = new CustomerDetails(request.getId(), request.getFullName(), request.getUserName(), request.getEmail()
                , request.getAddress(), request.getMobileNumber(), request.getAccountNumber(), this.passwordEncoder.encode(request.getPassword())
                , request.getSecurityQn(), request.getMeterNumber(), request.getPaymentMethod(), request.getNotificationPref()
                , request.getTariff());
        List<CustomerDetails> customerDetailsList = new ArrayList<>();
        customerDetailsList.add(customerDetails);

        // Mocking response
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customerDetails);
        Mockito.when(customerRepository.findAll()).thenReturn(customerDetailsList);

        //calling original method
        GenericResponse response = customerServiceImpl.registerCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.RG_ERR2, response.getErrorCode());
        Assertions.assertEquals("Email Id or Mobile number is already registered.", response.getMessage());
    }

    @Test
    @DisplayName("when customer details are not stored to CustomerDetails table, then the method returns appropriate error response")
    void forRegisterCustomer_whenCustomerDetailsAreNotStoredToCustomerDetailsTable_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        RegisterCustomerRequest request = new RegisterCustomerRequest();
        request.setId(2);
        request.setFullName("xyz user");
        request.setUserName("xyz");
        request.setEmail("xyz@gmail.com");
        request.setPassword("Xyz@1234");
        request.setAddress("Anugraha Nilaya");
        request.setAccountNumber("12345");
        request.setMobileNumber("9657678554");
        request.setMeterNumber("4534564");
        request.setNotificationPref("email");
        request.setPaymentMethod("UPI");
        request.setSecurityQn("what is your pet name");
        request.setTariff("1A");
        CustomerDetails customerDetails = new CustomerDetails();
        List<CustomerDetails> customerDetailsList = new ArrayList<>();
        CustomerDetails customerDetails1 = new CustomerDetails();
        customerDetails1.setId(2);
        customerDetails1.setFullName("abc user");
        customerDetails1.setUserName("abc");
        customerDetails1.setPassword("Abcd@123");
        customerDetails1.setAddress("Anugraha Nilaya");
        customerDetails1.setAccountNumber("12345");
        customerDetails1.setMobileNumber("9856324755");
        customerDetails1.setMeterNumber("4534564");
        customerDetails1.setNotificationPref("email");
        customerDetails1.setPaymentMethod("UPI");
        customerDetails1.setSecurityQn("what is your pet name");
        customerDetails1.setTariff("1A");
        customerDetails1.setEmail("abcd@gmail.com");
        customerDetailsList.add(customerDetails1);

        // Mocking response
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customerDetails);
        Mockito.when(customerRepository.findAll()).thenReturn(customerDetailsList);

        //calling original method
        GenericResponse response = customerServiceImpl.registerCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.RG_ERR1, response.getErrorCode());
        Assertions.assertEquals("Could not register customer. Please try after some time.", response.getMessage());
    }

    @Test
    @DisplayName("when password or email patter is invalid, then the method returns appropriate error response")
    void forRegisterCustomer_whenPasswordOrEmailPatternIsInValid_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        RegisterCustomerRequest request = new RegisterCustomerRequest();
        request.setId(2);
        request.setFullName("xyz user");
        request.setUserName("xyz");
        request.setEmail("xyzgmail.com");
        request.setPassword("xyz@1234");
        request.setAddress("Anugraha Nilaya");
        request.setAccountNumber("12345");
        request.setMobileNumber("9657678554");
        request.setMeterNumber("4534564");
        request.setNotificationPref("email");
        request.setPaymentMethod("UPI");
        request.setSecurityQn("what is your pet name");
        request.setTariff("1A");

        //calling original method
        GenericResponse response = customerServiceImpl.registerCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.RG_ERR3, response.getErrorCode());
        Assertions.assertEquals("Not a valid Email or Password pattern", response.getMessage());
    }

    @Test
    @DisplayName("when registerCustomer method throws Exception, then the method returns appropriate error response")
    void forRegisterCustomer_whenRegisterCustomerMethodThrowsException_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        RegisterCustomerRequest request = new RegisterCustomerRequest();
        request.setId(2);
        request.setFullName("xyz user");
        request.setUserName("xyz");
        request.setEmail("xyz@gmail.com");
        request.setPassword("Xyz@1234");
        request.setAddress("Anugraha Nilaya");
        request.setAccountNumber("12345");
        request.setMobileNumber("9657678554");
        request.setMeterNumber("4534564");
        request.setNotificationPref("email");
        request.setPaymentMethod("UPI");
        request.setSecurityQn("what is your pet name");
        request.setTariff("1A");
        List<CustomerDetails> customerDetailsList = new ArrayList<>();
        CustomerDetails customerDetails1 = new CustomerDetails();
        customerDetailsList.add(customerDetails1);

        // Mocking response
        Mockito.when(customerRepository.findAll()).thenReturn(customerDetailsList);

        //calling original method
        GenericResponse response = customerServiceImpl.registerCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.EXP_REG, response.getErrorCode());
        Assertions.assertEquals("Exception occurred in registerCustomer", response.getMessage());
    }

    @Test
    @DisplayName("when Customer is registered and password entered match, then the method returns success response")
    void forLoginCustomer_whenCustomerIsRegisteredAndPasswordEnteredMatch_thenTheMethodReturnsSuccessResponse() {
        //Initialization
        LoginRequest request = new LoginRequest();
        request.setEmail("abc@gmail.com");
        request.setPassword("Abc@1234");
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setEmail("abc@gmail.com");
        customerDetails.setPassword("$2a$10$cdc2yObRu1aUgk2U083jEeuG2Z4Yum8iMklD37rRGAZeMVkTyi8nq");
        customerDetails.setAccountNumber("723887");
        customerDetails.setId(1);
        customerDetails.setMeterNumber("12340");
        customerDetails.setTariff("1A");
        System.out.println("Customer Details " + customerDetails);

        // Mocking response
        Mockito.when(customerRepository.findByEmail(Mockito.anyString())).thenReturn(customerDetails);
        Mockito.when(passwordEncoder.matches(Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        //calling original method
        GenericResponse response = customerServiceImpl.loginCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Success", response.getStatus());
        Assertions.assertEquals("Login Success", response.getMessage());
    }

    @Test
    @DisplayName("when Customer is not registered, then the method returns appropriate error response")
    void forLoginCustomer_whenCustomerIsNotRegistered_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        LoginRequest request = new LoginRequest();
        request.setEmail("abc@gmail.com");
        request.setPassword("Abc@1234");
        // Mocking response
        Mockito.when(customerRepository.findByEmail(Mockito.anyString())).thenReturn(null);

        //calling original method
        GenericResponse response = customerServiceImpl.loginCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.LG_ERR2, response.getErrorCode());
        Assertions.assertEquals("User not registered", response.getMessage());
    }

    @Test
    @DisplayName("when Customer is registered and password entered does not match, then the method returns appropriate error response")
    void forLoginCustomer_whenCustomerIsRegisteredAndPasswordEnteredDoesNotMatch_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        LoginRequest request = new LoginRequest();
        request.setEmail("abc@gmail.com");
        request.setPassword("Abc@1234");
        CustomerDetails customerDetails = new CustomerDetails();
        request.setEmail("abc@gmail.com");
        request.setPassword("Abcd@1234");

        // Mocking response
        Mockito.when(customerRepository.findByEmail(Mockito.anyString())).thenReturn(customerDetails);

        //calling original method
        GenericResponse response = customerServiceImpl.loginCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.LG_ERR1, response.getErrorCode());
        Assertions.assertEquals("Invalid password", response.getMessage());
    }

    @Test
    @DisplayName("when password or email patter is invalid, then the method returns appropriate error response")
    void forLoginCustomer_whenPasswordOrEmailPatternIsInValid_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        LoginRequest request = new LoginRequest();
        request.setEmail("abcgmail.com");
        request.setPassword("Abc@1234");

        //calling original method
        GenericResponse response = customerServiceImpl.loginCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.LG_ERR3, response.getErrorCode());
        Assertions.assertEquals("Not a valid Email or Password pattern", response.getMessage());
    }


    @Test
    @DisplayName("when customerLogin method throws Exception, then the method returns appropriate error response")
    void forLoginCustomer_whenCustomerLoginMethodThrowsException_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        LoginRequest request = new LoginRequest();
        request.setEmail("abc@gmail.com");
        request.setPassword("Abc@1234");

        // Mocking response
        Mockito.when(customerRepository.findByEmail(Mockito.anyString())).thenThrow(new RuntimeException());

        //calling original method
        GenericResponse response = customerServiceImpl.loginCustomer(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.EXP_LOG, response.getErrorCode());
        Assertions.assertEquals("Exception occurred in getPriceSlab", response.getMessage());
    }

    @Test
    @DisplayName("when price slab details are not duplicate, then the price slab details are stored in data base")
    void forSetPriceSlab_whenPriceSlabDetailsAreNotDuplicate_thenThePriceSlabDetailsAreStoredInDataBase() {
        //Initialization
        SlabReadingRequest request = new SlabReadingRequest();
        request.setSlabId(1);
        request.setSlabName("1A");
        request.setSlabRating(8.23);
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setMinLimit(50);
        request.setMaxLimit(0);
        SlabReading slabReading1 = new SlabReading(1,"1A",8.23, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 50,0);

        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "1A", 7.00, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 0, 50);
        slabReadingList.add(slabReading);

        Mockito.when(slabRepository.findAll()).thenReturn(slabReadingList);
        Mockito.when(slabRepository.save(Mockito.any())).thenReturn(slabReading1);

        GenericResponse genericResponse = customerServiceImpl.setPriceSlab(request);

        Assertions.assertEquals("Success", genericResponse.getStatus());
        Assertions.assertEquals("Price slab saved successfully", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when price slab details are duplicate, then the method returns appropriate error response")
    void forSetPriceSlab_whenPriceSlabDetailsAreDuplicate_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        SlabReadingRequest request = new SlabReadingRequest();
        request.setSlabId(1);
        request.setSlabName("1A");
        request.setSlabRating(8.23);
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setMinLimit(50);
        request.setMaxLimit(0);

        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "1A", 8.23, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 50, 0);
        slabReadingList.add(slabReading);

        // Mocking response
        Mockito.when(slabRepository.findAll()).thenReturn(slabReadingList);
        Mockito.when(slabRepository.save(Mockito.any())).thenReturn(slabReading);

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setPriceSlab(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals(Constants.SET_SLB_ERR2, genericResponse.getErrorCode());
        Assertions.assertEquals("Duplicate slab. Slab already defined", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when price slab details are not stored in to the data base, then the method returns appropriate error response")
    void forSetPriceSlab_whenPriceSlabDetailsAreNotStoredInToTheDataBase_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        SlabReadingRequest request = new SlabReadingRequest();
        request.setSlabId(1);
        request.setSlabName("1A");
        request.setSlabRating(8.23);
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setMinLimit(50);
        request.setMaxLimit(0);
        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "1A", 7.00, new Date(2023, Calendar.FEBRUARY, 1), new Date(2023, Calendar.FEBRUARY, 28), 0, 50);
        slabReadingList.add(slabReading);

        // Mocking response
        Mockito.when(slabRepository.findAll()).thenReturn(slabReadingList);
        Mockito.when(slabRepository.save(Mockito.any())).thenReturn(new SlabReading());

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setPriceSlab(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals(Constants.SET_SLB_ERR1, genericResponse.getErrorCode());
        Assertions.assertEquals("Could not save price slab details. Please try after some time.", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when setPriceSlab method throws Exception, then the method returns appropriate error response")
    void forSetPriceSlab_whenSetPriceSlabMethodThrowsException_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        SlabReadingRequest request = new SlabReadingRequest();
        request.setSlabId(1);
        request.setSlabName("1A");
        request.setSlabRating(8.23);
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setMinLimit(50);
        request.setMaxLimit(0);

        // Mocking response
        Mockito.when(slabRepository.findAll()).thenReturn(null);

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setPriceSlab(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals(Constants.EXP_SET_SLB, genericResponse.getErrorCode());
        Assertions.assertEquals("Exception occurred in setPriceSlab", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when price slab table contains data, then the method should return price slab")
    void forGetPriceSlab_whenPriceSlabTableContainsData_thenTheMethodShouldReturnPriceSlab() {
        //Initialization
        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading1 = new SlabReading(1, "1A", 8.23, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 50, 0);
        SlabReading slabReading2 = new SlabReading(2, "2A", 7.00, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 50, 0);
        slabReadingList.add(slabReading1);
        slabReadingList.add(slabReading2);

        // Mocking response
        Mockito.when(slabRepository.findAll()).thenReturn(slabReadingList);

        //calling original method
        SlabReadingResponse response = customerServiceImpl.getPriceSlab();

        // Test validations / Assertions
        Assertions.assertEquals("Success", response.getStatus());
        Assertions.assertEquals("price slab loaded successfully", response.getMessage());
        Assertions.assertEquals(slabReading1.getSlabName(), response.getSlabReadingTos().get(0).getSlabName());
        Assertions.assertEquals(slabReading2.getSlabName(), response.getSlabReadingTos().get(1).getSlabName());
    }

    @Test
    @DisplayName("when price slab is empty, then the method returns appropriate error response")
    void forGetPriceSlab_whenPriceSlabIsEmpty_thenTheMethodReturnsAppropriateErrorResponse() {
        // Mocking response
        Mockito.when(slabRepository.findAll()).thenReturn(new ArrayList<>());

        //calling original method
        SlabReadingResponse response = customerServiceImpl.getPriceSlab();

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.GET_SLB_ERR1, response.getErrorCode());
        Assertions.assertEquals("Enable to load price slab", response.getMessage());
    }

    @Test
    @DisplayName("when getPriceSlab method throws Exception, then the method returns appropriate error response")
    void forGetPriceSlab_whenGetPriceSlabMethodThrowsException_thenTheMethodReturnsAppropriateErrorResponse() {
        // Mocking response
        Mockito.when(slabRepository.findAll()).thenReturn(null);

        //calling original method
        SlabReadingResponse response = customerServiceImpl.getPriceSlab();

        // Test validations / Assertions
        Assertions.assertEquals(Constants.EXP_GET_SLB, response.getErrorCode());
        Assertions.assertEquals("Exception occurred in getPriceSlab", response.getMessage());
        Assertions.assertEquals("Error", response.getStatus());
    }

    @Test
    @DisplayName("when bill details corresponding to the bill number is present in the MeterReading table, then the method should return bill detail")
    void forGenerateBill_whenBillDetailsCorrespondingToTheBillNumberIsPresentInTheMeterReadingTable_thenTheMethodShouldReturnBillDetail() {
        //Initialization
        MeterReading meterReading = new MeterReading("12345", "1134455", new Date(2023, Calendar.APRIL, 1), new Date(2023, Calendar.APRIL, 30), 6698, 6690, "328834", "1A", 89.00, "0897763","3567363");
        List<MeterReading> meterReadings = new ArrayList<>();
        meterReadings.add(meterReading);

        // Mocking response
        Mockito.when(meterRepository.findByBillNo(Mockito.anyString())).thenReturn(meterReading);
        Mockito.when(meterRepository.findAll()).thenReturn(meterReadings);

        //calling original method
        BillDetailsResponse response = customerServiceImpl.generateBill("12345");

        // Test validations / Assertions
        Assertions.assertEquals("12345", response.getBillNo());
        Assertions.assertEquals("Success", response.getStatus());
        Assertions.assertEquals("Bill generated successfully", response.getMessage());
    }

    @Test
    @DisplayName("when bill details corresponding to the bill number is not present in the MeterReading table, then the method returns appropriate error response")
    void forGenerateBill_whenBillDetailsCorrespondingToTheBillNumberIsNotPresentInTheMeterReadingTable_thenTheMethodReturnsAppropriateErrorResponse() {
        // Mocking response
        Mockito.when(meterRepository.findByBillNo(Mockito.anyString())).thenReturn(new MeterReading());

        //calling original method
        BillDetailsResponse response = customerServiceImpl.generateBill("12345");

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals("Could not find the bill details", response.getMessage());
    }

    @Test
    @DisplayName("when generateBill method throws Exception, then the method returns appropriate error response")
    void forGenerateBill_whenGenerateBillMethodThrowsException_thenTheMethodReturnsAppropriateErrorResponse() {
        // Mocking response
        Mockito.when(meterRepository.findByBillNo(Mockito.anyString())).thenThrow(new NullPointerException());

        //calling original method
        BillDetailsResponse response = customerServiceImpl.generateBill("12345");

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.EXP_GB, response.getErrorCode());
        Assertions.assertEquals("Exception occurred in generateBill", response.getMessage());
    }

    @Test
    @DisplayName("when slab details are empty, then the method returns appropriate error response")
    void forSetPriceSlab_whenSlabReadingDetailsAreEmpty_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1335);
        request.setPreviousReading(1325);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");

        List<MeterReading> meterReadingList = new ArrayList<>();
        MeterReading meterReading = new MeterReading("123456", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");
        meterReadingList.add(meterReading);

        // Mocking response
        Mockito.when( meterRepository.findAll()).thenReturn(meterReadingList);
        Mockito.when( slabRepository.findOneBySlabNameAndStartDateAndEndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals("Slab not set", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when current reading is smaller than previous reading, then the method returns appropriate error response")
    void forSetPriceSlab_whenCurrentReadingIsLessThanPreviousReading_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1325);
        request.setPreviousReading(1335);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");

        List<MeterReading> meterReadingList = new ArrayList<>();
        MeterReading meterReading = new MeterReading("123456", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");
        meterReadingList.add(meterReading);

        // Mocking response
        Mockito.when( meterRepository.findAll()).thenReturn(meterReadingList);
        Mockito.when( slabRepository.findOneBySlabNameAndStartDateAndEndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(new ArrayList<>());

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals("current reading is less than previous reading", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when slab details corresponding to the meter tariff is not present, then the method returns appropriate error response")
    void forSetPriceSlab_whenSlabDetailsCorrespondingToTheMeterTariffIsNotPresent_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1335);
        request.setPreviousReading(1325);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");

        List<MeterReading> meterReadingList = new ArrayList<>();
        MeterReading meterReading = new MeterReading("123456", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");
        meterReadingList.add(meterReading);

        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "2A", 7.00, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 50, 0);
        slabReadingList.add(slabReading);

        // Mocking response
        Mockito.when(meterRepository.findAll()).thenReturn(meterReadingList);
        Mockito.when(slabRepository.findOneBySlabNameAndStartDateAndEndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(slabReadingList);

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals("slab reading does not match", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when meter reading is not inserted in to the meter reading table, then the method returns appropriate error response")
    void forSetPriceSlab_whenMeterReadingIsNotInsertedInToTheMeterReadingTable_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1335);
        request.setPreviousReading(1325);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");

        List<MeterReading> meterReadingList = new ArrayList<>();
        MeterReading meterReading = new MeterReading("123456", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");
        meterReadingList.add(meterReading);

        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "1A", 7.00, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 0, 50);
        slabReadingList.add(slabReading);

        // Mocking response
        Mockito.when(meterRepository.findAll()).thenReturn(meterReadingList);
        Mockito.when(slabRepository.findOneBySlabNameAndStartDateAndEndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(slabReadingList);
        Mockito.when(meterRepository.save(Mockito.any())).thenReturn(new MeterReading());

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals("Could not save details. Please try after some time.", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when slab details corresponding to the meter with consumption less than 50 are present , then the method returns bill reading with success response")
    void forSetPriceSlab_whenSlabDetailsCorrespondingToTheMeterWithConsumptionLessThanFiftyArePresent_thenTheMethodReturnsBillReadingWithSuccessResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1335);
        request.setPreviousReading(1325);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");
        MeterReading meterReading = new MeterReading("12345", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");

        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "1A", 7.00, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 0, 50);
        slabReadingList.add(slabReading);

        // Mocking response
        Mockito.when(meterRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(slabRepository.findOneBySlabNameAndStartDateAndEndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(slabReadingList);
        Mockito.when(meterRepository.save(Mockito.any())).thenReturn(meterReading);

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Success", genericResponse.getStatus());
        Assertions.assertEquals("Meter readings saved successfully", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when slab details with corresponding to the meter with consumption greater than 50 reading are present, then the method returns bill reading with success response")
    void forSetPriceSlab_whenSlabDetailsWithCorrespondingToTheMeterWithConsumptionGreaterThanFiftyArePresent_thenTheMethodReturnsBillReadingWithSuccessResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1335);
        request.setPreviousReading(1225);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");
        MeterReading meterReading = new MeterReading("12345", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");

        List<SlabReading> slabReadingList = new ArrayList<>();
        SlabReading slabReading = new SlabReading(1, "1A", 7.00, new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 50, 0);
        slabReadingList.add(slabReading);

        // Mocking response
        Mockito.when(meterRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(slabRepository.findOneBySlabNameAndStartDateAndEndDate(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(slabReadingList);
        Mockito.when(meterRepository.save(Mockito.any())).thenReturn(meterReading);

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Success", genericResponse.getStatus());
        Assertions.assertEquals("Meter readings saved successfully", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when meter reading details are duplicate, then the method returns appropriate error response")
    void forSetPriceSlab_whenMeterReadingDetailsAreDuplicate_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        MeterReadingRequest request = new MeterReadingRequest();
        request.setBillNo("12345");
        request.setBillPeriod("30 days");
        request.setStartDate(new Date(2023, Calendar.MARCH, 1));
        request.setEndDate(new Date(2023, Calendar.MARCH, 30));
        request.setCurrentReading(1335);
        request.setPreviousReading(1325);
        request.setRrNumber("124485");
        request.setTariff("1A");
        request.setAccountNumber("686415");
        request.setMeterNumber("87500");

        List<MeterReading> meterReadingList = new ArrayList<>();
        MeterReading meterReading = new MeterReading("12345", "30 days", new Date(2023, Calendar.MARCH, 1), new Date(2023, Calendar.MARCH, 30), 1335, 1325, "124485", "1A", 8.23, "686415", "87500");
        meterReadingList.add(meterReading);

        // Mocking response
        Mockito.when( meterRepository.findAll()).thenReturn(meterReadingList);

        //calling original method
        GenericResponse genericResponse = customerServiceImpl.setMeterReading(request);

        // Test validations / Assertions
        Assertions.assertEquals("Error", genericResponse.getStatus());
        Assertions.assertEquals("Bill number already exist.", genericResponse.getMessage());
    }

    @Test
    @DisplayName("when setMeterReading method throws Exception, then the method returns appropriate error response")
    void forGenerateBill_whenSetMeterReadingMethodThrowsException_thenTheMethodReturnsAppropriateErrorResponse() {
        //Initialization
        MeterReadingRequest meterReadingRequest = new MeterReadingRequest();
        meterReadingRequest.setBillNo("12345");
        meterReadingRequest.setMeterNumber("562568");
        meterReadingRequest.setCurrentReading(1335);
        meterReadingRequest.setPreviousReading(1325);
        meterReadingRequest.setStartDate(new Date(2023, Calendar.MAY, 1));
        meterReadingRequest.setEndDate(new Date(2023, Calendar.MAY, 30));
        meterReadingRequest.setTariff("1A");
        meterReadingRequest.setAccountNumber("55886");
        meterReadingRequest.setBillPeriod("30 days");
        meterReadingRequest.setRrNumber("82132");

        // Mocking response
        Mockito.when(meterRepository.findAll()).thenReturn(null);

        //calling original method
        GenericResponse response = customerServiceImpl.setMeterReading(meterReadingRequest);

        // Test validations / Assertions
        Assertions.assertEquals("Error", response.getStatus());
        Assertions.assertEquals(Constants.EXP_SET_MTR, response.getErrorCode());
        Assertions.assertEquals("Exception occurred in setMeterReading", response.getMessage());
    }
}
