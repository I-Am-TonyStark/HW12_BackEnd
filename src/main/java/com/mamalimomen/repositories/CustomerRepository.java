package com.mamalimomen.repositories;

import com.mamalimomen.base.repositories.BaseRepository;
import com.mamalimomen.domains.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer, Long> {

    List<Customer> findAllCustomers();

    Optional<Customer> findOneCustomerByNationalCode(String nationalCode);
}
