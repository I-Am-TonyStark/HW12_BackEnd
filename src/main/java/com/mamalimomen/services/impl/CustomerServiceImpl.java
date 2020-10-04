package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.repositories.CustomerRepository;
import com.mamalimomen.repositories.impl.CustomerRepositoryImpl;
import com.mamalimomen.services.CustomerService;

import javax.persistence.EntityManager;
import java.util.Optional;

//FIXME
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long, CustomerRepository> implements CustomerService {
    public CustomerServiceImpl(EntityManager em) {
        super(new CustomerRepositoryImpl(em));
    }

    @Override
    public String createCustomer() {
        return null;
    }

    @Override
    public Optional<Customer> retrieveCustomer() {
        return Optional.empty();
    }

    @Override
    public String updateCustomer() {
        return null;
    }

    @Override
    public String deleteCustomer() {
        return null;
    }

    @Override
    public void showCustomers() {

    }

    @Override
    public void showCustomerByNationalCode() {

    }
}
