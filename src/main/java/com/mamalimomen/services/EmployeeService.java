package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Employee;

import java.util.Optional;

public interface EmployeeService extends BaseService<Employee, Long> {

    String createEmployee();

    Optional<Employee> retrieveEmployee();

    String updateEmployee();

    String deleteEmployee();

    void showEmployees();

    void showEmployeesByPostTitle();

    void showEmployeesByBossNationalCode();

    void showEmployeesByBranchName();

    void showEmployeeByNationalCode();
}
