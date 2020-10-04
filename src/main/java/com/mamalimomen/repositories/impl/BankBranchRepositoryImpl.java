package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.BankBranch;
import com.mamalimomen.repositories.BankBranchRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BankBranchRepositoryImpl extends BaseRepositoryImpl<BankBranch, Long> implements BankBranchRepository {
    public BankBranchRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<BankBranch> findAllBankBranches() {
        return findAllByNamedQuery("BankBranch.findAll", BankBranch.class);
    }

    @Override
    public Optional<BankBranch> findOneBankBranchByName(String branchName) {
        return findOneByNamedQuery("BankBranch.findOneByName", branchName, BankBranch.class);
    }
}
