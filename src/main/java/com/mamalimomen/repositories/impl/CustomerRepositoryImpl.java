package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Customer;
import com.mamalimomen.repositories.CustomerRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long> implements CustomerRepository {

    public CustomerRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return findAllByNamedQuery("Customer.findAll", Customer.class);
    }

    @Override
    public Optional<Customer> findOneCustomerByNationalCode(String nationalCode) {
        return findOneByNamedQuery("Customer.findOneByNationalCode", nationalCode, Customer.class);
    }
}
