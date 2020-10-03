package com.mamalimomen.services.impl;

import com.mamalimomen.repositories.impl.CustomerRepositoryImpl;
import com.mamalimomen.services.CustomerService;

import javax.persistence.EntityManager;

public class CustomerServiceImpl extends UserServiceImpl implements CustomerService {
    public CustomerServiceImpl(EntityManager em) {
        super(new CustomerRepositoryImpl(em));
    }
}
