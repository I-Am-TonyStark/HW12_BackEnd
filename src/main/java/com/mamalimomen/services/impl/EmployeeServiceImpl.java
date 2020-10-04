package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Employee;
import com.mamalimomen.repositories.EmployeeRepository;
import com.mamalimomen.repositories.impl.EmployeeRepositoryImpl;
import com.mamalimomen.services.EmployeeService;

import javax.persistence.EntityManager;
import java.util.Optional;

//FIXME
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long, EmployeeRepository> implements EmployeeService {
    public EmployeeServiceImpl(EntityManager em) {
        super(new EmployeeRepositoryImpl(em));
    }

    @Override
    public String createEmployee() {
        return null;
    }

    @Override
    public Optional<Employee> retrieveEmployee() {
        return Optional.empty();
    }

    @Override
    public String updateEmployee() {
        return null;
    }

    @Override
    public String deleteEmployee() {
        return null;
    }

    @Override
    public void showEmployees() {

    }

    @Override
    public void showEmployeesByPostTitle() {

    }

    @Override
    public void showEmployeesByBossNationalCode() {

    }

    @Override
    public void showEmployeesByBranchName() {

    }

    @Override
    public void showEmployeeByNationalCode() {

    }
}
