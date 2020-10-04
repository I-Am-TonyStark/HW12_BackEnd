package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {
    List<Employee> findAllEmployees();

    List<Employee> findManyEmployeesByPostTitle(String postTitle);

    List<Employee> findManyEmployeesByBossNationalCode(String bossNationalCode);

    List<Employee> findManyEmployeesByBranchName(String branchName);

    Optional<Employee> findOneEmployeeByNationalCode(String nationalCode);
}
