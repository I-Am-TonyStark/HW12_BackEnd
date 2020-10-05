package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Customer;

import java.util.Optional;

public interface CustomerService extends BaseService<Customer, Long> {

    Optional<Customer> createCustomer();

    Optional<Customer> retrieveCustomer();

    String updateCustomerPassword(Customer customer);

    String updateCustomer();

    String deleteCustomer();

    void showCustomers();

    void showCustomerByNationalCode();
}
