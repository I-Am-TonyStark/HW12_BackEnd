package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.Account;
import com.mamalimomen.domains.BankBranch;
import com.mamalimomen.repositories.BankBranchRepository;
import com.mamalimomen.repositories.impl.BankBranchRepositoryImpl;
import com.mamalimomen.services.BankBranchService;

import javax.persistence.EntityManager;
import java.util.Optional;

//FIXME
public class BankBranchServiceImpl extends BaseServiceImpl<BankBranch, Long, BankBranchRepository> implements BankBranchService {
    public BankBranchServiceImpl(EntityManager em) {
        super(new BankBranchRepositoryImpl(em));
    }

    @Override
    public String createAccount() {
        return null;
    }

    @Override
    public Optional<Account> retrieveAccount() {
        return Optional.empty();
    }

    @Override
    public String updateAccount() {
        return null;
    }

    @Override
    public String deleteAccount() {
        return null;
    }

    @Override
    public void showBankBranches() {

    }

    @Override
    public void showBankBranchByName() {

    }
}
