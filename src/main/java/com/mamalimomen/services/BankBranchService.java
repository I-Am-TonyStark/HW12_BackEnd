package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.BankBranch;

import java.util.List;
import java.util.Optional;

public interface BankBranchService extends BaseService<BankBranch, Long> {
    Optional<Category> findOneCategory(String title);

    List<Category> findAllCategories();
}
