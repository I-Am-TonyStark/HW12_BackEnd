package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.Employee;
import com.mamalimomen.repositories.EmployeeRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee, Long> implements EmployeeRepository {
    public EmployeeRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return findAllByNamedQuery("Employee.findAll", Employee.class);
    }

    @Override
    public List<Employee> findManyEmployeesByPostTitle(String postTitle) {
        return findManyByNamedQuery("Employee.findManyByPostTitle", postTitle, Employee.class);
    }

    @Override
    public List<Employee> findManyEmployeesByBossNationalCode(String bossNationalCode) {
        return findManyByNamedQuery("Employee.findManyByBossNationalCode", bossNationalCode, Employee.class);
    }

    @Override
    public List<Employee> findManyEmployeesByBranchName(String branchName) {
        return findManyByNamedQuery("Employee.findManyByBranchName", branchName, Employee.class);
    }

    @Override
    public Optional<Employee> findOneEmployeeByNationalCode(String nationalCode) {
        return findOneByNamedQuery("Employee.findOneByNationalCode", nationalCode, Employee.class);
    }
}
