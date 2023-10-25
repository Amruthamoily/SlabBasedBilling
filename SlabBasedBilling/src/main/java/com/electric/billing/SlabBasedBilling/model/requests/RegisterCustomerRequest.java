package com.electric.billing.SlabBasedBilling.model.requests;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterCustomerRequest {

    private Integer id;
    private String fullName;
    private String userName;
    private String email;
    private String address;
    private String mobileNumber;
    private String accountNumber;
    private String password;
    private String securityQn;
    private String meterNumber;
    private String paymentMethod;
    private String notificationPref;
    private String Tariff;

}
