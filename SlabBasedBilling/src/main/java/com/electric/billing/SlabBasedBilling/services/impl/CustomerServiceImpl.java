package com.electric.billing.SlabBasedBilling.services.impl;

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
import com.electric.billing.SlabBasedBilling.model.responses.to.SlabReadingTo;
import com.electric.billing.SlabBasedBilling.repository.CustomerRepository;
import com.electric.billing.SlabBasedBilling.repository.MeterRepository;
import com.electric.billing.SlabBasedBilling.repository.SlabRepository;
import com.electric.billing.SlabBasedBilling.services.CustomerService;
import com.electric.billing.SlabBasedBilling.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SlabRepository slabRepository;

    @Autowired
    MeterRepository meterRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public GenericResponse registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        GenericResponse response = new GenericResponse();
        boolean duplicate = false;
        Pattern passPattern = Pattern.compile(Constants.PASSWORD_REGEX);
        Matcher passMatcher = passPattern.matcher(registerCustomerRequest.getPassword());
        Pattern emailPattern = Pattern.compile(Constants.EMAIL_REGEX);
        Matcher emailMater = emailPattern.matcher(registerCustomerRequest.getEmail());
        try {
            if(passMatcher.matches() && emailMater.matches() && registerCustomerRequest.getPassword() != null && registerCustomerRequest.getEmail() != null) {
                List<CustomerDetails> details = customerRepository.findAll();
                if (!details.isEmpty()) {
                    duplicate = details.stream().anyMatch(obj ->
                            (obj.getEmail().equals(registerCustomerRequest.getEmail()) || obj.getMobileNumber().equals(registerCustomerRequest.getMobileNumber())));
                }
                if (!duplicate) {
                    CustomerDetails customerDetails = new CustomerDetails(registerCustomerRequest.getId(), registerCustomerRequest.getFullName(), registerCustomerRequest.getUserName(), registerCustomerRequest.getEmail()
                            , registerCustomerRequest.getAddress(), registerCustomerRequest.getMobileNumber(), registerCustomerRequest.getAccountNumber(), this.passwordEncoder.encode(registerCustomerRequest.getPassword())
                            , registerCustomerRequest.getSecurityQn(), registerCustomerRequest.getMeterNumber(), registerCustomerRequest.getPaymentMethod(), registerCustomerRequest.getNotificationPref()
                            , registerCustomerRequest.getTariff());
                    CustomerDetails save = customerRepository.save(customerDetails);
                    if (save.getId() != null) {
                        response.setStatus("Success");
                        response.setErrorCode("");
                        response.setMessage("Registration success");
                    } else {
                        response.setStatus("Error");
                        response.setErrorCode(Constants.RG_ERR1);
                        response.setMessage("Could not register customer. Please try after some time.");
                    }
                } else {
                    response.setStatus("Error");
                    response.setErrorCode(Constants.RG_ERR2);
                    response.setMessage("Email Id or Mobile number is already registered.");
                }
            } else {
                response.setStatus("Error");
                response.setErrorCode(Constants.RG_ERR3);
                response.setMessage("Not a valid Email or Password pattern");
            }
        }  catch (Exception e) {
            response.setStatus("Error");
            response.setErrorCode(Constants.EXP_REG);
            response.setMessage("Exception occurred in registerCustomer");
        }

        return response;
    }

    @Override
    public GenericResponse loginCustomer(LoginRequest loginRequest) {
        GenericResponse response = new GenericResponse();
        Pattern passPattern = Pattern.compile(Constants.PASSWORD_REGEX);
        Matcher passMatcher = passPattern.matcher(loginRequest.getPassword());
        Pattern emailPattern = Pattern.compile(Constants.EMAIL_REGEX);
        Matcher emailMatcher = emailPattern.matcher(loginRequest.getEmail());
        try {
            if(passMatcher.matches() && emailMatcher.matches() && loginRequest.getPassword() != null && loginRequest.getEmail() != null) {
            CustomerDetails customerDetails = customerRepository.findByEmail(loginRequest.getEmail());
            if (customerDetails != null) {
                String password = loginRequest.getPassword();
                System.out.println("input password " + this.passwordEncoder.encode(password));
                String storedPassword = customerDetails.getPassword();
                System.out.println("expected password " + storedPassword);
                boolean isPasswordMatch = passwordEncoder.matches(password, storedPassword);
                if (isPasswordMatch) {
                    response.setStatus("Success");
                    response.setErrorCode("");
                    response.setMessage("Login Success");
                } else {
                    response.setStatus("Error");
                    response.setErrorCode(Constants.LG_ERR1);
                    response.setMessage("Invalid password");
                }
            } else {
                response.setStatus("Error");
                response.setErrorCode(Constants.LG_ERR2);
                response.setMessage("User not registered");
            } } else {
                response.setStatus("Error");
                response.setErrorCode(Constants.LG_ERR3);
                response.setMessage("Not a valid Email or Password pattern");
            }
        }  catch (Exception e) {
            response.setStatus("Error");
            response.setErrorCode(Constants.EXP_LOG);
            response.setMessage("Exception occurred in getPriceSlab");
        }
        return response;
    }

    @Override
    public GenericResponse setPriceSlab(SlabReadingRequest request) {
        GenericResponse response = new GenericResponse();
        boolean duplicate = false;
        try {
            List<SlabReading> slabReadings = slabRepository.findAll();
            if (!slabReadings.isEmpty()) {
                duplicate = slabReadings.stream().anyMatch(slabReading ->
                        (slabReading.getSlabName().equals(request.getSlabName()) && slabReading.getEndDate().equals(request.getEndDate())
                                && slabReading.getStartDate().equals(request.getStartDate())) && slabReading.getMaxLimit() == request.getMaxLimit()
                                && slabReading.getMinLimit() == request.getMinLimit());
            }
            if (!duplicate) {
                SlabReading slabReading = new SlabReading(request.getSlabId(), request.getSlabName(), request.getSlabRating(),request.getStartDate(), request.getEndDate(), request.getMinLimit(), request.getMaxLimit());
                SlabReading save = slabRepository.save(slabReading);
                if (save.getSlabId() != null) {
                    response.setStatus("Success");
                    response.setErrorCode("");
                    response.setMessage("Price slab saved successfully");
                } else {
                    response.setStatus("Error");
                    response.setErrorCode(Constants.SET_SLB_ERR1);
                    response.setMessage("Could not save price slab details. Please try after some time.");
                }
            } else {
                response.setStatus("Error");
                response.setErrorCode(Constants.SET_SLB_ERR2);
                response.setMessage("Duplicate slab. Slab already defined");
            }
        } catch (Exception e) {
            response.setStatus("Error");
            response.setErrorCode(Constants.EXP_SET_SLB);
            response.setMessage("Exception occurred in setPriceSlab");
        }

        return response;
    }

    @Override
    public SlabReadingResponse getPriceSlab() {
        SlabReadingResponse slabReadingResponse = new SlabReadingResponse();
        List<SlabReadingTo> slabReadingToList;
        try {
            List<SlabReading> slabReadings = slabRepository.findAll();
            if (!slabReadings.isEmpty()) {
                slabReadingToList = slabReadings.stream().map(slabReading -> {
                    SlabReadingTo slabReadingTo = new SlabReadingTo();
                    slabReadingTo.setSlabId(slabReading.getSlabId());
                    slabReadingTo.setSlabName(slabReading.getSlabName());
                    slabReadingTo.setSlabRating(slabReading.getSlabRate());
                    slabReadingTo.setEndDate(slabReading.getEndDate());
                    slabReadingTo.setStartDate(slabReading.getStartDate());
                    slabReadingTo.setMinLimit(slabReading.getMinLimit());
                    slabReadingTo.setMaxLimit(slabReading.getMaxLimit());
                    return slabReadingTo;
                }).collect(Collectors.toList());
                slabReadingResponse.setSlabReadingTos(slabReadingToList);
                slabReadingResponse.setStatus("Success");
                slabReadingResponse.setMessage("price slab loaded successfully");
            } else {
                slabReadingResponse.setStatus("Error");
                slabReadingResponse.setErrorCode(Constants.GET_SLB_ERR1);
                slabReadingResponse.setMessage("Enable to load price slab");
            }
        } catch (Exception e) {
            slabReadingResponse.setStatus("Error");
            slabReadingResponse.setErrorCode(Constants.EXP_GET_SLB);
            slabReadingResponse.setMessage("Exception occurred in getPriceSlab");
        }

        return slabReadingResponse;
    }

    @Override
    public GenericResponse setMeterReading(MeterReadingRequest request) {
        GenericResponse response = new GenericResponse();
        boolean duplicate = false;
        double slabRate;
        double charges;
        try {
            List<MeterReading> meterReadings = meterRepository.findAll();
            if (!meterReadings.isEmpty()) {
                duplicate = meterReadings.stream().anyMatch(meterReading -> meterReading.getBillNo().equals(request.getBillNo()));
            }
            if (!duplicate) {
                double consumption = request.getCurrentReading() - request.getPreviousReading();
                if (consumption > 0) {
                    List<SlabReading> slabReadings = slabRepository.findOneBySlabNameAndStartDateAndEndDate(request.getTariff(), request.getStartDate(), request.getEndDate());
                    if (!slabReadings.isEmpty()) {
                        List<SlabReading> slabReading = slabReadings.stream().map(slabReading1 -> {
                            if (consumption > slabReading1.getMinLimit()) {
                                if (slabReading1.getMaxLimit() != 0) {
                                    if (consumption < slabReading1.getMaxLimit()) {
                                        return slabReading1;
                                    }
                                } else {
                                    return slabReading1;
                                }
                            }
                            return new SlabReading();
                        }).collect(Collectors.toList());
                        if (slabReading.get(0).getSlabId() != null) {
                            slabRate = slabReading.get(0).getSlabRate();

                            charges = consumption * slabRate;

                            MeterReading meterReading = new MeterReading(request.getBillNo(), request.getBillPeriod(), request.getStartDate(), request.getEndDate(),
                                    request.getCurrentReading(), request.getPreviousReading(),
                                    request.getRrNumber(), request.getTariff(), charges, request.getAccountNumber(), request.getMeterNumber());
                            MeterReading save = meterRepository.save(meterReading);
                            if (save.getBillNo() != null) {
                                response.setStatus("Success");
                                response.setErrorCode("");
                                response.setMessage("Meter readings saved successfully");
                            } else {
                                response.setStatus("Error");
                                response.setErrorCode(Constants.SET_MTR_ERR1);
                                response.setMessage("Could not save details. Please try after some time.");
                            }
                        } else {
                            response.setStatus("Error");
                            response.setErrorCode(Constants.SET_MTR_ERR2);
                            response.setMessage("slab reading does not match");
                        }
                    } else {
                        response.setStatus("Error");
                        response.setErrorCode(Constants.SET_MTR_ERR3);
                        response.setMessage("Slab not set");
                    }
                } else {
                    response.setStatus("Error");
                    response.setErrorCode(Constants.SET_MTR_ERR4);
                    response.setMessage("current reading is less than previous reading");
                }
            }  else {
                response.setStatus("Error");
                response.setErrorCode(Constants.SET_MTR_ERR5);
                response.setMessage("Bill number already exist.");
            }
        } catch (Exception e) {
            response.setStatus("Error");
            response.setErrorCode(Constants.EXP_SET_MTR);
            response.setMessage("Exception occurred in setMeterReading");
        }
        return response;
    }

    @Override
    public BillDetailsResponse generateBill(String billNumber) {
        BillDetailsResponse response;
        try {
            MeterReading meterReading = meterRepository.findByBillNo(billNumber);
            if (meterReading.getBillNo() != null) {
                response = new BillDetailsResponse(meterReading.getBillNo(), meterReading.getBillPeriod(), meterReading.getStartDate(),
                        meterReading.getEndDate(), meterReading.getCurrentReading(), meterReading.getPreviousReading(),
                        meterReading.getRrNumber(), meterReading.getTariff(), meterReading.getBillCharge());
                response.setStatus("Success");
                response.setErrorCode("");
                response.setMessage("Bill generated successfully");
            } else {
                response = new BillDetailsResponse();
                response.setStatus("Error");
                response.setErrorCode(Constants.GB_ERR);
                response.setMessage("Could not find the bill details");
            }
        } catch (Exception e) {
            response = new BillDetailsResponse();
            response.setStatus("Error");
            response.setErrorCode(Constants.EXP_GB);
            response.setMessage("Exception occurred in generateBill");
        }
        return response;
    }

}
