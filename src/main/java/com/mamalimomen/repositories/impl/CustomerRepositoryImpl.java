package com.mamalimomen.repositories.impl;

import com.mamalimomen.repositories.CustomerRepository;

import javax.persistence.EntityManager;

public class CustomerRepositoryImpl extends UserRepositoryImpl implements CustomerRepository {

    public CustomerRepositoryImpl(EntityManager em) {
        super(em);
    }
}
