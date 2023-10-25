package com.electric.billing.SlabBasedBilling.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "customer_details")
public class CustomerDetails {

    @Id
    @Column(name = "id",  length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "security_qn")
    private String securityQn;
    @Column(name = "meter_number")
    private String meterNumber;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "notification_pref")
    private String notificationPref;
    @Column(name = "tariff")
    private String tariff;

    public CustomerDetails(int id, String fullName, String userName, String email, String address, String mobileNumber, String accountNumber, String password, String securityQn, String meterNumber, String paymentMethod, String notificationPref, String tariff) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.accountNumber = accountNumber;
        this.password = password;
        this.securityQn = securityQn;
        this.meterNumber = meterNumber;
        this.paymentMethod = paymentMethod;
        this.notificationPref = notificationPref;
        this.tariff = tariff;
    }

    public CustomerDetails() {

    }
}
