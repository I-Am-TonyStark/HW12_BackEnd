package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.BankBranch;

import java.util.Optional;

public interface BankBranchService extends BaseService<BankBranch, Long> {
    String createAccount();

    Optional<Account> retrieveAccount();

    String updateAccount();

    String deleteAccount();

    void showBankBranches();

    void showBankBranchByName();
}
