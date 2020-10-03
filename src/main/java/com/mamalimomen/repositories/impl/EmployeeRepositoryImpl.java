package com.mamalimomen.repositories.impl;

import com.mamalimomen.repositories.EmployeeRepository;

import javax.persistence.EntityManager;

public class EmployeeRepositoryImpl extends UserRepositoryImpl implements EmployeeRepository {
    public EmployeeRepositoryImpl(EntityManager em) {
        super(em);
    }
}
