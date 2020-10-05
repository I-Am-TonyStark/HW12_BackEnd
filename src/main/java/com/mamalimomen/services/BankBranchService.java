package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.BankBranch;

import java.util.Optional;

public interface BankBranchService extends BaseService<BankBranch, Long> {
    Optional<BankBranch> createBankBranch();

    Optional<BankBranch> retrieveBankBranch();

    String updateBankBranch();

    String deleteBankBranch();

    void showBankBranches();

    void showBankBranchByName();
}
