package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Employee;

import java.util.Optional;

public interface EmployeeService extends BaseService<Employee, Long> {

    Optional<Employee> createEmployee(Employee manager);

    Optional<Employee> retrieveEmployee();

    String updateEmployeePassword(Employee employee);

    String updateEmployee();

    String deleteEmployee();

    void showEmployees();

    void showEmployeesByPostTitle();

    void showEmployeesByBossNationalCode(String bossNationalCode);

    String updateMyEmployeePost(Employee manager);

    boolean amIManager(Employee employee);

    void showEmployeesByBranchName();

    void showEmployeeByNationalCode();
}
