package com.mamalimomen.repositories.impl;

import com.mamalimomen.base.repositories.impl.BaseRepositoryImpl;
import com.mamalimomen.domains.BankBranch;
import com.mamalimomen.repositories.BankBranchRepository;

import javax.persistence.EntityManager;

public class BankBranchRepositoryImpl extends BaseRepositoryImpl<BankBranch,Long> implements BankBranchRepository {
    public BankBranchRepositoryImpl(EntityManager em) {
        super(em);
    }
}
