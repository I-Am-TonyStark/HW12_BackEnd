package com.mamalimomen.services.impl;

import com.mamalimomen.repositories.impl.EmployeeRepositoryImpl;
import com.mamalimomen.services.EmployeeService;

import javax.persistence.EntityManager;

public class EmployeeServiceImpl extends UserServiceImpl implements EmployeeService {
    public EmployeeServiceImpl(EntityManager em) {
        super(new EmployeeRepositoryImpl(em));
    }
}
