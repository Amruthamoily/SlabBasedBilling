package com.electric.billing.SlabBasedBilling.utils;

public class Constants {
    public static final String EXP_REG = "EX001";
    public static final String EXP_LOG = "EX002";
    public static final String EXP_SET_SLB = "EX003";
    public static final String EXP_GET_SLB = "EX004";
    public static final String EXP_SET_MTR = "EX004";
    public static final String EXP_GB = "EX004";

    //Registration error code
    public static final String RG_ERR1 = "RG001";
    public static final String RG_ERR2 = "RG002";
    public static final String RG_ERR3 = "RG003";

    //Login error code
    public static final String LG_ERR1 = "LG001";
    public static final String LG_ERR2 = "LG002";
    public static final String LG_ERR3 = "LG003";

    //set Slab reading error code
    public static final String SET_SLB_ERR1 = "SPS001";
    public static final String SET_SLB_ERR2 = "SPS002";

    //get slab reading error code
    public static final String  GET_SLB_ERR1 = "GPS001";

    //set meter reading error code
    public static final String  SET_MTR_ERR1 = "SMR001";
    public static final String  SET_MTR_ERR2 = "SMR002";
    public static final String  SET_MTR_ERR3 = "SMR003";
    public static final String  SET_MTR_ERR4 = "SMR004";
    public static final String  SET_MTR_ERR5 = "SMR005";

    //generate bill error code
    public static final String GB_ERR = "GB001";

    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
}
