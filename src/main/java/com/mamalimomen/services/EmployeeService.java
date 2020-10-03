package com.mamalimomen.services;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends UserService{
    Optional<Tag> findOneTag(String title);

    List<Tag> findAllTags();
}