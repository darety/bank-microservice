package com.olukotun.account.service;

import com.olukotun.account.dto.CustomerDto;


public interface IAccountService {

    /**
     * *
     * @param customerDTO- customerDTO Object
     */
    void createAccount(CustomerDto customerDTO);
    CustomerDto fetchAccount(String MobileNumber);
    boolean updateAccount(CustomerDto customerDTO);
    boolean deleteAccount(String MobileNumber);
}
