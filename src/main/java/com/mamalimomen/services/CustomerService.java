package com.mamalimomen.services;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends UserService{
    List<Role> findAllRoles();

    Optional<Role> findOneRole(String title);
}
