package com.olukotun.account.service.impl;

import com.olukotun.account.constant.AccountConstant;
import com.olukotun.account.dto.AccountsDto;
import com.olukotun.account.dto.CustomerDto;
import com.olukotun.account.entity.Accounts;
import com.olukotun.account.entity.Customer;
import com.olukotun.account.exception.CustomerAlreadyExistException;
import com.olukotun.account.exception.ResourceNotFoundException;
import com.olukotun.account.mapper.AccountMapper;
import com.olukotun.account.mapper.CustomerMapper;
import com.olukotun.account.repository.AccountRepository;
import com.olukotun.account.repository.CustomerRepository;
import com.olukotun.account.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public void createAccount(CustomerDto customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exist with given mobile number " + customerDTO.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);


        accountRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
       Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
        Accounts customerAccount = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
       CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
       customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(customerAccount, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDTO) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDTO.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        Long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstant.SAVINGS);
        newAccount.setBranchAddress(AccountConstant.ADDRESS);
        return newAccount;
    }

}
